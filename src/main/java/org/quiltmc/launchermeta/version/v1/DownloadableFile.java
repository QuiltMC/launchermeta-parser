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

/**
 * Some downloadable file for the game
 */
public class DownloadableFile {
    private final String sha1;
    private final int size;
    private final String url;

    public DownloadableFile(String sha1, int size, String url) {
        this.sha1 = sha1;
        this.size = size;
        this.url = url;
    }

    /**
     *
     * @return the SHA1 of the file
     */
    public String getSha1() {
        return sha1;
    }

    /**
     *
     * @return the size of the file
     */
    public int getSize() {
        return size;
    }

    /**
     *
     * @return the url of the file
     */
    public String getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DownloadableFile download = (DownloadableFile) o;
        return size == download.size && Objects.equals(sha1, download.sha1) && Objects.equals(url, download.url);
    }

    /**
     * A file that is downloaded to a specific path.
     */
    public static class PathDownload extends DownloadableFile {
        private final String path;

        public PathDownload(String sha1, int size, String url, String path) {
            super(sha1, size, url);
            this.path = path;
        }

        /**
         *
         * @return the path to download the file to
         */
        public String getPath() {
            return path;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            PathDownload that = (PathDownload) o;
            return Objects.equals(path, that.path);
        }
    }

    /**
     * A download file with a specific id.
     */
    public static class IdDownload extends DownloadableFile {
        private final String id;

        public IdDownload(String sha1, int size, String url, String id) {
            super(sha1, size, url);
            this.id = id;
        }

        /**
         *
         * @return the id for this download
         */
        public String getId() {
            return id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;
            IdDownload that = (IdDownload) o;
            return Objects.equals(id, that.id);
        }
    }
}
