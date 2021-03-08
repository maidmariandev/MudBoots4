import org.junit.Test

class WorldValidatorTests {
    @Test
    fun testEmptyTick() {
        val wrld: World = World()
        val t  = TickHandler(wrld);
        t.tick(listOf())
    }


}

