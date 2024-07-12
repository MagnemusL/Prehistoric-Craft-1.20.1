package net.smazeee.prehistoriccraft.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BatteryItem extends Item {
    public int storedRf = 0;
    public BatteryItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public Component getDescription() {
        return Component.literal("Stored RF: " + storedRf);
    }
}