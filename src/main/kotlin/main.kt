import sample.*

fun main(args: Array<String>) {

}

fun <T : Graphable> create_list(index: Int): GraphNode<T> {
    val Edges = arrayListOf<T>()
    return GraphNode(Edges, GraphNodeSortedMutator());
}
