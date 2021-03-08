import org.junit.Test
import sample.Identity
import sample.Player
import kotlin.test.assertEquals

class ShoutCommandTests {
    @Test
    fun testSingleShout() {
        val world = World()
        val t = TickHandler(world);
        world.add_player_to_world(Player(Identity(0)))
        world.add_player_to_world(Player(Identity(1, 1)))
        val executedCommands = t.tick(listOf(PlayerMessage(PlayerCommandType.Shout, 0, 0)))
        val cmd = executedCommands[0];
        val bcast = cmd.broadCasts[0];
        assertEquals("You hear a shout from the south", bcast.message)
        assertEquals(1, bcast.player.ident._id)
    }
    @Test
    fun testMultiShout() {
        val world = World()
        val t = TickHandler(world);
        world.add_player_to_world(Player(Identity(0)))
        world.add_player_to_world(Player(Identity(1, 1)))
        val executedCommands = t.tick(listOf(PlayerMessage(PlayerCommandType.Shout, 0, 0)))
        val cmd = executedCommands[0];
        val bcast = cmd.broadCasts[0];
        assertEquals("You hear a shout from the south", bcast.message)
        assertEquals(1, bcast.player.ident._id)
    }
}