package ru.cl_cody.hyperGUIs.api.elements;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.BiConsumer;

public class MenuButton {
    private final int slot;
    private final ItemStack icon;
    private final BiConsumer<InventoryClickEvent, Player> onClick;

    public MenuButton(int slot, ItemStack icon, BiConsumer<InventoryClickEvent, Player> onClick) {
        this.slot = slot;
        this.icon = icon;
        this.onClick = onClick;
    }

    public int getSlot() { return slot; }
    public ItemStack getIcon() { return icon; }
    public void handleClick(InventoryClickEvent e, Player p) { onClick.accept(e, p); }
}
