package ru.cl_cody.hyperGUIs.api.impls;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import ru.cl_cody.hyperGUIs.api.Menu;
import ru.cl_cody.hyperGUIs.api.elements.DropRegion;
import ru.cl_cody.hyperGUIs.api.elements.MenuButton;

import java.util.List;

public record MenuImpl(String title, int size, List<MenuButton> buttons, List<DropRegion> regions) implements Menu {
    @Override
    public Inventory getInventory(Player player) {
        Inventory inv = Bukkit.createInventory(null, size, title);
        buttons.forEach(b -> inv.setItem(b.getSlot(), b.getIcon()));
        return inv;
    }
}
