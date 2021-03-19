import Graph.GraphNode
import Graph.Mutators.Sorted.GraphNodeSortedMutator
import Graph.Graphable.Graphable

fun main(args: Array<String>) {

}

fun <T : Graphable> createList(index: Int): GraphNode<T> {
    val edges = arrayListOf<T>()
    return GraphNode(edges, GraphNodeSortedMutator());
}
