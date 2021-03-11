package WorldGen.azgaar.rivers

import WorldGen.azgaar.Feature

data class azFarRiver(
    val features: List<Feature<RiverProperties>>,
    val type: String
)