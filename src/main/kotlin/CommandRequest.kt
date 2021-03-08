import sample.Player

interface CommandRequest {
    fun handleCommand()  : CommandResponse;
}