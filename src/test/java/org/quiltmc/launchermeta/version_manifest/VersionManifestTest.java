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

import com.google.gson.JsonElement;
import org.junit.jupiter.api.Test;
import org.quiltmc.launchermeta.TestUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VersionManifestTest {
    public static final String MANIFEST_URL = "https://launchermeta.mojang.com/mc/game/version_manifest.json";

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

    @Test
    public void testParseFullJson() throws IOException {
        JsonElement json = TestUtil.getJsonFromURL(VersionManifestTest.MANIFEST_URL);
        VersionManifest manifest = VersionManifest.fromJson(json);

        assertEquals(manifest.getVersions().size(), json.getAsJsonObject().get("versions").getAsJsonArray().size(), "Size of version array matches json");
        assertEquals(manifest.getLatestVersions().getRelease(), json.getAsJsonObject().get("latest").getAsJsonObject().get("release").getAsString(), "Size of version array matches json");
    }

    @Test
    public void testParseTestJson() {
        VersionManifest actual = VersionManifest.fromString(TEST_JSON);
        VersionManifest expected = new VersionManifest(new LatestVersions("1.17.1", "21w42a"),
                List.of(new VersionEntry("21w42a", "snapshot", "https://launchermeta.mojang.com/v1/packages/f2affa3247f2471d3334b199d1915ce582914464/21w42a.json", "2021-10-20T12:46:24+00:00", "2021-10-20T12:41:25+00:00")));

        assertEquals(actual, expected, "Actual parse matches expected result");
    }

    @Test
    public void assertNoMethodReturnsAreNull() throws IOException {
        TestUtil.checkNoMethodsReturnNull(VersionManifest.class)
                .accept(VersionManifest.fromJson(TestUtil.getJsonFromURL(VersionManifestTest.MANIFEST_URL)));
    }
}