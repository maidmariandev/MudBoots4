import sample.GraphExplorerBoardDirection
import sample.GraphExplorerBoardDirectionResult
import sample.Player

class ShoutCommand(private val activePlayer: Player, private val  playerMessage: PlayerMessage, private val  world: World) : CommandRequest {

    override fun handleCommand()  : CommandResponse {
        val playerGraph = world.playerGraph;
        val graphBoard = world.graphBoard;
        val playerLocationGraph = world.playerLocationGraph;

        val nearBy = graphBoard.get_range(activePlayer.ident.blockId, 1);
        val playersNearby = nearBy.translate(playerLocationGraph); //translate keys to graph edges with direction
        val commandResponse = ShoutCommandResponse()
        for (nearby_player in playersNearby) {
            commandResponse.addBroadcast(nearby_player.Item,activePlayer,"You hear a shout from the " + invertDirection(nearby_player.location));

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