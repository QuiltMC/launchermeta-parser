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

public class VersionEntry {
    private final String id;
    private final String type;
    private final String url;
    private final String time;
    private final String releaseTime;
    private final String sha1;
    private final int complianceLevel;

    @Deprecated
    public VersionEntry(String id, String type, String url, String time, String releaseTime) {
        this(id, type, url, time, releaseTime, null, 0);
    }

    public VersionEntry(String id, String type, String url, String time, String releaseTime, String sha1, int complianceLevel) {
        this.id = id;
        this.type = type;
        this.url = url;
        this.time = time;
        this.releaseTime = releaseTime;
        this.sha1 = sha1;
        this.complianceLevel = complianceLevel;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public String getTime() {
        return time;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public Optional<String> getSha1() {
        return Optional.ofNullable(sha1);
    }

    public int getComplianceLevel() {
        return complianceLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VersionEntry versionEntry = (VersionEntry) o;
        return id.equals(versionEntry.id) && type.equals(versionEntry.type) && url.equals(versionEntry.url) && time.equals(versionEntry.time) && releaseTime.equals(versionEntry.releaseTime) && Objects.equals(sha1, versionEntry.sha1) && complianceLevel == versionEntry.complianceLevel;
    }
}
