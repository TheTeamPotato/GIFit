rootProject.name = "GIFit"
include(":app")
include(":features:favorites", ":features:search", ":features:splash", ":features:history")
include(":layers:data", ":layers:domain", ":layers:ui")
include(":testing")
include(":translate")