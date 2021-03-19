package Graph.BroadCastingGraph

import Graph.Graph
import Graph.GraphNode
import Graph.Graphable.BroadCastGraphable
import Graph.Graphable.Graphable
import Graph.NoSuchKeyError
import Graph.UpdateEventData
import IdCreator

open class BroadCastingGraph<T : BroadCastGraphable>(private val Nodes: HashMap<String, List<GraphNode<T>>>) :
    Graph<T>(Nodes) {
    protected val graphBroadcastID = nextGraphId()

    override protected fun update(j: T, updateEventData: UpdateEventData): Int {
        val keyName = updateEventData.KeyName
        val oldKey = updateEventData.OldKey
        if (oldKey != BroadCastGraphable.playerEnteringGraphFlag) {
            val graphNode = removeNodeItem(keyName, oldKey, j)
            j.onGraphableLeavesBucket(updateEventData, graphNode as GraphNode<Graphable>);
            val edges = graphNode.Edges
            for (edge in edges) {
                (edge as BroadCastGraphable).onGraphableLeavesBucket(j, updateEventData)
            }
        }

        val newKey = updateEventData.NewKey
        if (newKey == BroadCastGraphable.playerExitingGraphFlag) {
            return newKey;
        }
        val NewNode = Nodes[keyName]?.get(newKey) ?: throw NoSuchKeyError();

        //old key -2 means we are shoving a bunch into a bucket perhaps more than once and to skip re-adding
        if (updateEventData.DuplicateCheck && NewNode.Edges.find { it.who._id == j.who._id } != null) {
            return newKey;
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
        private val idCreator: IdCreator = IdCreator()
        fun nextGraphId(): Int {
            return idCreator.getNextId();
        }
    }
}