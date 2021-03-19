package Graph.Explorer.SquareBoard

import Graph.Graph
import Graph.Graphable.Graphable

class GraphExplorerBoardDirectionList(
    private val above: List<Int>,
    private val top_right: List<Int>,
    private val right: List<Int>,
    private val down_right: List<Int>,
    private val below: List<Int>,
    private val down_left: List<Int>,
    private val left: List<Int>,
    private val top_left: List<Int>
) {
    fun <T : Graphable> translate(locGraph: Graph<T>, graphKey: String): List<GraphExplorerBoardDirectionResult<T>> {
        val lst = arrayListOf<GraphExplorerBoardDirectionResult<T>>()
        addItemsFromNodeToList(above, locGraph, lst, GraphExplorerBoardDirection.Up, graphKey)
        addItemsFromNodeToList(top_right, locGraph, lst, GraphExplorerBoardDirection.UpRight, graphKey)
        addItemsFromNodeToList(right, locGraph, lst, GraphExplorerBoardDirection.Right, graphKey)
        addItemsFromNodeToList(down_right, locGraph, lst, GraphExplorerBoardDirection.DownRight, graphKey)
        addItemsFromNodeToList(below, locGraph, lst, GraphExplorerBoardDirection.Down, graphKey)
        addItemsFromNodeToList(down_left, locGraph, lst, GraphExplorerBoardDirection.DownLeft, graphKey)
        addItemsFromNodeToList(left, locGraph, lst, GraphExplorerBoardDirection.Left, graphKey)
        addItemsFromNodeToList(top_left, locGraph, lst, GraphExplorerBoardDirection.UpLeft, graphKey)
        return lst;
    }

    private fun <T : Graphable> addItemsFromNodeToList(
        src: List<Int>,
        locGraph: Graph<T>,
        lst: ArrayList<GraphExplorerBoardDirectionResult<T>>,
        direction: GraphExplorerBoardDirection,
        graphKey: String
    ) {
        for (i in src) {
            val players = locGraph.getNodesAt(i, graphKey);

            for (j in players) {
                lst.add(GraphExplorerBoardDirectionResult(direction, j))
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

class GraphExplorerBoardDirectionResult<T : Graphable>(val location: GraphExplorerBoardDirection, val item: T) {

}
