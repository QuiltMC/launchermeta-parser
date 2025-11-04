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

/**
 * A library for the game.
 */
public class Library {
    private final LibraryDownloads downloads;
    private final String name;
    @Nullable
    private final Natives natives;
    @Nullable
    private final Extract extract;
    private final List<Rule> rules;

    public Library(LibraryDownloads downloads, String name, @Nullable Natives natives, @Nullable Extract extract, List<Rule> rules) {
        this.downloads = downloads;
        this.name = name;
        this.natives = natives;
        this.extract = extract;
        this.rules = rules;
    }

    /**
     *
     * @return the downloads for the library
     */
    public LibraryDownloads getDownloads() {
        return downloads;
    }

    /**
     *
     * @return the name of the library
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return the natives for the library, if present
     */
    public Optional<Natives> getNatives() {
        return Optional.ofNullable(natives);
    }

    /**
     *
     * @return the file extraction rules, if present
     */
    public Optional<Extract> getExtract() {
        return Optional.ofNullable(extract);
    }

    /**
     *
     * @return the rules to enable the library
     */
    public List<Rule> getRules() {
        return rules == null ? Collections.emptyList() : rules;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Library library = (Library) o;
        return downloads.equals(library.downloads) && name.equals(library.name) && Objects.equals(natives, library.natives) && Objects.equals(extract, library.extract) && rules.equals(library.rules);
    }

    /**
     * The different classifiers for the library, usually referring to a native target.
     */
    public static class Classifiers {
        @Nullable
        private final DownloadableFile.PathDownload javadoc;

        @SerializedName("natives-linux")
        @Nullable
        private final DownloadableFile.PathDownload nativesLinux;

        @SerializedName("linux-x86_64")
        @Nullable
        private final DownloadableFile.PathDownload linux_x86_64;

        @SerializedName("natives-macos")
        @Nullable
        private final DownloadableFile.PathDownload nativesMacOS;

        @SerializedName("natives-osx")
        @Nullable
        private final DownloadableFile.PathDownload nativesOSX;

        @SerializedName("natives-windows")
        @Nullable
        private final DownloadableFile.PathDownload nativesWindows;

        @SerializedName("natives-windows-32")
        @Nullable
        private final DownloadableFile.PathDownload nativesWindows32;

        @SerializedName("natives-windows-64")
        @Nullable
        private final DownloadableFile.PathDownload nativesWindows64;

        @Nullable
        private final DownloadableFile.PathDownload sources;

        public Classifiers(@Nullable DownloadableFile.PathDownload javadoc, @Nullable DownloadableFile.PathDownload nativesLinux, @Nullable DownloadableFile.PathDownload linux_x86_64, @Nullable DownloadableFile.PathDownload nativesMacOS, DownloadableFile.@Nullable PathDownload nativesOSX, @Nullable DownloadableFile.PathDownload nativesWindows, @Nullable DownloadableFile.PathDownload nativesWindows32, @Nullable DownloadableFile.PathDownload nativesWindows64, @Nullable DownloadableFile.PathDownload sources) {
            this.javadoc = javadoc;
            this.nativesLinux = nativesLinux;
            this.linux_x86_64 = linux_x86_64;
            this.nativesMacOS = nativesMacOS;
            this.nativesOSX = nativesOSX;
            this.nativesWindows = nativesWindows;
            this.nativesWindows32 = nativesWindows32;
            this.nativesWindows64 = nativesWindows64;
            this.sources = sources;
        }

        /**
         *
         * @return the file for the javadoc classifier, if present
         */
        public Optional<DownloadableFile.PathDownload> getJavadoc() {
            return Optional.ofNullable(javadoc);
        }

        /**
         *
         * @return the file for the linux classifier, if present
         */
        public Optional<DownloadableFile.PathDownload> getNativesLinux() {
            return Optional.ofNullable(nativesLinux);
        }

        /**
         *
         * @return the file for the linux-x86_64 classifier, if present
         */
        public Optional<DownloadableFile.PathDownload> getNativesLinux_x84_64() {
            return Optional.ofNullable(linux_x86_64);
        }

        /**
         *
         * @return the file for the macOS classifier, if present
         */
        public Optional<DownloadableFile.PathDownload> getNativesMacOS() {
            return Optional.ofNullable(nativesMacOS);
        }

        /**
         *
         * @return the file for the OSX classifier, if present
         */
        public Optional<DownloadableFile.PathDownload> getNativesOSX() {
            return Optional.ofNullable(nativesOSX);
        }

        /**
         *
         * @return the file for the windows classifier, if present
         */
        public Optional<DownloadableFile.PathDownload> getNativesWindows() {
            return Optional.ofNullable(nativesWindows);
        }

        /**
         *
         * @return the file for the windows 32bit classifier, if present
         */
        public Optional<DownloadableFile.PathDownload> getNativesWindows32() {
            return Optional.ofNullable(nativesWindows32);
        }

        /**
         *
         * @return the file for the windows 64bit classifier, if present
         */
        public Optional<DownloadableFile.PathDownload> getNativesWindows64() {
            return Optional.ofNullable(nativesWindows64);
        }

        /**
         *
         * @return the file for the sources classifier, if present
         */
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

    /**
     * The classifiers based on the os for the library
     */
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

        /**
         *
         * @return the osx native classifier, if present
         */
        public Optional<String> getOsx() {
            return Optional.ofNullable(osx);
        }

        /**
         *
         * @return the linux native classifier, if present
         */
        public Optional<String> getLinux() {
            return Optional.ofNullable(linux);
        }

        /**
         *
         * @return the windows native classifier, if present
         */
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

    /**
     * The extract rules for the library.
     */
    public static class Extract {
        private final List<String> exclude;

        public Extract(List<String> exclude) {
            this.exclude = exclude;
        }

        /**
         *
         * @return the list of files to exclude when extracting
         */
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

    /**
     * The main and classified downloads for the library.
     */
    public static class LibraryDownloads {
        @Nullable
        private final DownloadableFile.PathDownload artifact;
        @Nullable
        private final Classifiers classifiers;

        public LibraryDownloads(@Nullable DownloadableFile.PathDownload artifact, @Nullable Classifiers classifiers) {
            this.artifact = artifact;
            this.classifiers = classifiers;
        }

        /**
         *
         * @return the main artifact for the library, if present
         */
        public Optional<DownloadableFile.PathDownload> getArtifact() {
            return Optional.ofNullable(artifact);
        }

        /**
         *
         * @return the classifier downloads for the library, if present
         */
        public Optional<Classifiers> getClassifiers() {
            return Optional.ofNullable(classifiers);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            LibraryDownloads downloads = (LibraryDownloads) o;
            return Objects.equals(artifact, downloads.artifact) && Objects.equals(classifiers, downloads.classifiers);
        }
    }
}
