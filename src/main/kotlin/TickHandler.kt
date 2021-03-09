
class TickHandler(val world: World) {
    fun tick(tickQueue: List<PlayerMessage>) : List<CommandResponse> {
        if (tickQueue.isEmpty()) return listOf();
        return tickQueue.map { i->handleCommand(i).handleCommand() }

    }
    private fun handleCommand(message: PlayerMessage): CommandRequest {

        val playerGraph = world.playerGraph
        val activePlayer = playerGraph.get(Identity(message.discordID));
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
