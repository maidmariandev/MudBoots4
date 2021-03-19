package Graph.Player

import BroadCast
import Graph.Player.BroadCasts.NotEnoughActionPointsBroadCast

class PlayerCombatHandler(val player: Player) {

    fun combatHandler(Combatants: List<Player>) {
        if (Combatants.isEmpty()) return;
        with(player) {
            val commandsPerTurn = playerStats.playerCombatStats.commandsPerTurn

            var takeSize = commandsPerTurn;
            if (Combatants.size < commandsPerTurn) {
                takeSize = Combatants.size;
            }
            val cmdlist = battleCommands.drainBroadCasts(commandsPerTurn);
            if (cmdlist.isEmpty()) return;
            for ((index, cmd) in cmdlist.withIndex()) {
                if (playerStats.playerCombatStats.remainingActionPoints > cmd.requiredActionPoints) {
                    addBroadCast(NotEnoughActionPointsBroadCast(cmd.actionName))

                    continue;
                }
                tradeBlows(cmd, Combatants[index]);
            }

        }
    }

    private fun tradeBlows(cmd: CombatCommand, otherplayer: Player) {

        otherplayer.playerStats.applyCombatCommand(cmd)
        val formatString =
            "${player.who._id}'s weak attack tickles ${player.who._id}" // cmd.formatBroadCast(player,otherplayer);
        player.addBroadCast(BroadCast(formatString))
        otherplayer.addBroadCast(BroadCast(formatString))

    }

}