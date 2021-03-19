package Graph

class GraphIdentity(val _id: Long, private var _blockId: Int = 0, private var _worldId: Int = 0) {

    val blockId: Int get() = _blockId;

    val worldId: Int get() = _worldId;
    fun canGoUp(maxRows: Int, rowSize: Int): Boolean {
        return blockId + rowSize <= maxRows * rowSize

    }
    fun Move(block: Int ) {
        _blockId = block;

    }

}