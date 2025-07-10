package ru.cl_cody.hyperGUIs.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.ItemStack;
import ru.cl_cody.hyperGUIs.api.Menu;
import ru.cl_cody.hyperGUIs.api.elements.DropRegion;
import ru.cl_cody.hyperGUIs.api.elements.MenuButton;
import ru.cl_cody.hyperGUIs.api.impls.MenuImpl;

import java.util.HashMap;
import java.util.Map;

public class MenuListener implements Listener {
    private static final Map<String, Menu> openMenus = new HashMap<>();

    public static void registerOpenMenu(Player p, Menu menu) {
        openMenus.put(p.getName(), menu);
        p.openInventory(menu.getInventory(p));
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player p)) return;

        // Меню игрока
        Menu menu = openMenus.get(p.getName());
        if (menu == null) return;

        // Название через InventoryView
        String openTitle = e.getView().getTitle();
        if (!openTitle.equals(menu.title())) return;

        // Блок перемещения
        e.setCancelled(true);

        // Предмет клика
        ItemStack clicked = e.getCurrentItem();
        if (clicked == null) return;

        // Находим нужную кнопку и вызываем её
        for (MenuButton b : ((MenuImpl) menu).buttons()) {
            if (b.getSlot() == e.getRawSlot()) {
                b.handleClick(e, p);
                break;
            }
        }
    }

    @EventHandler
    public void onDrag(InventoryDragEvent e) {
        if (!(e.getWhoClicked() instanceof Player p)) return;

        Menu menu = openMenus.get(p.getName());
        if (menu == null) return;

        // Проверка меню
        String openTitle = e.getView().getTitle();
        if (!openTitle.equals(menu.title())) return;

        // Разрешаем драг только в тех слотах, что указаны в регионах
        e.setCancelled(true); // по-умолчанию всё отменяем
        for (DropRegion region : ((MenuImpl) menu).regions()) {
            for (int slot : region.getSlots()) {
                if (e.getRawSlots().contains(slot)) {
                    e.setCancelled(false);
                    return;
                }
            }
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        if (!(e.getPlayer() instanceof Player p)) return;

        Menu menu = openMenus.remove(p.getName());
        if (menu == null) return;

        // Вызываем onClose у всех регионов
        ItemStack[] contents = e.getInventory().getContents();
        for (DropRegion region : ((MenuImpl) menu).regions()) {
            region.handleClose(p, contents);
        }
    }
}
