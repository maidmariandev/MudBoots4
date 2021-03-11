package Command.Shout

import Command.CommandRequest
import CommandResponse
import Graph.Explorer.SquareBoard.GraphExplorerBoardDirection
import Graph.Player.Player
import Ticks.TickMessage
import World

class ShoutCommand(private val activePlayer: Player, private val  tickMessage: TickMessage, private val  world: World) :
    CommandRequest {

    override fun handleCommand()  : CommandResponse {
        val playerGraph = world.playerGraph;
        val graphBoard = world.graphBoard;
        val playerLocationGraph = world.playerLocationGraph;

        val nearBy = graphBoard.getRange(activePlayer.who.blockId, 1);

        val playersNearby = nearBy.translate(playerLocationGraph); //translate keys to graph edges with direction
        val commandResponse = ShoutCommandResponse()
        for (nearby_player in playersNearby) {
            commandResponse.addBroadcast(nearby_player.item,activePlayer,"You hear a shout from the " + invertDirection(nearby_player.location));

        }
        return commandResponse;
    }

    private fun invertDirection(location: GraphExplorerBoardDirection): String {
        return when(location){
            GraphExplorerBoardDirection.Up -> "south"
            GraphExplorerBoardDirection.Down -> "north"
            GraphExplorerBoardDirection.UpRight -> "southwest"
            GraphExplorerBoardDirection.Right -> "west"
            GraphExplorerBoardDirection.Left -> "east"
            GraphExplorerBoardDirection.DownLeft -> "northeast"
            GraphExplorerBoardDirection.DownRight -> "northwest"
            GraphExplorerBoardDirection.UpLeft -> "southeast"
        }

    }

}