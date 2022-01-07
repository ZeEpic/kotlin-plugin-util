import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

@Listening
class MenuListener : Listener {

    @EventHandler
    fun onClick(event: InventoryClickEvent) {

        val holder = event.inventory.holder ?: return
        event.currentItem ?: return
        if (holder is CustomMenu) {
            event.isCancelled = true
            holder.onClick(event)
        }

    }

}
