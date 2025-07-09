package ru.cl_cody.hyperGUIs.api.elements;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class DropRegion {
    private final List<Integer> slots;
    private final BiConsumer<Player, List<ItemStack>> onClose;

    /**
     * @param slots   список слотов, куда можно класть предметы
     * @param onClose колбэк, который вызовется при закрытии меню и отдаст все предметы из этих слотов
     */
    public DropRegion(List<Integer> slots, BiConsumer<Player, List<ItemStack>> onClose) {
        this.slots = slots;
        this.onClose = onClose;
    }

    public List<Integer> getSlots() {
        return slots;
    }

    public void handleClose(Player p, ItemStack[] contents) {
        List<ItemStack> items = slots.stream()
                // Фильтруем только валидные слоты внутри массива
                .filter(slot -> slot >= 0 && slot < contents.length)
                // Получаем предмет из каждого слота
                .map(slot -> contents[slot])
                // Убираем пустые ячейки
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        onClose.accept(p, items);
    }
}
