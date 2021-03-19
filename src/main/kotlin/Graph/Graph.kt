package Graph

import Graph.Graphable.BroadCastGraphable
import Graph.Graphable.Graphable
import IdCreator

open class BroadCastingGraph<T :BroadCastGraphable> (private val Nodes: HashMap<String, List<GraphNode<T>>>,) : Graph<T>(Nodes){
    protected val graphBroadcastID = nextGraphId()

    override protected fun update(j: T, updateEventData: UpdateEventData): Int {
        val keyName = updateEventData.KeyName
        val oldKey = updateEventData.OldKey
        if (oldKey > 0) {
            val OldNode = removeNodeItem(keyName, oldKey, j)
            j.onGraphableLeavesBucket(updateEventData, OldNode as GraphNode<Graphable>);
            val edges = OldNode.Edges
            for (edge in edges) {
                (edge as BroadCastGraphable).onGraphableLeavesBucket(j, updateEventData)
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
            (edge as BroadCastGraphable).onGraphableJoinsBucket(j, updateEventData)
        }
        return 0; // return updated nodes count
    }
    override fun add(t: T, KeyName: String, key: Int): T {
        t.addCallBackk({ j -> update(t, j) }, 0)
        update(t, UpdateEventData(KeyName, -1, key, "Insertion"))
        return t;

    }
  override fun removeNodeItem(keyName: String, oldKey: Int, j: T): GraphNode<T> {
        val OldNode = Nodes[keyName]?.get(oldKey) ?: throw NoSuchKeyError();
        OldNode.remove(j);
        return OldNode
    }
    companion object {
        private   val idCreator : IdCreator = IdCreator()
        fun nextGraphId(): Int {
            return idCreator.getNextId();
        }
    }
}

open class Graph<T : Graphable>(
    private val Nodes: HashMap<String, List<GraphNode<T>>>,

) {

    protected open fun update(j: T, updateEventData: UpdateEventData): Int {
        val keyName = updateEventData.KeyName
        val oldKey = updateEventData.OldKey
        if (oldKey > 0) {
            val OldNode = removeNodeItem(keyName, oldKey, j)

        }
        val NewNode = Nodes[keyName]?.get(updateEventData.NewKey) ?: throw NoSuchKeyError();

        //old key -2 means we are shoving a bunch into a bucket perhaps more than once and to skip re-adding
        if (updateEventData.DuplicateCheck && NewNode.Edges.find { it.who._id == j.who._id } != null) {
            return updateEventData.NewKey;
        }
        NewNode.insert(j);

        return 0; // return updated nodes count
    }
      open fun removeNodeItem(keyName: String, oldKey: Int, j: T): GraphNode<T> {
        val OldNode = Nodes[keyName]?.get(oldKey) ?: throw NoSuchKeyError();
        OldNode.remove(j);
        return OldNode
    }

    open fun add(t: T, KeyName: String, key: Int): T {

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

