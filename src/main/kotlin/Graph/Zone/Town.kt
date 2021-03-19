package Graph.Zone

import Graph.Explorer.SquareBoard.GraphExplorerBoard
import Graph.Player.Player
import Graph.Player.PlayerGraph


class Town(playerGraph: PlayerGraph, graphExplorer: GraphExplorerBoard) : World(graphExplorer)  {

    lateinit var townOwner: Player


}