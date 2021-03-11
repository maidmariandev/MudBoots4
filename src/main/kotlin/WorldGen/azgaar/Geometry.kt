package WorldGen.azgaar

import kotlin.math.roundToInt


data class Geometry(
    val coordinates: List<List<List<Double>>>,
    val type: String

) {

}

class GeomTranslator(
    private val minX: Double,
    private val minY: Double,
    private val maxX: Double,
    private val maxY: Double,
    private val widthCells: Int,
    private val heightCells: Int
) {
    val widthChunk = (maxX - minX) / widthCells
    val heightChunk = (maxY - minY) / heightCells
    fun TranslateToSquare(x:Int, y:Int): Int {
        var cell :Int =  ((x- minX)/ widthChunk).roundToInt();
        cell +=  ((y- minY)/ widthChunk).roundToInt() * heightCells;
        return 0;
    }
}