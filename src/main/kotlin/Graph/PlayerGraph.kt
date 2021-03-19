package Graph

import Graph.Player.InsertKeyForPlayerLocation
import Graph.Player.InsertKeyForPlayers
import Graph.Player.Player

class PlayerGraph(
    Nodes: HashMap<String, List<GraphNode<Player>>>,

    ) : Graph<Player>(Nodes) {
    //.toString(), p.getKey(Player.PlayerGraphKeys.LookUp,)
    fun add(t: Player): Player {
        t.addCallBackk({ j -> update(t, j) }, 0)
        update(t, UpdateEventData(Player.PlayerGraphKeys.LookUp.toString(), -1, InsertKeyForPlayers(t.who),  "Initial Insertion"))
        update(t, UpdateEventData(Player.PlayerGraphKeys.Location.toString(), -1, InsertKeyForPlayerLocation(t.who),  "Initial Insertion"))
        return t;

    }
    fun remove(t:Player){

    }

    fun getByIdentity(who:GraphIdentity): Player? {
        val keyName = Player.PlayerGraphKeys.LookUp.toString()
        return get(who, keyName, InsertKeyForPlayers(who))
    }
}