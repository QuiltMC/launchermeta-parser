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

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;

public class Library {
    private final Downloads downloads;
    private final String name;
    @Nullable
    private final Natives natives;
    @Nullable
    private final Extract extract;
    private List<Rule> rules = Collections.emptyList();

    public Library(Downloads downloads, String name, @Nullable Natives natives, @Nullable Extract extract, List<Rule> rules) {
        this.downloads = downloads;
        this.name = name;
        this.natives = natives;
        this.extract = extract;
        this.rules = rules;
    }

    public Downloads getDownloads() {
        return downloads;
    }

    public String getName() {
        return name;
    }

    public Optional<Natives> getNatives() {
        return Optional.ofNullable(natives);
    }

    public Optional<Extract> getExtract() {
        return Optional.ofNullable(extract);
    }

    public List<Rule> getRules() {
        return rules;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Library library = (Library) o;
        return downloads.equals(library.downloads) && name.equals(library.name) && Objects.equals(natives, library.natives) && Objects.equals(extract, library.extract) && rules.equals(library.rules);
    }

    public static class Classifiers {
        @Nullable
        private final DownloadableFile.PathDownload javadoc;

        @SerializedName("natives-linux")
        @Nullable
        private final DownloadableFile.PathDownload nativesLinux;

        @SerializedName("natives-macos")
        @Nullable
        private final DownloadableFile.PathDownload nativesMacOS;

        @SerializedName("natives-osx")
        @Nullable
        private final DownloadableFile.PathDownload nativesOSX;

        @SerializedName("natives-windows")
        @Nullable
        private final DownloadableFile.PathDownload nativesWindows;

        @Nullable
        private final DownloadableFile.PathDownload sources;

        public Classifiers(@Nullable DownloadableFile.PathDownload javadoc, @Nullable DownloadableFile.PathDownload nativesLinux, @Nullable DownloadableFile.PathDownload nativesMacOS, DownloadableFile.@Nullable PathDownload nativesOSX, @Nullable DownloadableFile.PathDownload nativesWindows, @Nullable DownloadableFile.PathDownload sources) {
            this.javadoc = javadoc;
            this.nativesLinux = nativesLinux;
            this.nativesMacOS = nativesMacOS;
            this.nativesOSX = nativesOSX;
            this.nativesWindows = nativesWindows;
            this.sources = sources;
        }

        public Optional<DownloadableFile.PathDownload> getJavadoc() {
            return Optional.ofNullable(javadoc);
        }

        public Optional<DownloadableFile.PathDownload> getNativesLinux() {
            return Optional.ofNullable(nativesLinux);
        }

        public Optional<DownloadableFile.PathDownload> getNativesMacOS() {
            return Optional.ofNullable(nativesMacOS);
        }

        public Optional<DownloadableFile.PathDownload> getNativesOSX() {
            return Optional.ofNullable(nativesOSX);
        }

        public Optional<DownloadableFile.PathDownload> getNativesWindows() {
            return Optional.ofNullable(nativesWindows);
        }

        public Optional<DownloadableFile.PathDownload> getSources() {
            return Optional.ofNullable(sources);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Classifiers that = (Classifiers) o;
            return Objects.equals(javadoc, that.javadoc) && Objects.equals(nativesLinux, that.nativesLinux) && Objects.equals(nativesMacOS, that.nativesMacOS) && Objects.equals(nativesOSX, that.nativesOSX) && Objects.equals(nativesWindows, that.nativesWindows) && Objects.equals(sources, that.sources);
        }
    }

    public static class Natives {
        @Nullable
        private final String osx;
        @Nullable
        private final String linux;
        @Nullable
        private final String windows;

        public Natives(@Nullable String osx, @Nullable String linux, @Nullable String windows) {
            this.osx = osx;
            this.linux = linux;
            this.windows = windows;
        }

        public Optional<String> getOsx() {
            return Optional.ofNullable(osx);
        }

        public Optional<String> getLinux() {
            return Optional.ofNullable(linux);
        }

        public Optional<String> getWindows() {
            return Optional.ofNullable(windows);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Natives natives = (Natives) o;
            return Objects.equals(osx, natives.osx) && Objects.equals(linux, natives.linux) && Objects.equals(windows, natives.windows);
        }
    }

    public static class Extract {
        private List<String> exclude = Collections.emptyList();

        public Extract(List<String> exclude) {
            this.exclude = exclude;
        }

        public List<String> getExclude() {
            return exclude;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Extract extract = (Extract) o;
            return Objects.equals(exclude, extract.exclude);
        }
    }

    public static class Downloads {
        private final DownloadableFile.PathDownload artifact;
        @Nullable
        private final Classifiers classifiers;

        public Downloads(DownloadableFile.PathDownload artifact, @Nullable Classifiers classifiers) {
            this.artifact = artifact;
            this.classifiers = classifiers;
        }

        public DownloadableFile.PathDownload getArtifact() {
            return artifact;
        }

        public Optional<Classifiers> getClassifiers() {
            return Optional.ofNullable(classifiers);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Downloads downloads = (Downloads) o;
            return Objects.equals(artifact, downloads.artifact) && Objects.equals(classifiers, downloads.classifiers);
        }
    }
}
