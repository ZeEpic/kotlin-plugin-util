import org.bukkit.Bukkit
import org.bukkit.entity.Player

abstract class TargetedCommand(override val minArgs: Int = 1) : CustomCommand(minArgs) {

    override fun executeCommand(player: Player, args: Array<out String>): Boolean {
        if (args.isEmpty()) return false
        val target = Bukkit.getPlayer(args[0]) ?: return false
        if (target == player) {
            player.send("You cannot target yourself.")
            return true
        }
        return executeTargetedCommand(player, target, args.copyOfRange(1, args.size))
    }

    abstract fun executeTargetedCommand(player: Player, target: Player, args: Array<out String>): Boolean

}
