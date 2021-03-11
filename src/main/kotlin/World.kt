import Graph.Graph
import Graph.GraphExplorerBoard
import Graph.Graphable
import Graph.Identity
import Graph.Player.Player

class Inventory(override val who: Identity, val p1: Player) : Graphable {

}

class InventorySlot(override val who: Identity, val inventory: Inventory) : Graphable {

}


interface CommandResponse {
    val broadCasts: ArrayList<BroadCast>
    fun addBroadcast(ToPlayer: Player, FromPlayer: Player, broadcast: String);
}


class World(var playerLocationGraph: Graph<Player>,
            var playerGraph: Graph<Player>,
            private var inventoryGraph: Graph<Inventory>,
            var graphBoard: GraphExplorerBoard
) {
//these will have to be moved later but this is easier for tests -- default sample world
    init {

    }


    fun add_player_to_world(p: Player) {
        playerGraph.add(p);
        playerLocationGraph.add(p);

    }


    fun create_player() {
        val discordid = 445251792226484235
        val p1 = Player(Identity(discordid));
        val p1Inventory = Inventory(Identity(discordid), p1)
        playerGraph.add(p1);
        inventoryGraph.add(p1Inventory);
    }
}

