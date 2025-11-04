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

import java.util.Objects;
import java.util.Optional;

import org.jetbrains.annotations.Nullable;

/**
 * Information about a version in the manifest.
 */
public class VersionEntry {
    private final String id;
    private final String type;
    private final String url;
    private final String time;
    private final String releaseTime;
    @Nullable
    private final String sha1;

    @Nullable
    private final Integer complianceLevel;

    @Deprecated
    public VersionEntry(String id, String type, String url, String time, String releaseTime) {
        this(id, type, url, time, releaseTime, null, null);
    }

    public VersionEntry(String id, String type, String url, String time, String releaseTime, @Nullable String sha1, @Nullable Integer complianceLevel) {
        this.id = id;
        this.type = type;
        this.url = url;
        this.time = time;
        this.releaseTime = releaseTime;
        this.sha1 = sha1;
        this.complianceLevel = complianceLevel;
    }

    /**
     *
     * @return the id of the version
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @return the type of the version
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @return the version json file
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @return an ISO-8601 timestamp for the release of the version
     */
    public String getTime() {
        return time;
    }

    /**
     *
     * @return an ISO-8601 timestamp for the release of the version
     */
    public String getReleaseTime() {
        return releaseTime;
    }

    /**
     *
     * @return the SHA-1 for the version json, if present
     */
    public Optional<String> getSha1() {
        return Optional.ofNullable(sha1);
    }

    /**
     *
     * @return the compliance level of the version json, if present
     */
    public Optional<Integer> getComplianceLevel() {
        return Optional.ofNullable(complianceLevel);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VersionEntry versionEntry = (VersionEntry) o;
        return id.equals(versionEntry.id) && type.equals(versionEntry.type) && url.equals(versionEntry.url) && time.equals(versionEntry.time) && releaseTime.equals(versionEntry.releaseTime) && Objects.equals(sha1, versionEntry.sha1) && Objects.equals(complianceLevel, versionEntry.complianceLevel);
    }
}
