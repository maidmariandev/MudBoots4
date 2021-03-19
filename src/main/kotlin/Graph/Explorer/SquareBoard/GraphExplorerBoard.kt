package Graph.Explorer.SquareBoard

class GraphExplorerBoard(val squareSize: Int, val maxRows: Int) {
    companion object {
        fun invertDirection(location: GraphExplorerBoardDirection): String {
            return when (location) {
                GraphExplorerBoardDirection.Up -> "south"
                GraphExplorerBoardDirection.Down -> "north"
                GraphExplorerBoardDirection.UpRight -> "southwest"
                GraphExplorerBoardDirection.Right -> "west"
                GraphExplorerBoardDirection.Left -> "east"
                GraphExplorerBoardDirection.DownLeft -> "northeast"
                GraphExplorerBoardDirection.DownRight -> "northwest"
                GraphExplorerBoardDirection.UpLeft -> "southeast"
            }

        }
    }

    fun getAboveRange(indx: Int, i: Int): ArrayList<Int> {
        val indexes = arrayListOf<Int>()

        var start = indx;
        var end = i;
        val LastRowTop = squareMax

        while (end-- > 0) {
            start += squareSize;
            if (start > LastRowTop) break;
            indexes.add(start)
        }

        return indexes;
    }

    fun getUpLeftRange(indx: Int, i: Int): ArrayList<Int> {
        val indexes = arrayListOf<Int>()

        var end = i;
        var start = indx;


        while (end-- > 0) {
            val LastRowTop = squareMax

            start += squareSize;
            if (LastRowTop < start) break;
            val lastRowLeft = start + (squareSize - (start % squareSize)) - 1;
            start += 1;
            if (lastRowLeft < start) {
                break
            }
            indexes.add(start)
        }

        return indexes;
    }

    fun getDownLeftRange(indx: Int, i: Int): ArrayList<Int> {
        val indexes = arrayListOf<Int>()

        var end = i;
        var start = indx;


        while (end-- > 0) {
            val lastrowbottom = (squareSize - (squareSize - (start % squareSize)))
            start -= squareSize;
            if (start < lastrowbottom) {
                break;
            }
            val lastRowLeft = start + (squareSize - (start % squareSize)) - 1;
            start += 1;
            if (lastRowLeft < start) {
                break
            }
            indexes.add(start)
        }

        return indexes;
    }

    fun getUpRightRange(indx: Int, i: Int): ArrayList<Int> {
        val indexes = arrayListOf<Int>()

        var end = i;
        var start = indx;


        while (end-- > 0) {
            val LastRowTop = squareMax

            start += squareSize;
            if (LastRowTop < start) break;
            val LastRowRight = start - (start % squareSize)
            start -= 1;
            if (LastRowRight > start) {
                break
            }
            indexes.add(start)
        }

        return indexes;
    }

    fun getRightRange(indx: Int, i: Int): ArrayList<Int> {
        val indexes = arrayListOf<Int>()


        var start = indx;
        val LastRowRight = start - (start % squareSize)
        var end = i;
        while (end-- > 0 && start > LastRowRight) {

            start -= 1;
            indexes.add(start)
        }
        return indexes;
    }

    fun getDownRange(indx: Int, i: Int): ArrayList<Int> {
        val indexes = arrayListOf<Int>()

        var start = indx;
        var end = i;
        val lastrowbottom = (squareSize - (squareSize - (start % squareSize)))

        while (end-- > 0) {
            start -= squareSize;
            if (start < lastrowbottom) break;
            indexes.add(start)
        }

        return indexes;
    }

    fun getDownRightRange(indx: Int, i: Int): ArrayList<Int> {
        val indexes = arrayListOf<Int>()

        var start = indx;
        var end = i;

        while (end-- > 0) {
            val lastrowbottom = (squareSize - (squareSize - (start % squareSize)))
            start -= squareSize;
            if (start < lastrowbottom) {
                break;
            }
            val LastRowRight = start - (start % squareSize)
            start -= 1;
            if (LastRowRight > start) {
                break
            }

            indexes.add(start)
        }

        return indexes;
    }

    fun getLeftRange(indx: Int, i: Int): ArrayList<Int> {
        val indexes = arrayListOf<Int>()


        var start = indx;
        val lastRowLeft = start + (squareSize - (start % squareSize)) - 1;
        var end = i;
        while (end-- > 0 && start < lastRowLeft) {

            start += 1;
            indexes.add(start)
        }
        return indexes;
    }


    private val squareMax = (squareSize * maxRows) - 1;

    fun getRange(center: Int, i: Int): GraphExplorerBoardDirectionList {
        val above = getAboveRange(center, i);
        val left = getLeftRange(center, i);
        val right = getRightRange(center, i);
        val topright = getUpRightRange(center, i);
        val downright = getDownRightRange(center, i);
        val below = getDownRange(center, i);
        val downleft = getDownLeftRange(center, i);
        val topleft = getUpLeftRange(center, i);
        return GraphExplorerBoardDirectionList(above, topright, right, downright, below, downleft, left, topleft);
    }


    // 5 4 3 2 1 0

}