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

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import org.jetbrains.annotations.Nullable;

public class Version {
    public static final Gson GSON = new GsonBuilder()
            .registerTypeAdapter(Arguments.Argument.class, new Arguments.Argument.Parser())
            .create();

    @Nullable
    private final Arguments arguments;
    private final AssetIndex assetIndex;
    private final String assets;
    private final int complianceLevel;
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

    public Version(@Nullable Arguments arguments, AssetIndex assetIndex, String assets, int complianceLevel, Downloads downloads, String id, @Nullable JavaVersion javaVersion, List<Library> libraries, @Nullable Logging logging, String mainClass, int minimumLauncherVersion, String releaseTime, String time, String type) {
        this.arguments = arguments;
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

    public static Version fromJson(JsonElement json) {
        return GSON.fromJson(json, Version.class);
    }

    public static Version fromString(String json) {
        return GSON.fromJson(json, Version.class);
    }

    public Optional<Arguments> getArguments() {
        return Optional.ofNullable(arguments);
    }

    public AssetIndex getAssetIndex() {
        return assetIndex;
    }

    public String getAssets() {
        return assets;
    }

    public int getComplianceLevel() {
        return complianceLevel;
    }

    public Downloads getDownloads() {
        return downloads;
    }

    public String getId() {
        return id;
    }

    public Optional<JavaVersion> getJavaVersion() {
        return Optional.ofNullable(javaVersion);
    }

    public List<Library> getLibraries() {
        return libraries;
    }

    public Optional<Logging> getLogging() {
        return Optional.ofNullable(logging);
    }

    public String getMainClass() {
        return mainClass;
    }

    public int getMinimumLauncherVersion() {
        return minimumLauncherVersion;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public String getTime() {
        return time;
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Version version = (Version) o;
        return complianceLevel == version.complianceLevel && minimumLauncherVersion == version.minimumLauncherVersion && Objects.equals(arguments, version.arguments) && Objects.equals(assetIndex, version.assetIndex) && Objects.equals(assets, version.assets) && Objects.equals(downloads, version.downloads) && Objects.equals(id, version.id) && Objects.equals(javaVersion, version.javaVersion) && Objects.equals(libraries, version.libraries) && Objects.equals(logging, version.logging) && Objects.equals(mainClass, version.mainClass) && Objects.equals(releaseTime, version.releaseTime) && Objects.equals(time, version.time) && Objects.equals(type, version.type);
    }
}
