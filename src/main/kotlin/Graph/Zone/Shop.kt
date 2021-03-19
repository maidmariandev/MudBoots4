package Graph.Zone

import Graph.Explorer.SquareBoard.GraphExplorerBoard
import Graph.Player.Player
import Graph.Player.PlayerGraph

class Shop(playerGraph: PlayerGraph, graphExplorer: GraphExplorerBoard) : World(graphExplorer)  {

    lateinit var townOwner: Player

}