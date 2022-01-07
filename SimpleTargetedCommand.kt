import org.bukkit.entity.Player

open class SimpleTargetedCommand(
    val command: (Player, Player) -> Unit
) : TargetedCommand() {

    override fun executeTargetedCommand(player: Player, target: Player, args: Array<out String>)
        = true.also { command(player, target) }

}
