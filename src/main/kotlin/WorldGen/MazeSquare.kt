package WorldGen

import Graph.Graph
import Graph.Graphable
import Graph.GraphIdentity


class MazeSquare(override val who: GraphIdentity, private val maze: Graph<MazeSquare>) : Graphable {



    fun addExitUp() {
        TODO("Not yet implemented")
    }

    fun addexitRight() {
        TODO("Not yet implemented")
    }

    fun addExitDown() {
        TODO("Not yet implemented")
    }

    fun addExitLeft() {
        TODO("Not yet implemented")
    }

    fun canGoDown(): Boolean {
        TODO("Not yet implemented")
    }

    fun canGoUp(): Boolean {
        TODO("Not yet implemented")
    }

    fun canGoLeft(): Boolean {
        TODO("Not yet implemented")
    }

    fun canGoRight(): Boolean {
        TODO("Not yet implemented")
    }
}
