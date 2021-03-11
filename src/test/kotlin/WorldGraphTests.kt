import Graph.GraphIdentity
import Graph.Player.Player
import org.junit.Test

import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class WorldGraphTests{
    @Test
    fun testGetPlayerByDiscordId(){
        val tstData = worldTestsGenerator();

        val world = World(tstData.
        playerLocationGraph,
            tstData. playerGraph,
            tstData. inventoryGraph,
            tstData.graphBoard )
        world.add_player_to_world(Player(GraphIdentity(0)))
        world.add_player_to_world(Player(GraphIdentity(1, 1)))
        world.add_player_to_world(Player(GraphIdentity(2, 6)))

        val playerGraph = world.playerGraph
        val activePlayer = playerGraph.get(GraphIdentity(0));
        assertNotNull(activePlayer);
        assertEquals(activePlayer.who._id ,0);


        val activePlayer2 = playerGraph.get(GraphIdentity(1));
        assertNotNull(activePlayer2);
        assertEquals(activePlayer2.who._id ,1);


        val activePlayer3 = playerGraph.get(GraphIdentity(2));
        assertNotNull(activePlayer3);
        assertEquals(activePlayer3.who._id ,2);

        val activeplayerNul  = playerGraph.get(GraphIdentity(12));
        assertNull(activeplayerNul);
    }
}