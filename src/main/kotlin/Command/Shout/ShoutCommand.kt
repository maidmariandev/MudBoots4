package Command.Shout

import BroadCast
import Command.CommandRequest
import CommandResponse
import Graph.Explorer.SquareBoard.GraphExplorerBoard
import Graph.Explorer.SquareBoard.GraphExplorerBoardDirection
import Graph.Player.Player
import Ticks.TickMessage
import Graph.Zone.World

class ShoutCommand(private val activePlayer: Player, private val tickMessage: TickMessage, private val world: World) :
    CommandRequest {

    override fun handleCommand(): CommandResponse {
        val playerGraph = world.playerGraph;
        val graphBoard = world.graphBoard;
        val playerLocationGraph = world.playerGraph;

        val nearBy = graphBoard.getRange(activePlayer.who.blockId, 1);

        val playersNearby = playerLocationGraph.translate(nearBy); //translate keys to graph edges with direction
        val commandResponse = ShoutCommandResponse()
        for (nearby_player in playersNearby) {
            val player = nearby_player.item
            val invertDirection = GraphExplorerBoard.invertDirection(
                nearby_player.location
            )
            player.addBroadCast(BroadCast("You hear a shout from the $invertDirection"))
        }
        return commandResponse;
    }


}