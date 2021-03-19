package Graph.Player

import BroadCast
import Graph.GraphNode
import Graph.Graphable.Graphable
import Graph.UpdateEventData

class PlayerEventHandler(val player: Player) {

    fun onPlayerDeath(){

    }

    fun onPlayerEntersCombat(otherPlayers: List<Player>) {
        val formatString = otherPlayers.joinToString { it.who._id.toString() }
        with(player) {
            broadCasts.addBroadCast(BroadCast("$formatString begin their attack."))
        }
    }

    fun onPlayerLeavesCombat(otherPlayers: List<Player>,combatResultFlags :CombatResultFlag) {
        with(player) {
            broadCasts.addBroadCast(BroadCast("You escape combat."))
        }
    }

    fun onOtherPlayerEntersTitle(otherPlayer: Player, eventData: UpdateEventData) {
        with(player) {
            broadCasts.addBroadCast(BroadCast("${player.who._id} enters the room, stinking of a fresh kill."))
        }
    }

    fun onPlayerLeavesTile(otherPlayer: Player, eventData: UpdateEventData) {
        with(player) {
            broadCasts.addBroadCast(BroadCast("${player.who._id} leaves the room."))
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