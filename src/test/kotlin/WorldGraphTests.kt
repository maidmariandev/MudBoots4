import Graph.Explorer.SquareBoard.GraphExplorerBoard
import Graph.GraphIdentity
import Graph.Player.InsertKeyForPlayers
import Graph.Player.Player
import Graph.Zone.World
import org.junit.Test

import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull


class WorldGraphTests{
    @Test
    fun testGetPlayerByDiscordId(){


        val world = World(GraphExplorerBoard(6,6,) )
        val player1Identy = GraphIdentity(0)
        val p = Player(player1Identy)
        world.add_player_to_world(p)
        val player2Ident = GraphIdentity(1, 1)
        world.add_player_to_world(Player(player2Ident))
        val player3Ident = GraphIdentity(2, 6)
        world.add_player_to_world(Player(player3Ident))

        val playerGraph = world.playerGraph
        val LookUpKey = Player.PlayerGraphKeys.LookUp;

        val activePlayer = playerGraph.get(player1Identy,LookUpKey.toString(), InsertKeyForPlayers(player1Identy));
        assertNotNull(activePlayer);
        assertEquals(activePlayer.who._id ,0);


        val activePlayer2 = playerGraph.getByIdentity(GraphIdentity(1));
        assertNotNull(activePlayer2);
        assertEquals(activePlayer2.who._id ,1);


        val activePlayer3 = playerGraph.getByIdentity(GraphIdentity(2));
        assertNotNull(activePlayer3);
        assertEquals(activePlayer3.who._id ,2);

        val activeplayerNul  = playerGraph.getByIdentity(GraphIdentity(12));
        assertNull(activeplayerNul);
    }
}