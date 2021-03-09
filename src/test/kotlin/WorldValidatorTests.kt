import org.junit.Test

class WorldValidatorTests {
    @Test
    fun testEmptyTick() {
        val tstData = worldTestsGenerator();

        val world = World(tstData.
        playerLocationGraph,
            tstData. playerGraph,
            tstData. inventoryGraph,
            tstData.graphBoard )
        val t  = TickHandler(world);
        t.tick(listOf())
    }


}

