


class Graph<T : Graphable>(
    private val Nodes: MutableList<GraphNode<T>>,
    val InsertKey: (obj: Identity) -> Int,
    private val Add_Item: ((obj: T, graph: Graph<T>) -> T)?,
    private val Remove_Item: ((obj: T, graph: Graph<T>) -> T)?
) {
    init {

    }


    fun add(t: T): T {
        val key = InsertKey(t.who);
        val Node = Nodes[key];
        return if (Add_Item == null) {
            Node.insert(t);
            t;
        } else {
            Add_Item?.invoke(t, this);
        }
    }

    fun getIds(i: Int): List<Long> {
        return Nodes[i].Edges.map { i -> i.who._id };
    }

    fun get(identId: Identity): T? {
        val key = InsertKey(identId);
        val graphNode = Nodes[key];
        return graphNode.get(identId);

    }

    fun getNodesAt(range: Int): List<T> {
        return Nodes[range].Edges;


    }


}