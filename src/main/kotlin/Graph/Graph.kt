package Graph

open class Graph<T : Graphable>(
    private val Nodes: HashMap<String, List<GraphNode<T>>>,

) {


    protected fun update(j: T, updateEventData: UpdateEventData): Int {
        val keyName = updateEventData.KeyName
        val oldKey = updateEventData.OldKey
        if (oldKey > 0) {
            val OldNode = removeNodeItem(keyName, oldKey, j)
            j.onGraphableLeavesBucket(updateEventData, OldNode as GraphNode<Graphable>);
            for (edge in OldNode.Edges) {
                edge.onGraphableLeavesBucket(j, updateEventData)
            }
        }
        val NewNode = Nodes[keyName]?.get(updateEventData.NewKey) ?: throw NoSuchKeyError();

        //old key -2 means we are shoving a bunch into a bucket perhaps more than once and to skip re-adding
        if (updateEventData.DuplicateCheck && NewNode.Edges.find { it.who._id == j.who._id } != null) {
            return updateEventData.NewKey;
        }
        NewNode.insert(j);
        j.onGraphableJoinsBucket(updateEventData, NewNode as GraphNode<Graphable>)
        for (edge in NewNode.Edges) {
            edge.onGraphableJoinsBucket(j, updateEventData)
        }
        return 0; // return updated nodes count
    }

    private fun removeNodeItem(keyName: String, oldKey: Int, j: T): GraphNode<T> {
        val OldNode = Nodes[keyName]?.get(oldKey) ?: throw NoSuchKeyError();
        OldNode.remove(j);
        return OldNode
    }

    fun add(t: T, KeyName: String, key: Int): T {
        t.addCallBackk({ j -> update(t, j) }, 0)
        update(t, UpdateEventData(KeyName, -1, key, "Insertion"))
        return t;

    }

    private fun getNode(KeyName: String, key: Int): GraphNode<T> {
        return Nodes[KeyName]?.get(key) ?: throw NoSuchKeyError()

    }

    fun get(identId: GraphIdentity, KeyName: String, key: Int): T? {
        val Node = Nodes[KeyName]?.get(key) ?: throw NoSuchKeyError();
        return Node.get(identId);

    }

    fun getNodesAt(range: Int, KeyName: String): List<T> {
        val Node = Nodes[KeyName]?.get(range) ?: throw NoSuchKeyError();
        return Node.Edges;
    }


}

