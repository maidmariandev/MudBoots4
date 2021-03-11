package Graph

interface GraphMutator<T : Graphable> {
    var graphNode: GraphNode<T>;
    abstract fun insert(t: T): T;
}
