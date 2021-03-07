package sample

class PlayerBroadcastHandler {
    private val broadcasts = arrayListOf<BroadCast>()
    fun getBroadCasts(): ArrayList<BroadCast> {
        synchronized(this) {
            return broadcasts;
        }
    }

    fun getBroadAndClear(): List<BroadCast> {
        synchronized(this) {
            val b2 = broadcasts.toList();
            broadcasts.clear();
            return b2;

        }
    }

    fun broadcastToPlayer(activePlayer: Player, message: String) {
        synchronized(this) {
            broadcasts.add(BroadCast(message));
        }
    }
}

class Player(override val ident: Identity) : Graphable {

    val playerBroadcastHandler: PlayerBroadcastHandler = PlayerBroadcastHandler();


}