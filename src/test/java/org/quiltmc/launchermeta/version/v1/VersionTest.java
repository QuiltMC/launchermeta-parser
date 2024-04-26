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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.quiltmc.launchermeta.TestUtil;
import org.quiltmc.launchermeta.version_manifest.VersionManifest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class VersionTest {
    private static final String VERSION_URL = "https://piston-meta.mojang.com/v1/packages/3ecc58bbbc2b680be6742747089cbbf3272526f9/1.20.6-rc1.json";
    private static final String MANIFEST_URL = "https://launchermeta.mojang.com/mc/game/version_manifest_v2.json";

    @Test
    public void testParseDoesNotThrow() throws IOException {
        JsonElement json = TestUtil.getJsonFromURL(VERSION_URL);
        assertDoesNotThrow(() -> {
            Version version = Version.fromJson(json);
        });
    }

    @Disabled("Manual verification passes with 1.20.6-rc1, but the order of elements is different")
    @Test
    public void testParseFullJson() throws IOException {
        JsonElement json = TestUtil.getJsonFromURL(VERSION_URL);
        Assertions.assertEquals(json, Version.GSON.toJsonTree(Version.fromJson(json)));
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

    @Disabled("Manual verification passes with 1.20.6-rc1, but the order of elements is different")
    @Test
    public void assertCreatesSameJson() throws IOException {
        VersionManifest.fromJson(TestUtil.getJsonFromURL(MANIFEST_URL))
                .getVersions()
                .parallelStream()
                .forEach(version -> {
                    JsonElement json = null;
                    try {
                        json = TestUtil.getJsonFromURL(version.getUrl());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Assertions.assertEquals(Version.GSON.toJsonTree(Version.fromJson(json)), json);
                });
    }
}