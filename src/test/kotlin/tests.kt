import Graph.Graph
import Graph.Explorer.SquareBoard.GraphExplorerBoard
import Graph.GraphIdentity
import Graph.Player.Player

class test{}


fun worldTestsGenerator(): TestData {
    val InsertKeyForPlayers: (obj: GraphIdentity) -> Int = { i ->
        (i._id % 8000).toInt()
    }
    val InsertKeyInv: (obj: GraphIdentity) -> Int = { i ->
        (i._id % 8000).toInt()
    }
    val InsertKeyForPlayerLocation: (obj: GraphIdentity) -> Int = { i ->
        i.blockId
    }
    val  playerGraph = Graph<Player>(MutableList(8000) { i -> createList(i) }, InsertKeyForPlayers, null, null);
    val  inventoryGraph = Graph<Inventory>(MutableList(8000) { i -> createList(i) }, InsertKeyInv, null, null);
    val  playerLocationGraph =
        Graph<Player>(MutableList(8000) { i -> createList(i) }, InsertKeyForPlayerLocation, null, null);
    val  graphBoard = GraphExplorerBoard(12, 12);
    return TestData(playerGraph,inventoryGraph,graphBoard,playerLocationGraph)
}

class TestData(
    val playerGraph: Graph<Player>,
    val inventoryGraph: Graph<Inventory>,
    val graphBoard: GraphExplorerBoard,
    val playerLocationGraph: Graph<Player>
) {

}
