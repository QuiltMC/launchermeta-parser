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

import java.io.IOException;

import com.google.gson.JsonElement;
import org.junit.jupiter.api.Test;
import org.quiltmc.launchermeta.TestUtil;
import org.quiltmc.launchermeta.version_manifest.VersionManifest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class VersionTest {
    private static final String VERSION_URL = "https://launchermeta.mojang.com/v1/packages/f2affa3247f2471d3334b199d1915ce582914464/21w42a.json";
    private static final String MANIFEST_URL = "https://launchermeta.mojang.com/mc/game/version_manifest.json";

    @Test
    public void testParseFullJson() throws IOException {
        JsonElement json = TestUtil.getJsonFromURL(VERSION_URL);
        assertDoesNotThrow(() -> {
            Version version = Version.fromJson(json);
        });
    }

    @Test
    public void assertNoMethodReturnsAreNull() throws IOException {
        VersionManifest.fromJson(TestUtil.getJsonFromURL(MANIFEST_URL))
                .getVersions()
                .parallelStream()
                .map(version -> {
                    JsonElement json = null;
                    try {
                        json = TestUtil.getJsonFromURL(version.getUrl());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return Version.fromJson(json);
                })
                .forEach(TestUtil.checkNoMethodsReturnNull(Version.class));
    }
}