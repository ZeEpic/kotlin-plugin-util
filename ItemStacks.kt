import net.kyori.adventure.text.Component
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

fun ItemStack.named(name: String): ItemStack {
    val meta = itemMeta
    meta.displayName(Component.text(name))
    return withMeta(meta)
}

fun ItemStack.description(description: String): ItemStack {
    val meta = itemMeta
    meta.lore(description
        .split("\n")
        .map { Component.text(it) }
    )
    return withMeta(meta)
}

fun ItemStack.withMeta(meta: ItemMeta): ItemStack {
    itemMeta = meta
    return this
}
