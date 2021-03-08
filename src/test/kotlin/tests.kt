import org.junit.Assert
import org.junit.Test
import sample.GraphExplorerBoard



class GraphExplorerBoardTest {
    @Test
    fun testGet_above_range() {
        var output = GraphExplorerBoard(6, 3).get_above_range(5, 3);
        draw_grid(6, 3, 5, output);
        Assert.assertArrayEquals(output.toArray(), arrayOf(11, 17))
        output = GraphExplorerBoard(6, 23).get_above_range(0, 4);

        Assert.assertArrayEquals(output.toArray(), arrayOf(6, 12, 18, 24))
        output = GraphExplorerBoard(6, 3).get_above_range(0, 4);
        Assert.assertArrayEquals(output.toArray(), arrayOf(6, 12))
    }

    @Test
    fun testGet_up_right_range() {
        var output = GraphExplorerBoard(6, 3).get_up_right_range(5, 3);
       // draw_grid(6, 3, 5, output);

        Assert.assertArrayEquals(output.toArray(), arrayOf(10, 15))
        output = GraphExplorerBoard(6, 3).get_up_right_range(1, 3);
        draw_grid(6, 3, 1, output);
        Assert.assertArrayEquals(output.toArray(), arrayOf(6))
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
        Assert.assertArrayEquals(output.toArray(), arrayOf(9,16))
        output = GraphExplorerBoard(6, 3).get_up_left_range(8, 13);
        draw_grid(6, 3, 8, output);
        Assert.assertArrayEquals(output.toArray(), arrayOf(15));
    }
    @Test
    fun testGet_down_left_range() {
        var output = GraphExplorerBoard(6, 3).get_down_left_range(12, 3);
        draw_grid(6, 3, 12, output);
        Assert.assertArrayEquals(output.toArray(), arrayOf(7,2))
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
