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

import java.util.Objects;
import java.util.Optional;

import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;

public class Downloads {
    private final DownloadableFile client;
    @SerializedName("client_mappings")
    @Nullable
    private final DownloadableFile clientMappings;
    @Nullable
    private final DownloadableFile server;
    @SerializedName("server_mappings")
    @Nullable
    private final DownloadableFile serverMappings;

    public Downloads(DownloadableFile client, @Nullable DownloadableFile clientMappings, @Nullable DownloadableFile server, @Nullable DownloadableFile serverMappings) {
        this.client = client;
        this.clientMappings = clientMappings;
        this.server = server;
        this.serverMappings = serverMappings;
    }

    public DownloadableFile getClient() {
        return client;
    }

    public Optional<DownloadableFile> getClientMappings() {
        return Optional.ofNullable(clientMappings);
    }

    public Optional<DownloadableFile> getServer() {
        return Optional.ofNullable(server);
    }

    public Optional<DownloadableFile> getServerMappings() {
        return Optional.ofNullable(serverMappings);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Downloads downloads = (Downloads) o;
        return Objects.equals(client, downloads.client) && Objects.equals(clientMappings, downloads.clientMappings) && Objects.equals(server, downloads.server) && Objects.equals(serverMappings, downloads.serverMappings);
    }
}
