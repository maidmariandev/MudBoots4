import Graph.Player.Player

interface CommandResponse {
    val broadCasts: ArrayList<BroadCast>
    fun addBroadcast(ToPlayer: Player, FromPlayer: Player, broadcast: String);
}