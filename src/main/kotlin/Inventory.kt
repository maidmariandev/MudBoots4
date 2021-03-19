import Graph.*
import Graph.Player.Player

class Inventory(override val who: GraphIdentity, val p1: Player) : Graphable {
    override fun getKey(KeyName: String): Int {
        TODO("Not yet implemented")
    }

    override fun keyChanges(KeyName: String, oldVal: Int, Reason: String): Int {
        TODO("Not yet implemented")
    }

    override val callbacks: ArrayList<Pair<Int, (UpdateEventData) -> Int>>
        get() = TODO("Not yet implemented")

    override fun onGraphableLeavesBucket(item: Graphable, updateEventData: UpdateEventData) {
        TODO("Not yet implemented")
    }

    override fun onGraphableJoinsBucket(item: Graphable, updateEventData: UpdateEventData) {
        TODO("Not yet implemented")
    }

    override fun onGraphableLeavesBucket(
        updateEventData: UpdateEventData,
        bucket: GraphNode<Graphable>
    ) {
        TODO("Not yet implemented")
    }

    override fun onGraphableJoinsBucket(
        updateEventData: UpdateEventData,
        bucket: GraphNode<Graphable>
    ) {
        TODO("Not yet implemented")
    }

}