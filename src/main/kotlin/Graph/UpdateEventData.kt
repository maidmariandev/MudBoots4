package Graph

data class UpdateEventData(val KeyName :String, val OldKey :Int, val NewKey:Int, val Extra : String,val DuplicateCheck: Boolean = false)