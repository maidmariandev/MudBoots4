package sample

interface GraphMutator<T : Graphable> {
    var GNode: GraphNode<T>;
    abstract fun insert(t: T): T;
}
