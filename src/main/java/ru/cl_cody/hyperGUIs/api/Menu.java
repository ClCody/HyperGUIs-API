package ru.cl_cody.hyperGUIs.api;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public interface Menu {
    String title();
    int size();
    Inventory getInventory(Player player);
}
