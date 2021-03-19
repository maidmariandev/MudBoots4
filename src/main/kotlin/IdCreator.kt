class IdCreator {
    private var idCounter: Int = 0;
    fun getNextId(): Int {
        synchronized(this) {
            return idCounter++;
        }
    }
}