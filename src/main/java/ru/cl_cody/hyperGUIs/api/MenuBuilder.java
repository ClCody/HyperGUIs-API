package ru.cl_cody.hyperGUIs.api;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import ru.cl_cody.hyperGUIs.api.elements.DropRegion;
import ru.cl_cody.hyperGUIs.api.elements.MenuButton;
import ru.cl_cody.hyperGUIs.api.impls.MenuImpl;

import java.util.ArrayList;
import java.util.List;

public class MenuBuilder {
    private String title = "Menu";
    private int size = 9;
    private final List<MenuButton> buttons = new ArrayList<>();
    private final List<DropRegion> regions = new ArrayList<>();

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

    public MenuBuilder addRegion(DropRegion region) {
        this.regions.add(region);
        return this;
    }

    public Menu build() {
        return new MenuImpl(title, size, buttons, regions);
    }
}
