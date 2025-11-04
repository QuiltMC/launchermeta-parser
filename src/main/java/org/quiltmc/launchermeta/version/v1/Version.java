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

import java.io.Reader;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import org.jetbrains.annotations.Nullable;

/**
 * Information about the game version.
 */
public class Version {
    private static final Gson GSON = newGsonBuilder()
            .create();

    @Nullable
    private final Arguments arguments;

    @Nullable
    private final String minecraftArguments;
    private final AssetIndex assetIndex;
    private final String assets;
    @Nullable
    private final Integer complianceLevel;
    private final Downloads downloads;
    private final String id;
    @Nullable
    private final JavaVersion javaVersion;
    private final List<Library> libraries;
    @Nullable
    private final Logging logging;
    private final String mainClass;
    private final int minimumLauncherVersion;
    private final String releaseTime;
    private final String time;
    private final String type;

    public Version(@Nullable Arguments arguments, @Nullable String minecraftArguments, AssetIndex assetIndex, String assets, int complianceLevel, Downloads downloads, String id, @Nullable JavaVersion javaVersion, List<Library> libraries, @Nullable Logging logging, String mainClass, int minimumLauncherVersion, String releaseTime, String time, String type) {
        this.arguments = arguments;
        this.minecraftArguments = minecraftArguments;
        this.assetIndex = assetIndex;
        this.assets = assets;
        this.complianceLevel = complianceLevel;
        this.downloads = downloads;
        this.id = id;
        this.javaVersion = javaVersion;
        this.libraries = libraries;
        this.logging = logging;
        this.mainClass = mainClass;
        this.minimumLauncherVersion = minimumLauncherVersion;
        this.releaseTime = releaseTime;
        this.time = time;
        this.type = type;
    }

    /**
     *
     * @param json the json element
     * @return a parsed {@link Version}
     */
    public static Version fromJson(JsonElement json) {
        return GSON.fromJson(json, Version.class);
    }

    /**
     *
     * @param json the json string
     * @return a parsed {@link Version}
     */
    public static Version fromString(String json) {
        return GSON.fromJson(json, Version.class);
    }

    /**
     *
     * @param reader a reader for the json
     * @return a parsed {@link Version}
     */
    public static Version fromReader(Reader reader) {
        return GSON.fromJson(reader, Version.class);
    }

    /**
     *
     * @return a {@link GsonBuilder} with custom parserss
     */
    public static GsonBuilder newGsonBuilder() {
        return new GsonBuilder()
                .registerTypeAdapter(Arguments.Argument.class, new Arguments.Argument.Parser())
                .registerTypeAdapter(Arguments.Argument.class, new Arguments.Argument.Serializer());
    }

    /**
     *
     * @return the arguments for the game, if present
     */
    public Optional<Arguments> getArguments() {
        return Optional.ofNullable(arguments);
    }

    /**
     *
     * @return the arguments for the game, if present
     */
    public Optional<String> getMinecraftArguments() {
        return Optional.ofNullable(minecraftArguments);
    }

    /**
     *
     * @return the asset index for the game
     */
    public AssetIndex getAssetIndex() {
        return assetIndex;
    }

    /**
     *
     * @return the assets version
     */
    public String getAssets() {
        return assets;
    }

    /**
     *
     * @return the compliance level of the game, if present
     */
    public Optional<Integer> getComplianceLevel() {
        return Optional.ofNullable(complianceLevel);
    }

    /**
     *
     * @return the files to download for the game
     */
    public Downloads getDownloads() {
        return downloads;
    }

    /**
     *
     * @return the id of the game
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @return the java version for the game
     */
    public Optional<JavaVersion> getJavaVersion() {
        return Optional.ofNullable(javaVersion);
    }

    /**
     *
     * @return the libraries needed for the game
     */
    public List<Library> getLibraries() {
        return libraries;
    }

    /**
     *
     * @return the logging information for the game, if present
     */
    public Optional<Logging> getLogging() {
        return Optional.ofNullable(logging);
    }

    /**
     *
     * @return the main class for the game
     */
    public String getMainClass() {
        return mainClass;
    }

    /**
     * Can be used as a form of a version for the version information.
     * ie support for version 1 but getting version 2 could indicate a breaking change.
     * @return the minimum launcher version supported.
     */
    public int getMinimumLauncherVersion() {
        return minimumLauncherVersion;
    }

    /**
     *
     * @return an ISO-8601 timestamp for the release time
     */
    public String getReleaseTime() {
        return releaseTime;
    }

    /**
     *
     * @return an ISO-8601 timestamp for the release time
     */
    public String getTime() {
        return time;
    }

    /**
     *
     * @return the type of the game version
     */
    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Version version = (Version) o;
        return Objects.equals(complianceLevel, version.complianceLevel) && minimumLauncherVersion == version.minimumLauncherVersion && Objects.equals(arguments, version.arguments) && Objects.equals(minecraftArguments, version.minecraftArguments) && Objects.equals(assetIndex, version.assetIndex) && Objects.equals(assets, version.assets) && Objects.equals(downloads, version.downloads) && Objects.equals(id, version.id) && Objects.equals(javaVersion, version.javaVersion) && Objects.equals(libraries, version.libraries) && Objects.equals(logging, version.logging) && Objects.equals(mainClass, version.mainClass) && Objects.equals(releaseTime, version.releaseTime) && Objects.equals(time, version.time) && Objects.equals(type, version.type);
    }
}
