package ru.cl_cody.hyperGUIs.api;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import ru.cl_cody.hyperGUIs.api.elements.MenuButton;

import java.util.ArrayList;
import java.util.List;

public class MenuBuilder {
    private String title = "Menu";
    private int size = 9;
    private final List<MenuButton> buttons = new ArrayList<>();

    public MenuBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public MenuBuilder setSize(int size) {
        if (size % 9 != 0 || size < 9) throw new IllegalArgumentException("Size must be multiple of 9");
        this.size = size;
        return this;
    }

    public MenuBuilder addButton(MenuButton button) {
        buttons.add(button);
        return this;
    }

    public Menu build() {
        return new Menu() {
            @Override
            public String getTitle() { return title; }
            @Override
            public int getSize() { return size; }
            @Override
            public Inventory getInventory(Player player) {
                Inventory inv = Bukkit.createInventory(null, size, title);
                buttons.forEach(b -> inv.setItem(b.getSlot(), b.getIcon()));
                return inv;
            }
        };
    }
}
