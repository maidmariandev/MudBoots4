class IdCreator {
    private var idCounter: Int = 0;
    fun get_next_global_id(): Int {
        synchronized(this) {
            return idCounter++;
        }
    }
}