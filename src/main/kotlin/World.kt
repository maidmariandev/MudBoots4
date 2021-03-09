
class Inventory(override val who: Identity, val p1: Player) : Graphable {

}

class InventorySlot(override val who: Identity, val inventory: Inventory) : Graphable {

}


interface CommandResponse {
    val broadCasts: ArrayList<BroadCast>
    fun addBroadcast(ToPlayer: Player, FromPlayer: Player, broadcast: String);
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
        playerGraph = Graph(MutableList(8000) { i -> createList(i) }, InsertKeyForPlayers, null, null);
        inventoryGraph = Graph(MutableList(8000) { i -> createList(i) }, InsertKeyInv, null, null);
        playerLocationGraph =
            Graph(MutableList(8000) { i -> createList(i) }, InsertKeyForPlayerLocation, null, null);
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

