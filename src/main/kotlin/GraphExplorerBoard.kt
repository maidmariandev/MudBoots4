package sample

class GraphExplorerBoard(val squareSize: Int, val maxRows: Int) {

    fun get_above_range(indx: Int, i: Int): ArrayList<Int> {
        val indexes = arrayListOf<Int>()

        var start = indx;
        var end = i;
        val LastRowTop = squareMax

        while (end-- >0 && start <= LastRowTop) {
            start += squareSize;
            indexes.add(start)
        }

        return indexes;
    }
    fun get_up_left_range(indx: Int, i: Int): ArrayList<Int> {
        val indexes = arrayListOf<Int>()

        var end = i;
        var start = indx;


        while (end-- >0  ) {
            val LastRowTop = squareMax

            start += squareSize;
            if(LastRowTop < start) break;
            val lastRowLeft = start + (squareSize - (start % squareSize)) -1;
            start +=1;
            if(lastRowLeft < start) {
                break
            }
            indexes.add(start)
        }

        return indexes;
    }
    fun get_down_left_range(indx: Int, i: Int): ArrayList<Int> {
        val indexes = arrayListOf<Int>()

        var end = i;
        var start = indx;


        while (end-- >0  ) {
            val lastrowbottom = (squareSize - (squareSize - (start % squareSize)))
            start -= squareSize ;
            if(start < lastrowbottom){
                break;
            }
            val lastRowLeft = start + (squareSize - (start % squareSize)) -1;
            start +=1;
            if(lastRowLeft < start) {
                break
            }
            indexes.add(start)
        }

        return indexes;
    }
    fun get_up_right_range(indx: Int, i: Int): ArrayList<Int> {
        val indexes = arrayListOf<Int>()

        var end = i;
        var start = indx;


        while (end-- >0  ) {
            val LastRowTop = squareMax

            start += squareSize;
            if(LastRowTop < start) break;
            val LastRowRight = start - (start % squareSize)
            start -=1;
            if(LastRowRight > start) {
                break
            }
            indexes.add(start)
        }

        return indexes;
    }

    fun get_right_range(indx: Int, i: Int): ArrayList<Int> {
        val indexes = arrayListOf<Int>()


        var start = indx  ;
        val LastRowRight = start - (start % squareSize)
        var end = i;
        while (end-- >0 && start > LastRowRight ) {

            start -=1;
            indexes.add(start)
        }
        return indexes;
    }

    fun get_down_range(indx: Int, i: Int): ArrayList<Int> {
        val indexes = arrayListOf<Int>()

        var start = indx;
        var end = i;
        val lastrowbottom = (squareSize - (squareSize - (start % squareSize)))

        while (end-- >0 && start > lastrowbottom) {
            start -= squareSize;
            indexes.add(start)
        }

        return indexes;
    }

    fun get_down_right_range(indx: Int, i: Int): ArrayList<Int> {
        val indexes = arrayListOf<Int>()

        var start = indx;
        var end = i;

        while (end-- >0 ) {
            val lastrowbottom = (squareSize - (squareSize - (start % squareSize)))
            start -= squareSize ;
            if(start < lastrowbottom){
                break;
            }
            val LastRowRight = start - (start % squareSize)
            start -=1;
            if(LastRowRight > start) {
                break
            }

            indexes.add(start)
        }

        return indexes;
    }
    fun get_left_range(indx: Int, i: Int): ArrayList<Int> {
        val indexes = arrayListOf<Int>()


        var start = indx  ;
        val lastRowLeft = start + (squareSize - (start % squareSize)) -1;
        var end = i;
        while (end-- >0 && start < lastRowLeft ) {

            start +=1;
            indexes.add(start)
        }
        return indexes;
    }


    val squareMax = (squareSize * maxRows) - 1;
    val TopSquareStart = ((squareSize * maxRows) - 1) - squareSize;

    fun get_range(center: Int, i: Int): GraphExplorerBoardDirectionList {
        val above = get_above_range(center, i);
        val left = get_left_range(center, i);
        val right = get_right_range(center, i);
        val topright = get_up_right_range(center, i);
        val downright = get_down_right_range(center,i);
        val below = get_down_range(center,i);
        val downleft = get_down_left_range(center,i);
        val topleft = get_up_left_range(center,i);
        return GraphExplorerBoardDirectionList(above,topright,right,downright,below,downleft,left,topleft);
    }


    // 5 4 3 2 1 0

}