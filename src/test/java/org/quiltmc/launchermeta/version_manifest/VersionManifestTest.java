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

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import com.google.gson.JsonElement;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import org.quiltmc.launchermeta.TestUtil;

import static org.junit.jupiter.api.Assertions.*;

public class VersionManifestTest {
    private static final String MANIFEST_URL = "https://launchermeta.mojang.com/mc/game/version_manifest.json";
    private static final String MANIFEST_URL_V2 = "https://piston-meta.mojang.com/mc/game/version_manifest_v2.json";

    private static final String TEST_JSON = """
            {
                "latest": {
                    "release": "1.17.1",
                    "snapshot": "21w42a"
                },
                "versions": [{
                    "id": "21w42a",
                    "type": "snapshot",
                    "url": "https://launchermeta.mojang.com/v1/packages/f2affa3247f2471d3334b199d1915ce582914464/21w42a.json",
                    "time": "2021-10-20T12:46:24+00:00",
                    "releaseTime": "2021-10-20T12:41:25+00:00"
                }]
            }
            """;
    private static final String TEST_JSON_V2 = """
            {
                "latest": {
                    "release": "1.17.1",
                    "snapshot": "21w42a"
                },
                "versions": [{
                    "id": "21w42a",
                    "type": "snapshot",
                    "url": "https://piston-meta.mojang.com/v1/packages/3ce8fdf60e69bfb0944e479ada4cf6b60dcc3995/21w42a.json",
                    "time": "2021-10-20T12:46:24+00:00",
                    "releaseTime": "2021-10-20T12:41:25+00:00",
                    "sha1": "3ce8fdf60e69bfb0944e479ada4cf6b60dcc3995",
                    "complianceLevel": 1
                }]
            }
            """;
    private static final VersionManifest VERSION_MANIFEST = new VersionManifest(new LatestVersions("1.17.1", "21w42a"), List.of(new VersionEntry("21w42a", "snapshot", "https://launchermeta.mojang.com/v1/packages/f2affa3247f2471d3334b199d1915ce582914464/21w42a.json", "2021-10-20T12:46:24+00:00", "2021-10-20T12:41:25+00:00")));
    public static final VersionManifest VERSION_MANIFEST_V2 = new VersionManifest(new LatestVersions("1.17.1", "21w42a"), List.of(new VersionEntry("21w42a", "snapshot", "https://piston-meta.mojang.com/v1/packages/3ce8fdf60e69bfb0944e479ada4cf6b60dcc3995/21w42a.json", "2021-10-20T12:46:24+00:00", "2021-10-20T12:41:25+00:00", "3ce8fdf60e69bfb0944e479ada4cf6b60dcc3995", 1)));

    @ParameterizedTest
    @ValueSource(strings = {MANIFEST_URL, MANIFEST_URL_V2})
    void testParseFullJson(String url) throws IOException {
        JsonElement json = TestUtil.getJsonFromURL(url);
        VersionManifest manifest = VersionManifest.fromJson(json);

        assertEquals(manifest.getVersions().size(), json.getAsJsonObject().get("versions").getAsJsonArray().size(), "Size of version array matches json");
        assertEquals(manifest.getLatestVersions().getRelease(), json.getAsJsonObject().get("latest").getAsJsonObject().get("release").getAsString(), "Size of version array matches json");
    }

    @Test
    void testParseFullJsonV2HasShaAnCompliance() throws IOException {
        JsonElement json = TestUtil.getJsonFromURL(MANIFEST_URL_V2);
        VersionManifest manifest = VersionManifest.fromJson(json);
        String latestVersion = manifest.getLatestVersions().getRelease();
        VersionEntry latestEntry = manifest.getVersions().stream()
                .filter(entry -> entry.getId().equals(latestVersion))
                .findFirst()
                .orElseThrow();
        assertNotNull(latestEntry.getSha1(), "Latest version has a sha1");
        assertNotEquals(latestEntry.getComplianceLevel(), 0, "Latest version has a compliance level");
    }

    @ParameterizedTest
    @MethodSource("provideManifest")
    void testParseTestJson(String json, VersionManifest manifest) {
        VersionManifest actual = VersionManifest.fromString(json);
        assertEquals(actual, manifest, "Actual parse matches expected result");
    }

    @ParameterizedTest
    @ValueSource(strings = {MANIFEST_URL, MANIFEST_URL_V2})
    void checkRemoteNoNulls(String url) throws IOException {
        TestUtil.checkNoMethodsReturnNull(VersionManifest.class).accept(VersionManifest.fromJson(TestUtil.getJsonFromURL(url)));
    }

    private static Stream<Arguments> provideManifest() {
        return Stream.of(
                Arguments.of(TEST_JSON, VERSION_MANIFEST),
                Arguments.of(TEST_JSON_V2, VERSION_MANIFEST_V2)
        );
    }
}