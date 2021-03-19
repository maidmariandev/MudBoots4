package Graph.Player

import BroadCast
import Graph.GraphNode
import Graph.Graphable
import Graph.UpdateEventData

class PlayerEventHandler(val player: Player) {

    fun onOtherPlayerEntersTitle(otherPlayer: Player, eventData: UpdateEventData) {
        with(player) {
            broadCasts.addBroadCast(BroadCast("${player.who._id} enters the room, stinking of a fresh kill."))
        }
    }

    fun onPlayerMovesToTile(bucket: GraphNode<Graphable>, eventData: UpdateEventData) {
        val playersInRoom = bucket.Edges
        val formatString = playersInRoom.joinToString { it.who._id.toString() }
        with(player) {
            broadCasts.addBroadCast(BroadCast("You See $formatString"))
        }
    }
}