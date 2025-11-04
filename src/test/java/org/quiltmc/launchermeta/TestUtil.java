/*
 * Copyright 2021 QuiltMC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.quiltmc.launchermeta;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.quiltmc.launchermeta.version.v1.Version;

public final class TestUtil {
    public static Gson GSON = Version.newGsonBuilder().create();

    public static <T> List<Method> getAllGetterMethodsThatAreNotFromObject(Class<T> clazz) {
        Method[] methods = clazz.getMethods();

        return Arrays.stream(methods)
                .filter(method -> method.getParameterCount() == 0)
                .filter(method -> !method.getDeclaringClass().equals(Object.class))
                .collect(Collectors.toList());
    }

    public static JsonElement getJsonFromURL(String url) throws IOException {
        return JsonParser.parseString(new String(new URL(url).openStream().readAllBytes()));
    }

    public static <T> Consumer<T> checkNoMethodsReturnNull(Class<T> clazz) {
        return instance -> {
            Map<Object, List<Method>> getters = Map.of(instance, TestUtil.getAllGetterMethodsThatAreNotFromObject(clazz));

            while (!getters.isEmpty()) {
                Map<Object, List<Method>> newGetters = new HashMap<>();
                for (Object invoke : getters.keySet()) {
                    List<Method> methods = getters.get(invoke);
                    for (Method method : methods) {
                        try {
                            Object result = method.invoke(invoke);
                            assertNotNull(result, "Method " + method.getName() + " returned null with version " + instance);
                            if (result.getClass().getPackageName().startsWith("org.quiltmc")) {
                                newGetters.put(result, TestUtil.getAllGetterMethodsThatAreNotFromObject(result.getClass()));
                            } else if (result instanceof Optional<?> o && o.isPresent() && o.get().getClass().getPackageName().startsWith("org.quiltmc")) {
                                newGetters.put(o.get(), TestUtil.getAllGetterMethodsThatAreNotFromObject(o.get().getClass()));
                            } else if (result instanceof List<?> l && !l.isEmpty()) {
                                for (Object o : l) {
                                    newGetters.put(o, TestUtil.getAllGetterMethodsThatAreNotFromObject(o.getClass()));
                                }
                            }
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }
                getters = newGetters;
            }
        };
    }

    // This is a very rough and coarse compare, definitely not optimal at all
    public static boolean compareJsonElements(JsonElement a, JsonElement b) {
        if (a.isJsonObject() && b.isJsonObject()) {
            Set<String> aKeys = a.getAsJsonObject().keySet();
            Set<String> bKeys = b.getAsJsonObject().keySet();

            if (aKeys.containsAll(bKeys) && bKeys.containsAll(aKeys)) {
                for (Map.Entry<String, JsonElement> element : a.getAsJsonObject().entrySet()) {
                    if (!compareJsonElements(element.getValue(), b.getAsJsonObject().get(element.getKey()))) {
                        return false;
                    }
                }
            } else {
                Set<String> aExtra = new HashSet<>(aKeys);
                Set<String> bExtra = new HashSet<>(bKeys);

                aExtra.removeAll(bKeys);
                bExtra.removeAll(aKeys);

                System.err.printf("Mismatch between json element keys! %s <=> %s%n", aExtra, bExtra);
                return false;
            }

        } else if (a.isJsonArray() && b.isJsonArray()) {
            if (a.getAsJsonArray().size() != b.getAsJsonArray().size()) {
                System.err.println("Array lengths do not match");
                return false;
            }
            for (int i = 0; i < a.getAsJsonArray().size(); i++) {
                if (!compareJsonElements(a.getAsJsonArray().get(i), b.getAsJsonArray().get(i))) {
                    return false;
                }
            }
        } else if (a.isJsonPrimitive() && b.isJsonPrimitive()) {
            if (!a.getAsJsonPrimitive().equals(b.getAsJsonPrimitive())) {
                System.err.printf("Primitive mismatch: %s <=> %s%n", a, b);
                return false;
            }
        } else if (a.isJsonNull() && b.isJsonNull()) {
            return true;
        } else if (a.isJsonArray() && b.isJsonPrimitive()) {
            if (a.getAsJsonArray().size() == 1) {
                return a.getAsJsonArray().get(0).equals(b);
            }
        } else if (b.isJsonArray() && a.isJsonPrimitive()) {
            if (b.getAsJsonArray().size() == 1) {
                return b.getAsJsonArray().get(0).equals(a);
            }
        } else  {
            System.out.println("Failed to compare json elements " + a + " and " + b);
        }
        return true;
    }
}
