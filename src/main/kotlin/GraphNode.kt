package sample

class GraphNode<T : Graphable>(val Edges: MutableList<T>, private val Inserter : GraphMutator<T>) {

    init {
        Inserter.GNode = this;
    }

    fun insert(t:T) : T{
        return Inserter.insert(t);
    }
    fun get(ident :Identity) : T?{
        return Edges.firstOrNull { it.ident._id == ident._id }
    }


}