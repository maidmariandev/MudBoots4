package Graph

import Graph.Player.InsertKeyForPlayerLocation
import Graph.Player.InsertKeyForPlayers
import Graph.Player.Player

class PlayerGraph(


    Nodes: HashMap<String, List<GraphNode<Player>>>,

    ) : BroadCastingGraph<Player>(Nodes) {

    fun add(t: Player): Player {
        t.addCallBackk({ j -> update(t, j) }, graphBroadcastID)
        update(t, UpdateEventData(Player.PlayerGraphKeys.LookUp.toString(), -1, InsertKeyForPlayers(t.who),  "Initial Insertion"))
        update(t, UpdateEventData(Player.PlayerGraphKeys.Location.toString(), -1, InsertKeyForPlayerLocation(t.who),  "Initial Insertion"))
        return t;

    }
    fun remove(t:Player){
        update(t, UpdateEventData(Player.PlayerGraphKeys.LookUp.toString(),  InsertKeyForPlayers(t.who), -3,  "Initial Insertion"))
        update(t, UpdateEventData(Player.PlayerGraphKeys.Location.toString(),  InsertKeyForPlayerLocation(t.who), -3,  "Initial Insertion"))
        t.removeCallBack(graphBroadcastID)

    }

    fun getByIdentity(who:GraphIdentity): Player? {
        val keyName = Player.PlayerGraphKeys.LookUp.toString()
        return get(who, keyName, InsertKeyForPlayers(who))
    }

}