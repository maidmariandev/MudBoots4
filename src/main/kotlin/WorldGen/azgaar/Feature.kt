package WorldGen.azgaar

data class Feature<T> (
    val geometry: Geometry,
    val properties: T,
    val type: String
)