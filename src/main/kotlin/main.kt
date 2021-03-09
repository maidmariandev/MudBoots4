
fun main(args: Array<String>) {

}

fun <T : Graphable> createList(index: Int): GraphNode<T> {
    val edges = arrayListOf<T>()
    return GraphNode(edges, GraphNodeSortedMutator());
}
