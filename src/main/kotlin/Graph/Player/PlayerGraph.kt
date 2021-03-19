package Graph.Player

import Graph.BroadCastingGraph.BroadCastingGraph
import Graph.Explorer.SquareBoard.GraphExplorerBoardDirectionList
import Graph.Explorer.SquareBoard.GraphExplorerBoardDirectionResult
import Graph.GraphIdentity
import Graph.GraphNode
import Graph.Graphable.BroadCastGraphable
import Graph.UpdateEventData

class PlayerGraph(


    Nodes: HashMap<String, List<GraphNode<Player>>>,

    ) : BroadCastingGraph<Player>(Nodes) {

    fun add(t: Player): Player {
        t.addCallBackk({ j -> update(t, j) }, graphBroadcastID)
        update(t, UpdateEventData(Player.PlayerGraphKeys.LookUp.toString(), BroadCastGraphable.playerEnteringGraphFlag, InsertKeyForPlayers(t.who),  "Initial Insertion"))
        update(t, UpdateEventData(Player.PlayerGraphKeys.Location.toString(), BroadCastGraphable.playerEnteringGraphFlag, InsertKeyForPlayerLocation(t.who),  "Initial Insertion"))
        return t;

    }
    fun remove(t:Player){
        update(t, UpdateEventData(Player.PlayerGraphKeys.LookUp.toString(),  InsertKeyForPlayers(t.who), BroadCastGraphable.playerExitingGraphFlag,  "Initial Insertion"))
        update(t, UpdateEventData(Player.PlayerGraphKeys.Location.toString(),  InsertKeyForPlayerLocation(t.who), BroadCastGraphable.playerExitingGraphFlag,  "Initial Insertion"))
        t.removeCallBack(graphBroadcastID)

    }
    fun getByLocation(who: GraphIdentity): Player? {
        val keyName = Player.PlayerGraphKeys.Location.toString()
        return get(who, keyName, InsertKeyForPlayerLocation(who))
    }
    fun getByIdentity(who: GraphIdentity): Player? {
        val keyName = Player.PlayerGraphKeys.LookUp.toString()
        return get(who, keyName, InsertKeyForPlayers(who))
    }

    fun translate(nearBy: GraphExplorerBoardDirectionList): List<GraphExplorerBoardDirectionResult<Player>> {
        val keyName = Player.PlayerGraphKeys.Location.toString()
        return nearBy.translate(this,keyName)
    }

}