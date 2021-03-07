import sample.*
import java.lang.Error

class Inventory(override val ident: Identity, val p1: Player) : Graphable {

}

class InventorySlot(override val ident: Identity, val inventory: Inventory) : Graphable {

}

var IdCounter = 0;

@Synchronized
fun get_next_global_id(): Int {

    return IdCounter++;
}

interface CommandRequest {
    fun handle_command(active_player: Player, message: PlayerMessage, world: World)  : CommandRequest;
}
class CommandResult(activePlayer: Player, nearbyPlayer: GraphExplorerBoardDirectionResult<Player>) {

}
class ShoutCommand : CommandRequest {
    override fun handle_command(active_player: Player, message: PlayerMessage, world: World)  : List<CommandRequest>{
        val playerGraph = world.playerGraph;
        val graphBoard = world.graphBoard;
        val playerLocationGraph = world.playerLocationGraph;

        val nearBy = graphBoard.get_range(active_player.ident.blockId, 1);
        val players_nearby = nearBy.translate(playerLocationGraph); //translate keys to graph edges with direction

        for (nearby_player in players_nearby) {
            when (nearby_player.location) {
                GraphExplorerBoardDirection.Up -> broadcastShoutUp(active_player, nearby_player, message);
                GraphExplorerBoardDirection.Down -> TODO()
                GraphExplorerBoardDirection.UpRight -> TODO()
                GraphExplorerBoardDirection.Right -> TODO()
                GraphExplorerBoardDirection.Left -> TODO()
                GraphExplorerBoardDirection.DownLeft -> TODO()
                GraphExplorerBoardDirection.DownRight -> TODO()
                GraphExplorerBoardDirection.UpLeft -> TODO()
            }
        }
    }

    private fun broadcastShoutUp(
        activePlayer: Player,
        nearbyPlayer: GraphExplorerBoardDirectionResult<Player>,
        message: PlayerMessage
    ) : CommandResult{
        nearbyPlayer.Item.playerBroadcastHandler.broadcastShoutFromBelow(activePlayer,message.Message)
        return CommandResult(activePlayer,nearbyPlayer);

    }

}

class TickHandler(val world: World) {
    fun tick(tickQueue: ArrayList<PlayerMessage>) {
        if (tickQueue.size == 0) return;

        val commands_by_time = tickQueue.sortedBy { a -> a.commandTimestamp }
        for (command in commands_by_time) {
            handleCommand(command)
        }
    }

    private fun handleCommand(message: PlayerMessage) : CommandRequest{

        val playerGraph = world.playerGraph
        val active_player = playerGraph.get(Identity(message.discordID));
        if (active_player == null) {
            println("Can not find player");
            throw Error("Player could not be found")
        }
        when (message.Type) {
            PlayerCommandType.Shout -> ShoutCommand(active_player, message)
            PlayerCommandType.Move -> TODO()
            PlayerCommandType.Hide -> TODO()
        }
    }



}

class World() {
    val playerLocationGraph: Graph<Player>
    val playerGraph: Graph<Player>
    private val inventoryGraph: Graph<Inventory>
    val graphBoard: GraphExplorerBoard;

    init {
        val InsertKeyForPlayers: (obj: Identity) -> Int = { i ->
            (i._id % 8000).toInt()
        }
        val InsertKeyInv: (obj: Identity) -> Int = { i ->
            (i._id % 8000).toInt()
        }
        val InsertKeyForPlayerLocation: (obj: Identity) -> Int = { i ->
            i.blockId
        }
        playerGraph = Graph(MutableList(8000) { i -> create_list(i) }, InsertKeyForPlayers, null, null);
        inventoryGraph = Graph(MutableList(8000) { i -> create_list(i) }, InsertKeyInv, null, null);
        playerLocationGraph =
            Graph(MutableList(8000) { i -> create_list(i) }, InsertKeyForPlayerLocation, null, null);
        graphBoard = GraphExplorerBoard(12, 12);
    }


    fun add_player_to_world(p: Player) {
        playerGraph.add(p);
        playerLocationGraph.add(p);

    }


    fun create_player() {
        val discordid = 445251792226484235
        val p1 = Player(Identity(discordid));
        val p1Inventory = Inventory(Identity(discordid), p1);
        playerGraph.add(p1);
        inventoryGraph.add(p1Inventory);
    }
}

