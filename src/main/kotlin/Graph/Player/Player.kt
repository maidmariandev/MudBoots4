package Graph.Player

import BroadCast
import Graph.GraphIdentity
import Graph.GraphNode
import Graph.Graphable.BroadCastGraphable
import Graph.Graphable.Graphable
import Graph.NoSuchKeyError
import Graph.UpdateEventData
import QueueDrainer

val InsertKeyForPlayers: (obj: GraphIdentity) -> Int = { i ->
    (i._id % 8000).toInt()
}

val InsertKeyForPlayerLocation: (obj: GraphIdentity) -> Int = { i ->
    i.blockId
}


class CombatCommandCostCalculator {

}

class CombatCommand {
    val requiredActionPoints = 1
    val actionName = "<not set>"
}

open class Player(override val who: GraphIdentity) : IPlayer, BroadCastGraphable {
    override val broadCasts = QueueDrainer<BroadCast>()
    override val battleCommands = QueueDrainer<CombatCommand>()
    override val eventHandler by lazy { PlayerEventHandler(this) }
    override val playerStats by lazy { PlayerStats(this) }
    override fun getKey(KeyName: String): Int {
        val Key = PlayerGraphKeys.valueOf(KeyName)
        return getKey(Key)

    }

    enum class PlayerUpdateReasons {
        CommandMove
    }

    enum class PlayerGraphKeys {
        Location,
        LookUp,
        ActiveBroadCast
    }
    fun updateHasBroadCasts(){
        keyChanges(Player.PlayerGraphKeys.ActiveBroadCast.toString(), -2, "Broadcast Dispatched",true)
    }

    fun getKey(Key: PlayerGraphKeys): Int {
        return when (Key) {
            PlayerGraphKeys.Location -> InsertKeyForPlayerLocation(who)
            PlayerGraphKeys.LookUp -> InsertKeyForPlayers(who)
            PlayerGraphKeys.ActiveBroadCast -> broadCasts.dirty()
        }
    }

    fun updatePositionBlock(newBlockId: Int) {
        val OldBlock = who.blockId
        who.Move(newBlockId)
        keyChanges(PlayerGraphKeys.Location.toString(), OldBlock, PlayerUpdateReasons.CommandMove.toString());
    }

    override fun keyChanges(KeyName: String, oldVal: Int, Reason: String, IgnoreDupes : Boolean): Int {
        when (PlayerGraphKeys.valueOf(KeyName)) {
            PlayerGraphKeys.Location ->
                getallCallBacks().forEach {
                    it(UpdateEventData(KeyName, oldVal, InsertKeyForPlayerLocation(who), Reason,IgnoreDupes))
                }
            PlayerGraphKeys.LookUp -> TODO()
        }

        throw NoSuchKeyError() //this should only ever get called if a coder oppsyied
    }

    override val callbacks: ArrayList<Pair<Int, (updateEventData: UpdateEventData) -> Int>> = arrayListOf()

    override fun onGraphableLeavesBucket(item: Graphable, updateEventData: UpdateEventData) {

        when (PlayerGraphKeys.valueOf(updateEventData.KeyName)) {
            PlayerGraphKeys.Location -> {
                val player = item as Player
                val Reason: PlayerUpdateReasons = PlayerUpdateReasons.valueOf(updateEventData.Extra);
                when (Reason) {
                    PlayerUpdateReasons.CommandMove -> {

                        addBroadCast(BroadCast("""${player.who._id} Leaves"""))

                    }
                }
            }
            PlayerGraphKeys.LookUp -> TODO() //notify object it was looked up, probably no-op
            PlayerGraphKeys.ActiveBroadCast -> TODO() //Notify object that broadcasts were sent.
        }
        TODO("Not yet implemented")
    }

    override fun onGraphableJoinsBucket(item: Graphable, updateEventData: UpdateEventData) {

        when (PlayerGraphKeys.valueOf(updateEventData.KeyName)) {
            PlayerGraphKeys.Location -> {
                val Reason: PlayerUpdateReasons = PlayerUpdateReasons.valueOf(updateEventData.Extra);
                val player = item as Player
                when (Reason) {
                    PlayerUpdateReasons.CommandMove -> {
                        eventHandler.onOtherPlayerEntersTitle(player, updateEventData);

                    }
                }
            }
            PlayerGraphKeys.LookUp -> TODO() //notify object it was looked up, probably no-op
            PlayerGraphKeys.ActiveBroadCast -> TODO() //Notify object that broadcasts were sent.
        }

        TODO("Not yet implemented")
    }

    override fun onGraphableJoinsBucket(updateEventData: UpdateEventData, bucket: GraphNode<Graphable>) {
        when (PlayerGraphKeys.valueOf(updateEventData.KeyName)) {
            PlayerGraphKeys.Location -> {
                val Reason: PlayerUpdateReasons = PlayerUpdateReasons.valueOf(updateEventData.Extra);

                when (Reason) {
                    PlayerUpdateReasons.CommandMove -> {
                        eventHandler.onPlayerMovesToTile(bucket, updateEventData)
                    }
                }
            }
            PlayerGraphKeys.LookUp -> TODO() //notify object it was looked up, probably no-op
            PlayerGraphKeys.ActiveBroadCast -> TODO() //Notify object that broadcasts were sent.
        }


    }

    override fun onGraphableLeavesBucket(updateEventData: UpdateEventData, bucket: GraphNode<Graphable>) {
        when (PlayerGraphKeys.valueOf(updateEventData.KeyName)) {
            PlayerGraphKeys.Location -> {
                var oldPlayersInRoom: GraphNode<Player> = bucket as GraphNode<Player>
                //you leave a since fart cloud as you leave
            }
            PlayerGraphKeys.LookUp -> TODO() //notify object it was looked up, probably no-op
            PlayerGraphKeys.ActiveBroadCast -> TODO() //Notify object that broadcasts were sent.
        }

    }

    fun addBroadCast(broadCast: BroadCast) {
        broadCasts.addBroadCast(broadCast)
        updateHasBroadCasts() // so all we have to do is look in the dirty bucket
    }
}


