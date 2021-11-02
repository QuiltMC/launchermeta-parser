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

import java.util.Objects;
import java.util.Optional;

import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;

public class Rule {
    private final String action;
    @Nullable
    private final OS os;
    @Nullable
    private final Features features;

    public Rule(String action, @Nullable OS os, @Nullable Features features) {
        this.action = action;
        this.os = os;
        this.features = features;
    }

    public String getAction() {
        return action;
    }

    public Optional<OS> getOs() {
        return Optional.ofNullable(os);
    }

    public Optional<Features> getFeatures() {
        return Optional.ofNullable(features);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rule rule = (Rule) o;
        return Objects.equals(action, rule.action) && Objects.equals(os, rule.os) && Objects.equals(features, rule.features);
    }

    public static class OS {
        @Nullable
        private final String name;
        @Nullable
        private final String version;
        @Nullable
        private final String arch;

        public OS(@Nullable String name, @Nullable String version, @Nullable String arch) {
            this.name = name;
            this.version = version;
            this.arch = arch;
        }

        public Optional<String> getName() {
            return Optional.ofNullable(name);
        }

        public Optional<String> getVersion() {
            return Optional.ofNullable(version);
        }

        public Optional<String> getArch() {
            return Optional.ofNullable(arch);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            OS os = (OS) o;
            return Objects.equals(name, os.name) && Objects.equals(version, os.version) && Objects.equals(arch, os.arch);
        }
    }

    public static class Features {
        @SerializedName("is_demo_user")
        @Nullable
        private Boolean isDemoUser;

        @SerializedName("has_custom_resolution")
        @Nullable
        private Boolean hasCustomResolution;

        public Features(@Nullable Boolean isDemoUser, @Nullable Boolean hasCustomResolution) {
            this.isDemoUser = isDemoUser;
            this.hasCustomResolution = hasCustomResolution;
        }

        public Optional<Boolean> getDemoUser() {
            return Optional.ofNullable(isDemoUser);
        }

        public Optional<Boolean> getHasCustomResolution() {
            return Optional.ofNullable(hasCustomResolution);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Features features = (Features) o;
            return Objects.equals(isDemoUser, features.isDemoUser) && Objects.equals(hasCustomResolution, features.hasCustomResolution);
        }
    }
}
