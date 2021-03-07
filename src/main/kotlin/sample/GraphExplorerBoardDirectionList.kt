package sample

import java.util.ArrayList

class GraphExplorerBoardDirectionList(
    val above: ArrayList<Int>,
    val top_right: ArrayList<Int>,
    val right: ArrayList<Int>,
    val down_right: ArrayList<Int>,
    val below: ArrayList<Int>,
    val down_left: ArrayList<Int>,
    val left: ArrayList<Int>,
    val top_left: ArrayList<Int>
) {
    fun <T : Graphable> translate(locGraph: Graph<T>): List<GraphExplorerBoardDirectionResult<T>> {
        val lst = mutableListOf<GraphExplorerBoardDirectionResult<T>>()
        addItemsFromNodeToList(above, locGraph, lst)
        addItemsFromNodeToList(top_right, locGraph, lst)
        addItemsFromNodeToList(right, locGraph, lst)
        addItemsFromNodeToList(down_right, locGraph, lst)
        addItemsFromNodeToList(below, locGraph, lst)
        addItemsFromNodeToList(down_left, locGraph, lst)
        addItemsFromNodeToList(left, locGraph, lst)
        addItemsFromNodeToList(top_left, locGraph, lst)
       return lst;
    }

    private fun <T : Graphable> addItemsFromNodeToList(
        src: ArrayList<Int>,
        locGraph: Graph<T>,
        lst: MutableList<GraphExplorerBoardDirectionResult<T>>
    ) {
        for (i in src) {
            val players = locGraph.get_nodes_at(i);
            for (j in players) {
                lst.add(GraphExplorerBoardDirectionResult(GraphExplorerBoardDirection.Up, j))
            }
        }
    }

}

enum class GraphExplorerBoardDirection {
    Up,
    Down,
    UpRight,
    Right,
    Left,
    DownLeft,
    DownRight,
    UpLeft
}

class GraphExplorerBoardDirectionResult<T : Graphable>(val location: GraphExplorerBoardDirection, val Item: T) {

}
