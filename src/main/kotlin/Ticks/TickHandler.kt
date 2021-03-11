package Ticks

import Command.CommandRequest
import Command.NoSuchPlayercommandRequest
import Command.PlayerCommandType
import Command.Shout.ShoutCommand
import CommandResponse
import Graph.GraphIdentity
import World

class TickHandler(val world: World) {
    fun tick(tickQueue: List<TickMessage>) : List<CommandResponse> {
        if (tickQueue.isEmpty()) return listOf();
        return tickQueue.map { i->handleCommand(i).handleCommand() }

    }
    private fun handleCommand(message: TickMessage): CommandRequest {

        val playerGraph = world.playerGraph
        val activePlayer = playerGraph.get(GraphIdentity(message.discordID));
        if (activePlayer == null) {
            println("Can not find player");
            return NoSuchPlayercommandRequest()
        }
        return when (message.Type) {
            PlayerCommandType.Shout -> ShoutCommand(activePlayer, message, world)
            PlayerCommandType.Move -> TODO()
            PlayerCommandType.Hide -> TODO()
        }
    }


}
