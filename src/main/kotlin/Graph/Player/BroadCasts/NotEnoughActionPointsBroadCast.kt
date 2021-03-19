package Graph.Player.BroadCasts

import BroadCast

class NotEnoughActionPointsBroadCast(actionName: String) :
    BroadCast("You do not have enough action points to $actionName") {

}