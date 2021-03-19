package Graph

;

interface Graphable {
    val who: GraphIdentity
    fun getKey(KeyName: String): Int
    fun keyChanges(KeyName: String, oldVal: Int,Reason:String): Int


    val callbacks: ArrayList<Pair<Int, (UpdateEventData) -> Int>>
    fun addCallBackk(func: (UpdateEventData) -> Int, id: Int) {
        callbacks.add(Pair(id, func))
    }

    fun getallCallBacks(): List<(UpdateEventData) -> Int> {
        return callbacks.map { it.second }
    }

    fun removeCallBack(id: Int): Boolean {
        return callbacks.removeIf { it.first == id }
    }

    fun onGraphableJoinsBucket(updateEventData: UpdateEventData, bucket: GraphNode<Graphable>)
    fun onGraphableLeavesBucket(updateEventData: UpdateEventData, bucket: GraphNode<Graphable>)
    fun onGraphableJoinsBucket(item: Graphable, updateEventData: UpdateEventData)
    fun onGraphableLeavesBucket(item: Graphable, updateEventData: UpdateEventData)

}