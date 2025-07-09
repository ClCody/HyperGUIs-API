package ru.cl_cody.hyperGUIs;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import ru.cl_cody.hyperGUIs.listeners.MenuListener;

public final class HyperGUIs extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Слушатель кликов
        Bukkit.getPluginManager().registerEvents(new MenuListener(), this);
        getLogger().info("HyperGUIs API Initialized");
    }

    @Override
    public void onDisable() {
        getLogger().info("HyperGUIs API Disabled");
    }
}
