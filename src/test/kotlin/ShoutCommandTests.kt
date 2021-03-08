import org.junit.Test
import sample.Identity
import sample.Player
import kotlin.test.assertEquals
import kotlin.test.asserter

class ShoutCommandTests{
    @Test
    fun testSingleTick() {
        val wrld = World()
        val t  = TickHandler(wrld);

        wrld.add_player_to_world(Player(Identity(0)))
        wrld.add_player_to_world(Player(Identity(1, 1)))

       val executedCommands =  t.tick(listOf(PlayerMessage(PlayerCommandType.Shout, 0, 0)))
        val cmd = executedCommands[0];
        val bcast = cmd.broadCasts[0];

        //  wrld.tick()

    }
}