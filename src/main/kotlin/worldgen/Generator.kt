package worldgen

import Graph
import Identity
import createList
import kotlin.random.Random

interface Generator {
    val width: Int
    val height: Int
    val maxrow: Int get() = (height * width) - 1

}

class MazeGenerator(override val width: Int, override val height: Int) : Generator {
    val random = Random(234234)
    private val startSquare: Int = random.nextInt(maxrow);

    fun generate(exits: Int) {
        if (exits < 0) {
            println("Exits can not be less than 0");
            return;
        }
        if(exits > maxrow){
            println("every square can not be an exit. ");
            return;
        }

        val insertkey: (obj: Identity) -> Int = { i ->
            i.blockId
        }
        val mazeGraph =
            Graph<MazeSquare>(MutableList(8000) { i -> createList(i) }, insertkey, null, null);


    }


}