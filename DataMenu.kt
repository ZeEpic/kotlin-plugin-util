import org.bukkit.Material
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack

class DataMenu<T : Button>(title: String, rows: Int, data: Collection<T>) : CustomMenu(title, rows) {

    init {
        create()
        borderWith(ItemStack(Material.GRAY_STAINED_GLASS_PANE).named(" "))
        data.forEachIndexed() { i, button ->
            inventory.setItem(i + 11, button.getIcon())
        }
    }

    override fun onClick(event: InventoryClickEvent) { }

}
