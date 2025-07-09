package ru.cl_cody.hyperGUIs.api;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public interface Menu {
    String getTitle();
    int getSize();
    Inventory getInventory(Player player);
}
