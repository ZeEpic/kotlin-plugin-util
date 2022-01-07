import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

abstract class CustomCommand(open val minArgs: Int) : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) return true
        if (args.size < minArgs) return false
        return executeCommand(sender, args)
    }

    abstract fun executeCommand(player: Player, args: Array<out String>): Boolean

}
