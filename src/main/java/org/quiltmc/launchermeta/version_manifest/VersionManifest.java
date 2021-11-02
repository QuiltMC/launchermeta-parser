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
package org.quiltmc.launchermeta.version_manifest;

import java.io.Reader;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;

public class VersionManifest {
    public static final Gson GSON = new Gson();

    @SerializedName("latest")
    private final LatestVersions latestVersions;

    private final List<VersionEntry> versions;

    public VersionManifest(LatestVersions latestVersions, List<VersionEntry> versions) {
        this.latestVersions = latestVersions;
        this.versions = versions;
    }

    public static VersionManifest fromJson(JsonElement json) {
        return GSON.fromJson(json, VersionManifest.class);
    }

    public static VersionManifest fromString(String json) {
        return GSON.fromJson(json, VersionManifest.class);
    }

    public static VersionManifest fromReader(Reader reader) {
        return GSON.fromJson(reader, VersionManifest.class);
    }

    public LatestVersions getLatestVersions() {
        return latestVersions;
    }

    public List<VersionEntry> getVersions() {
        return versions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VersionManifest manifest = (VersionManifest) o;
        return latestVersions.equals(manifest.latestVersions) && versions.equals(manifest.versions);
    }
}
