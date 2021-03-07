package sample

class GraphNodeSortedMutator<T : Graphable>() : GraphMutator<T> {
    override lateinit var GNode : GraphNode<T> ;
    private var startindexValue: Long = 0;
    private var middleindexValue: Long = 0;
    private var endindexValue: Long = 0;
    override fun insert(t: T): T {
        val result = insert_case(t);
        if (GNode.Edges.size > 3) {
            startindexValue = GNode.Edges[0].ident._id;
            endindexValue = GNode.Edges[GNode.Edges.size - 1].ident._id;
            middleindexValue = GNode.Edges[middle_index()].ident._id;
            println("start : $startindexValue middle : $middleindexValue end : $endindexValue")
        }
        return result;
    }

    private fun middle_index(): Int {
        val sizeCount = GNode.Edges.size - 1
        return sizeCount /2;

    }

    private fun insert_case(t: T): T {
        if (EdgeCases(t)) return t
        val distanceStart = Math.abs(startindexValue - t.ident._id);
        val distanceMiddle = Math.abs(middleindexValue - t.ident._id);
        val distanceEnd = Math.abs(endindexValue - t.ident._id);

        return if (t.ident._id in (startindexValue + 1) until middleindexValue) {
            insert_start(t, distanceStart, distanceMiddle);
        } else {
            insert_middle(t, distanceMiddle, distanceEnd);
        }

    }


    private fun EdgeCases(t: T): Boolean {
        if (GNode.Edges.size < 4) {
            GNode.Edges.add(t);
            GNode.Edges.sortBy { it.ident._id }
            if (GNode.Edges.size == 3) {
                startindexValue = GNode.Edges[0].ident._id;
                middleindexValue = GNode.Edges[1].ident._id;
                endindexValue = GNode.Edges[2].ident._id;
            }
            return true;
        }else{
            if(t.ident._id < GNode.Edges[0].ident._id){
                GNode. Edges.add(0,t);
                return true;
            }else if(t.ident._id > GNode.Edges[GNode.Edges.size-1].ident._id){
                GNode.Edges.add(t);
                return true;
            }
        }

        return false
    }

    private fun insert_iter_rev(t: T, start: Int, end: Int): T {
        if (end > start) {
            throw Error("insert reverse iterator divide by 0");
        }
        if (start == end) {
            //we reached the end of the block
            GNode.Edges.add(start, t);
            return t;
        }
        if (GNode.Edges[start].ident._id < t.ident._id) {
            GNode.Edges.add(start , t);
            return t;
        }
        return insert_iter_rev(t, start - 1, end);

    }

    private fun insert_iter(t: T, start: Int, end: Int): T {

        if (end < start) {
            throw Error("insert iterator divide by 0");
        }
        if (start == end) {
            //we reached the end of the block
            GNode.Edges.add(end, t);
            return t;
        }
        if (GNode.Edges[start].ident._id > t.ident._id) {
            GNode.Edges.add(start, t);
            return t;
        }
        return insert_iter(t, start + 1, end);

    }

    private fun insert_middle(t: T, distanceMiddle: Long, distanceEnd: Long): T {
        return if (distanceEnd < distanceMiddle) {
            insert_iter_rev(t, GNode.Edges.size - 1, middle_index());
        } else {
            insert_iter(t, middle_index(), GNode.Edges.size - 1);
        }

    }

    private fun insert_start(t: T, distanceStart: Long, distanceMiddle: Long): T {
        return if (distanceMiddle < distanceStart) {
            insert_iter_rev(t, middle_index(), 0);
        } else {
            insert_iter(t, 0, middle_index());
        }

    }
}