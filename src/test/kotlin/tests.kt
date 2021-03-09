class test{}


fun worldTestsGenerator(): TestData {
    val InsertKeyForPlayers: (obj: Identity) -> Int = { i ->
        (i._id % 8000).toInt()
    }
    val InsertKeyInv: (obj: Identity) -> Int = { i ->
        (i._id % 8000).toInt()
    }
    val InsertKeyForPlayerLocation: (obj: Identity) -> Int = { i ->
        i.blockId
    }
    val  playerGraph = Graph(MutableList(8000) { i -> createList(i) }, InsertKeyForPlayers, null, null);
    val  inventoryGraph = Graph(MutableList(8000) { i -> createList(i) }, InsertKeyInv, null, null);
    val  playerLocationGraph =
        Graph(MutableList(8000) { i -> createList(i) }, InsertKeyForPlayerLocation, null, null);
    val  graphBoard = GraphExplorerBoard(12, 12);
    return TestData(playerGraph,inventoryGraph,graphBoard,playerLocationGraph)
}

class TestData(
    val playerGraph: Graph<Graphable>,
    val inventoryGraph: Graph<Graphable>,
    val graphBoard: GraphExplorerBoard,
    val playerLocationGraph: Graph<Graphable>
) {

}
