import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.inventory.InventoryClickEvent

interface Menu {

    fun create()
    fun showTo(player: Player)

    @EventHandler
    fun onClick(event: InventoryClickEvent)

}
