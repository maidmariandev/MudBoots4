import Graph.Graph
import Graph.Explorer.SquareBoard.GraphExplorerBoard
import Graph.GraphIdentity
import Graph.Player.PlayerGraph

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
    val  playerGraph = PlayerGraph(HashMap())
    val  inventoryGraph = Graph<Inventory>(HashMap());
     val  graphBoard = GraphExplorerBoard(12, 12);
    return TestData(playerGraph,inventoryGraph,graphBoard )
}

class TestData(
    val playerGraph: PlayerGraph,
    val inventoryGraph: Graph<Inventory>,
    val graphBoard: GraphExplorerBoard
) {

}
