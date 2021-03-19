package Graph

import Graph.Graphable.BroadCastGraphable
import Graph.Graphable.Graphable

open class Graph<T : Graphable>(
    private val Nodes: HashMap<String, List<GraphNode<T>>>,

    ) {

    protected open fun update(j: T, updateEventData: UpdateEventData): Int {
        val keyName = updateEventData.KeyName
        val oldKey = updateEventData.OldKey
        if (oldKey != BroadCastGraphable.playerEnteringGraphFlag) {
            removeNodeItem(keyName, oldKey, j)
        }
        val newKey = updateEventData.NewKey
        if (newKey == BroadCastGraphable.playerExitingGraphFlag) {
            return oldKey;
        }
        val graphNode = Nodes[keyName]?.get(newKey) ?: throw NoSuchKeyError();
        if (updateEventData.DuplicateCheck && graphNode.Edges.find { it.who._id == j.who._id } != null) {
            return updateEventData.NewKey;
        }
        graphNode.insert(j);
        return newKey; // return updated nodes count
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

