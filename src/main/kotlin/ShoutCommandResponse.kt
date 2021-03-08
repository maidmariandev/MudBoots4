import sample.BroadCast
import sample.Player

class ShoutCommandResponse : CommandResponse {
    override val broadCasts: ArrayList<BroadCast> = arrayListOf();
    override fun addBroadcast(ToPlayer: Player, FromPlayer: Player, broadcast: String) {
        broadCasts.add(BroadCast(ToPlayer,broadcast))
    }

}
