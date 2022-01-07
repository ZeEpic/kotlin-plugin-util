import org.bukkit.entity.Player

abstract class SimpleCommand(
    val command: (Player) -> Unit
) : CustomCommand(0) {

    override fun executeCommand(player: Player, args: Array<out String>)
        = true.also { command(player) }
}
