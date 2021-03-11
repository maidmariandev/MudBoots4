package Command

import CommandResponse

interface CommandRequest {
    fun handleCommand()  : CommandResponse;
}