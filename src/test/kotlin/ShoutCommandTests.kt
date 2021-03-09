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
        assertEquals(executedCommands.size,1)
        val cmd = executedCommands[0];
        assertEquals(cmd.broadCasts.size,1)
        val beast = cmd.broadCasts[0];
        assertEquals("You hear a shout from the east", beast.message)
        assertEquals(1, beast.player.who._id)
    }
    @Test
    fun testMultiShout() {
        val world = World()
        val t = TickHandler(world);
        world.add_player_to_world(Player(Identity(0)))
        world.add_player_to_world(Player(Identity(1, 1)))
        world.add_player_to_world(Player(Identity(2, 12)))
        val playerMessage = PlayerMessage(PlayerCommandType.Shout, 0, 0)
        val executedCommands = t.tick(listOf(playerMessage))
        assertEquals(executedCommands.size,1)
        val cmd = executedCommands[0];
        assertEquals(cmd.broadCasts.size,2)
        val bcast = cmd.broadCasts[0];
        assertEquals("You hear a shout from the south", bcast.message)
        assertEquals(2, bcast.player.who._id)
        val bcast2 = cmd.broadCasts[1];
        assertEquals("You hear a shout from the east", bcast2.message)
        assertEquals(1, bcast2.player.who._id)

    }
}