import org.junit.Assert
import org.junit.Test
import sample.GraphExplorerBoard
import sample.Identity
import sample.Player

class GraphExplorerBoardTest {


    fun generate_range_test(
        squareSize: Int,
        maxRows: Int,
        startPosition: Int,
        range: Int,
        handler: (GraphExplorerBoard, Int, Int) -> ArrayList<Int>,
        expected: Array<Int>
    ) {
        val output = handler(GraphExplorerBoard(6, 3), startPosition, range)
        println("Start Position: $startPosition squareSize: $squareSize Max Rows: $maxRows Range: $range Expected: ${expected.joinToString { it -> it.toString() }}")
        draw_grid(6, 3, startPosition, output);
        Assert.assertArrayEquals(expected, output.toArray())
    }

    fun draw_grid(squareSize: Int, maxRows: Int, start: Int, moves: List<Int>) {
        for (i in 0..maxRows - 1) {
            for (j in 0..squareSize - 1) {
                val position = (maxRows * squareSize - 1) - (j + (i * squareSize))
                var position_str = position.toString()
                if (position == start) {
                    position_str = "|"
                } else if (moves.contains(position)) {
                    position_str = "x";
                }
                if (j == squareSize - 1) {
                    println(position_str);
                } else {
                    print("$position_str\t")
                }
            }
        }
        println("")
        println("")
    }

    @Test
    fun testGet_range_test() {
        val world = World()
        val activePlayer = Player(Identity(0))
        world.add_player_to_world(activePlayer)
        world.add_player_to_world(Player(Identity(1, 1)))
        world.add_player_to_world(Player(Identity(2, 6)))
        val graphBoard = world.graphBoard;
        val playerLocationGraph = world.playerLocationGraph;
        val nearBy = graphBoard.get_range(activePlayer.ident.blockId, 1);
        val playersNearby = nearBy.translate(playerLocationGraph); //translate keys to graph edges with direction

    }

    @Test
    fun testGet_above_range() {

        val handler =
            { board: GraphExplorerBoard, startPosition: Int, range: Int -> board.get_above_range(startPosition, range) }
        generate_range_test(6, 3, 5, 3, handler, arrayOf(11, 17));
        generate_range_test(6, 3, 17, 4, handler, arrayOf());
        generate_range_test(6, 3, 11, 4, handler, arrayOf(17));
        generate_range_test(6, 3, 5, 4, handler, arrayOf(11, 17));
        generate_range_test(6, 3, 12, 4, handler, arrayOf());


    }

    @Test
    fun testGet_up_right_range() {

        val handler =
            { board: GraphExplorerBoard, startPosition: Int, range: Int ->
                board.get_up_right_range(
                    startPosition,
                    range
                )
            }
        generate_range_test(6, 3, 5, 3, handler, arrayOf(10, 15));
        generate_range_test(6, 3, 1, 3, handler, arrayOf(6));

    }


    @Test
    fun testGet_down_right_range() {
        var output = GraphExplorerBoard(6, 3).get_down_right_range(17, 3);
        draw_grid(6, 3, 17, output);
        println(output);
        Assert.assertArrayEquals(output.toArray(), arrayOf(10, 3))
        output = GraphExplorerBoard(6, 3).get_down_right_range(12, 3);
        draw_grid(6, 3, 12, output);
        println(output);
        Assert.assertArrayEquals(output.toArray(), arrayOf())

    }

    @Test
    fun testGet_right_range() {
        var output = GraphExplorerBoard(6, 3).get_right_range(5, 3);
        draw_grid(6, 3, 5, output);
        Assert.assertArrayEquals(output.toArray(), arrayOf(4, 3, 2))
        output = GraphExplorerBoard(6, 3).get_right_range(5, 12);
        draw_grid(6, 3, 5, output);
        Assert.assertArrayEquals(output.toArray(), arrayOf(4, 3, 2, 1, 0))
    }

    @Test
    fun testGet_up_left_range() {
        var output = GraphExplorerBoard(6, 3).get_up_left_range(2, 3);
        draw_grid(6, 3, 2, output);
        Assert.assertArrayEquals(output.toArray(), arrayOf(9, 16))
        output = GraphExplorerBoard(6, 3).get_up_left_range(8, 13);
        draw_grid(6, 3, 8, output);
        Assert.assertArrayEquals(output.toArray(), arrayOf(15));
    }

    @Test
    fun testGet_down_left_range() {
        var output = GraphExplorerBoard(6, 3).get_down_left_range(12, 3);
        draw_grid(6, 3, 12, output);
        Assert.assertArrayEquals(output.toArray(), arrayOf(7, 2))
        output = GraphExplorerBoard(6, 3).get_down_left_range(17, 3);
        draw_grid(6, 3, 17, output);
        Assert.assertArrayEquals(output.toArray(), arrayOf())
    }

    @Test
    fun testGet_left_range() {
        var output = GraphExplorerBoard(6, 3).get_left_range(2, 3);
        draw_grid(6, 3, 2, output);
        Assert.assertArrayEquals(output.toArray(), arrayOf(3, 4, 5))
        output = GraphExplorerBoard(6, 3).get_left_range(2, 13);
        draw_grid(6, 3, 2, output);
        Assert.assertArrayEquals(output.toArray(), arrayOf(3, 4, 5));
    }

    @Test
    fun testGet_down_range() {
        val output = GraphExplorerBoard(6, 3).get_down_range(12, 3);
        draw_grid(6, 3, 12, output);
        Assert.assertArrayEquals(output.toArray(), arrayOf(6, 0))

    }

    @Test
    fun testGet_range() {
    }
}