package net.seentro.prehistoriccraft.util.inventorypower;

import net.minecraft.core.Direction;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandlerModifiable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class InventoryDirectionWrapper {
    public Map<Direction, LazyOptional<WrappedHandler>> directionsMap;

    public InventoryDirectionWrapper(IItemHandlerModifiable handler, InventoryDirectionEntry... entries) {
        directionsMap = new HashMap<>();
        for (var j : entries) {
            directionsMap.put(j.direction,
                    LazyOptional.of(() -> new WrappedHandler(handler, (i) -> Objects.equals(i, j.slotIndex), (i, s) -> j.canInsert)));
        }
    }
}
