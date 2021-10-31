# LauncherMeta Parser

This project provides an easy way to convert the launcher meta files that Mojang provides into Java Objects for use in
projects. Some use cases for this could be generating a list of usable versions, downloading game jars, or checking for
library updates.

This library has a focus around not returning null in any situation, and will wrap all null values in an Optional. This
library has been tested on all versions up to 21w42a, and no null value are returned.

## Licensing

LauncherMeta Parser is available under the Apache 2.0 license.