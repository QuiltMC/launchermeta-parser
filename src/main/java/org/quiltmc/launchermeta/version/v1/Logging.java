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

public class Logging {
    private final LoggingInstance client;

    public Logging(LoggingInstance client) {
        this.client = client;
    }

    public LoggingInstance getClient() {
        return client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Logging logging = (Logging) o;
        return Objects.equals(client, logging.client);
    }

    public static class LoggingInstance {
        private final String argument;
        private final String type;
        private final Download.IdDownload file;

        public LoggingInstance(String argument, String type, Download.IdDownload file) {
            this.argument = argument;
            this.type = type;
            this.file = file;
        }

        public String getArgument() {
            return argument;
        }

        public String getType() {
            return type;
        }

        public Download.IdDownload getFile() {
            return file;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            LoggingInstance that = (LoggingInstance) o;
            return Objects.equals(argument, that.argument) && Objects.equals(type, that.type) && Objects.equals(file, that.file);
        }
    }
}
