
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack
import kotlin.math.abs

abstract class CustomMenu(private val title: String, private val rows: Int) : InventoryHolder, Listener, Menu {

    private lateinit var inventory: Inventory
    override fun getInventory() = inventory

    override fun create() {
        inventory = Bukkit.createInventory(this, rows * 9, Component.text(title))
    }

    override fun showTo(player: Player) {
        player.openInventory(inventory)
    }

    fun fillWith(item: ItemStack) {
        for (i in 0..inventory.size) {
            inventory.setItem(i, item)
        }
    }

    fun borderWith(item: ItemStack) {
        for (i in 0 until inventory.size) {
            if (i % 9 == 0 || i % 9 == 8 || i < 10 || abs(i - inventory.size) < 9) {
                inventory.setItem(i, item)
            }
        }
    }

}
