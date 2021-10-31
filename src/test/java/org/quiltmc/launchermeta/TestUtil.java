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
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public final class TestUtil {
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
}
