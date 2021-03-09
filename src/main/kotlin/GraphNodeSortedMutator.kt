
class GraphNodeSortedMutator<T : Graphable>() : GraphMutator<T> {
    override lateinit var graphNode : GraphNode<T> ;
    private var startIndexValue: Long = 0;
    private var middleIndexValue: Long = 0;
    private var endIndexValue: Long = 0;
    override fun insert(t: T): T {
        val result = insertCase(t);
        if (graphNode.Edges.size > 3) {
            startIndexValue = graphNode.Edges[0].who._id;
            endIndexValue = graphNode.Edges[graphNode.Edges.size - 1].who._id;
            middleIndexValue = graphNode.Edges[middleIndex()].who._id;
            println("start : $startIndexValue middle : $middleIndexValue end : $endIndexValue")
        }
        return result;
    }

    private fun middleIndex(): Int {
        val sizeCount = graphNode.Edges.size - 1
        return sizeCount /2;

    }

    private fun insertCase(t: T): T {
        if (edgeCases(t)) return t
        val distanceStart = Math.abs(startIndexValue - t.who._id);
        val distanceMiddle = Math.abs(middleIndexValue - t.who._id);
        val distanceEnd = Math.abs(endIndexValue - t.who._id);

        return if (t.who._id in (startIndexValue + 1) until middleIndexValue) {
            insertStart(t, distanceStart, distanceMiddle);
        } else {
            insertMiddle(t, distanceMiddle, distanceEnd);
        }

    }


    private fun edgeCases(t: T): Boolean {
        if (graphNode.Edges.size < 4) {
            graphNode.Edges.add(t);
            graphNode.Edges.sortBy { it.who._id }
            if (graphNode.Edges.size == 3) {
                startIndexValue = graphNode.Edges[0].who._id;
                middleIndexValue = graphNode.Edges[1].who._id;
                endIndexValue = graphNode.Edges[2].who._id;
            }
            return true;
        }else{
            if(t.who._id < graphNode.Edges[0].who._id){
                graphNode. Edges.add(0,t);
                return true;
            }else if(t.who._id > graphNode.Edges[graphNode.Edges.size-1].who._id){
                graphNode.Edges.add(t);
                return true;
            }
        }

        return false
    }

    private fun insertIterRev(t: T, start: Int, end: Int): T {
        if (end > start) {
            throw Error("insert reverse iterator divide by 0");
        }
        if (start == end) {
            //we reached the end of the block
            graphNode.Edges.add(start, t);
            return t;
        }
        if (graphNode.Edges[start].who._id < t.who._id) {
            graphNode.Edges.add(start , t);
            return t;
        }
        return insertIterRev(t, start - 1, end);

    }

    private fun insertIter(t: T, start: Int, end: Int): T {

        if (end < start) {
            throw Error("insert iterator divide by 0");
        }
        if (start == end) {
            //we reached the end of the block
            graphNode.Edges.add(end, t);
            return t;
        }
        if (graphNode.Edges[start].who._id > t.who._id) {
            graphNode.Edges.add(start, t);
            return t;
        }
        return insertIter(t, start + 1, end);

    }

    private fun insertMiddle(t: T, distanceMiddle: Long, distanceEnd: Long): T {
        return if (distanceEnd < distanceMiddle) {
            insertIterRev(t, graphNode.Edges.size - 1, middleIndex());
        } else {
            insertIter(t, middleIndex(), graphNode.Edges.size - 1);
        }

    }

    private fun insertStart(t: T, distanceStart: Long, distanceMiddle: Long): T {
        return if (distanceMiddle < distanceStart) {
            insertIterRev(t, middleIndex(), 0);
        } else {
            insertIter(t, 0, middleIndex());
        }

    }
}