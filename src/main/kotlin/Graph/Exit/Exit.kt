package Graph.Exit

import Graph.GraphIdentity
import Graph.Graphable.Graphable

class Exit(override val who: GraphIdentity, val exitText: String) : Graphable {


    override fun getKey(KeyName: String): Int {
        TODO("Not yet implemented")
    }


}