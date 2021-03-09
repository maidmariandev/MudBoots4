
class GraphNode<T : Graphable>(val Edges: MutableList<T>, private val insertMutator : GraphMutator<T>) {

    init {
        insertMutator.graphNode = this;
    }

    fun insert(t:T) : T{
        return insertMutator.insert(t);
    }
    fun get(ident :Identity) : T?{
        return Edges.firstOrNull { it.who._id == ident._id }
    }


}