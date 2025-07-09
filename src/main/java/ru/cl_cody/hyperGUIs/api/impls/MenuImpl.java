package ru.cl_cody.hyperGUIs.api.impls;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import ru.cl_cody.hyperGUIs.api.Menu;
import ru.cl_cody.hyperGUIs.api.elements.DropRegion;
import ru.cl_cody.hyperGUIs.api.elements.MenuButton;

import java.util.List;

public class MenuImpl implements Menu {
    private final String title;
    private final int size;
    private final List<MenuButton> buttons;
    private final List<DropRegion> regions;

    MenuImpl(String title, int size, List<MenuButton> buttons, List<DropRegion> regions) {
        this.title = title;
        this.size = size;
        this.buttons = buttons;
        this.regions = regions;
    }

    @Override public String getTitle() { return title; }
    @Override public int getSize() { return size; }

    @Override
    public Inventory getInventory(Player player) {
        Inventory inv = Bukkit.createInventory(null, size, title);
        buttons.forEach(b -> inv.setItem(b.getSlot(), b.getIcon()));
        return inv;
    }

    public List<MenuButton> getButtons() { return buttons; }
    public List<DropRegion> getRegions() { return regions; }
}
