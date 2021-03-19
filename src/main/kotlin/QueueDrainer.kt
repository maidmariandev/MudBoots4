class QueueDrainer<T> {
    private val theQueue: ArrayList<T> = arrayListOf<T>()

    fun addBroadCast(b: T) {
        synchronized(this) {
            theQueue.add(b);
        }
    }

    fun drainBroadCasts(Max: Int = -1): List<T> {
        synchronized(this) {
            if (Max == 0) return listOf()
            if (Max < -1) return listOf();
            if (Max == -1) {
                val tmp = theQueue.toList();
                theQueue.clear();
                return tmp;
            }
            val tmp = theQueue.subList(0, Max);
            theQueue.removeAll(tmp);
            return tmp;
        }
    }

    fun dirty(): Int {
        synchronized(this) {
            if(theQueue.isEmpty()) return 0;
            return 1;
        }
    }
}