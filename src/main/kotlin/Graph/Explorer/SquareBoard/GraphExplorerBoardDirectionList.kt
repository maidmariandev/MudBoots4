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
    fun <T : Graphable> translate(locGraph: Graph<T>): List<GraphExplorerBoardDirectionResult<T>> {
        val lst = mutableListOf<GraphExplorerBoardDirectionResult<T>>()
        addItemsFromNodeToList(above, locGraph, lst, GraphExplorerBoardDirection.Up)
        addItemsFromNodeToList(top_right, locGraph, lst, GraphExplorerBoardDirection.UpRight)
        addItemsFromNodeToList(right, locGraph, lst, GraphExplorerBoardDirection.Right)
        addItemsFromNodeToList(down_right, locGraph, lst, GraphExplorerBoardDirection.DownRight)
        addItemsFromNodeToList(below, locGraph, lst, GraphExplorerBoardDirection.Down)
        addItemsFromNodeToList(down_left, locGraph, lst, GraphExplorerBoardDirection.DownLeft)
        addItemsFromNodeToList(left, locGraph, lst, GraphExplorerBoardDirection.Left)
        addItemsFromNodeToList(top_left, locGraph, lst, GraphExplorerBoardDirection.UpLeft)
       return lst;
    }

    private fun <T : Graphable> addItemsFromNodeToList(
        src: List<Int>,
        locGraph: Graph<T>,
        lst: MutableList<GraphExplorerBoardDirectionResult<T>>,
        direction: GraphExplorerBoardDirection
    ) {
        for (i in src) {
            val players = locGraph.getNodesAt(i);

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
