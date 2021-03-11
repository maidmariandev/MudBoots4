package WorldGen.azgaar

data class WorldProperties(
    val biome: Int,
    val culture: Int,
    val height: Int,
    val id: Int,
    val neighbors: List<Int>,
    val population: Int,
    val province: Int,
    val religion: Int,
    val state: Int,
    val type: String
)