package Graph.Zone

import Graph.Exit.Exit
import Graph.Exit.ExitGraph
import Graph.Explorer.SquareBoard.GraphExplorerBoard
import Graph.Player.Player
import Graph.Player.PlayerGraph


open class World(
    var graphBoard: GraphExplorerBoard
) {
    val playerGraph: PlayerGraph = PlayerGraph(HashMap())
    val exitGraph: ExitGraph = ExitGraph(HashMap())

    enum class ExitGraphKeys {
        ExitLocation
    }
    fun getPlayersAt(location: Int): List<Player> {
        return playerGraph.getNodesAt(location, Player.PlayerGraphKeys.Location.toString())
    }
    fun getExitsAt(location: Int): List<Exit> {
        return exitGraph.getNodesAt(location, ExitGraphKeys.ExitLocation.toString())
    }
    fun add_player_to_world(p: Player, blockId :Int) {
        playerGraph.add(p);
        p.updatePositionBlock(blockId)

    }
    fun add_player_to_world(p: Player) {
        playerGraph.add(p);

    }
    fun remove_player_from_world(p:Player){
        playerGraph.remove(p)
    }


}

