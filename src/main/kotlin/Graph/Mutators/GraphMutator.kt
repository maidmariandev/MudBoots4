package Graph.Mutators

import Graph.GraphNode
import Graph.Graphable

interface GraphMutator<T : Graphable> {
    var graphNode: GraphNode<T>;
    abstract fun insert(t: T): T;
}
