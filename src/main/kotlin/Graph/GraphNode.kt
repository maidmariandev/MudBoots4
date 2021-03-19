package Graph

import Graph.Graphable.Graphable
import Graph.Mutators.GraphMutator

class GraphNode<T : Graphable>(val Edges: ArrayList<T>, private val insertMutator : GraphMutator<T>) {

    init {
        insertMutator.graphNode = this;
    }
    fun remove(t:T) :T {
        return t;
    }
    fun insert(t:T) : T{
        return insertMutator.insert(t);
    }
    fun get(ident : GraphIdentity) : T?{
        return Edges.firstOrNull { it.who._id == ident._id }
    }

    fun get(ident: Long) : T? {

        return Edges.firstOrNull { it.who._id == ident }
    }


}