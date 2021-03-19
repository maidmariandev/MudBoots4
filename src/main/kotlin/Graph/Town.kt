package Graph

import Graph.Explorer.GraphExplorer
import Graph.Explorer.SquareBoard.GraphExplorerBoard
import Graph.Player.Player
import World


class Shop(playerGraph: PlayerGraph, graphExplorer: GraphExplorerBoard) : World(graphExplorer)  {

    lateinit var townOwner: Player

}

class Town(playerGraph: PlayerGraph, graphExplorer: GraphExplorerBoard) : World(graphExplorer)  {

    lateinit var townOwner: Player


}