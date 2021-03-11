package WorldGen.azgaar.markers

import WorldGen.azgaar.Feature

data class azfartMarker(
    val features: List<Feature<MarkerProperties>>,
    val type: String
)