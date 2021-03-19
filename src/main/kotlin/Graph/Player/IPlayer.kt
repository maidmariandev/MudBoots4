package Graph.Player

import BroadCast
import QueueDrainer

interface IPlayer {
    val broadCasts: QueueDrainer<BroadCast>
    val battleCommands: QueueDrainer<CombatCommand>
    val eventHandler: PlayerEventHandler
    val playerStats: PlayerStats
}