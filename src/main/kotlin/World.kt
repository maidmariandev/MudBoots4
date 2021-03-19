import Graph.Explorer.SquareBoard.GraphExplorerBoard
import Graph.Graph
import Graph.Player.Player
import Graph.PlayerGraph


class World(
    var playerGraph: PlayerGraph,
    private var inventoryGraph: Graph<Inventory>,
    var graphBoard: GraphExplorerBoard
) {


    fun add_player_to_world(p: Player) {
        playerGraph.add (p, Player.PlayerGraphKeys.LookUp);

    }


}

