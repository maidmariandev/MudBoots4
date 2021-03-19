import Graph.Explorer.SquareBoard.GraphExplorerBoard
import Graph.GraphIdentity
import Graph.Player.Player
import Graph.Zone.World
import org.junit.Assert
import org.junit.Test


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
    fun testGet_above_range() {

        val handler =
            { board: GraphExplorerBoard, startPosition: Int, range: Int -> board.getAboveRange(startPosition, range) }
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
                board.getUpRightRange(
                    startPosition,
                    range
                )
            }
        generate_range_test(6, 3, 5, 3, handler, arrayOf(10, 15));
        generate_range_test(6, 3, 1, 3, handler, arrayOf(6));

    }


    @Test
    fun testGet_down_right_range() {


    }

    @Test
    fun testGet_right_range() {

    }

    @Test
    fun testGet_up_left_range() {

    }

    @Test
    fun testGet_down_left_range() {

    }

    @Test
    fun testGet_left_range() {

    }

    @Test
    fun testGet_down_range() {


    }

    @Test
    fun testGet_range() {
    }
}