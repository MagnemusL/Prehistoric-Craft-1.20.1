package net.seentro.prehistoriccraft.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.seentro.prehistoriccraft.plants.Belemnopteris;

import java.util.Objects;

public class BatteryItem extends Item{
    public int storedRf = 0;
    public BatteryItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public Component getDescription() {
        return Component.literal("Stored RF: " + storedRf);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Objects.requireNonNull(pContext.getPlayer()).displayClientMessage(Component.literal("List: " + Belemnopteris.TEMP_POS_LIST), true);
        return InteractionResult.SUCCESS;
    }
}