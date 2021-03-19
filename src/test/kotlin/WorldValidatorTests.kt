import Graph.Explorer.SquareBoard.GraphExplorerBoard
import Graph.Zone.World
import Ticks.TickHandler
import org.junit.Test

class WorldValidatorTests {
    @Test
    fun testEmptyTick() {


        val world = World(GraphExplorerBoard(6,6,) )
        val t  = TickHandler(world);
        t.tick(listOf())
    }


}

