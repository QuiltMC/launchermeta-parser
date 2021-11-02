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
package org.quiltmc.launchermeta.version.v1;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

public class Arguments {
    private final List<Argument> game;
    private final List<Argument> jvm;

    public Arguments(List<Argument> game, List<Argument> jvm) {
        this.game = game;
        this.jvm = jvm;
    }

    public List<Argument> getGame() {
        return game;
    }

    public List<Argument> getJvm() {
        return jvm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Arguments arguments = (Arguments) o;
        return Objects.equals(game, arguments.game) && Objects.equals(jvm, arguments.jvm);
    }

    public static class Argument {
        private final List<String> value;
        private final List<Rule> rules;

        public Argument(List<String> value, List<Rule> rules) {
            this.value = value;
            this.rules = rules;
        }

        public List<String> getValue() {
            return value;
        }

        public List<Rule> getRules() {
            return rules;
        }

        public static class Parser implements JsonDeserializer<Argument> {
            @Override
            public Argument deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                List<String> value;
                List<Rule> rules = Collections.emptyList();

                if (json.isJsonObject()) { // has rules
                    JsonObject argument = json.getAsJsonObject();
                    rules = context.deserialize(argument.get("rules"), new TypeToken<List<Rule>>() {
                    }.getType());

                    rules = rules == null ? Collections.emptyList() : rules;

                    if (argument.get("value").isJsonArray()) {
                        value = context.deserialize(argument.get("value"), new TypeToken<List<String>>() {
                        }.getType());
                    } else {
                        value = Collections.singletonList(argument.get("value").getAsString());
                    }
                } else {
                    value = Collections.singletonList(json.getAsString());
                }

                return new Argument(value, rules);
            }
        }
    }
}
