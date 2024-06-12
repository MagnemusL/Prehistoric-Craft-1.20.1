package net.smazeee.prehistoriccraft.item.custom;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class Satchets extends Item {
    private final int type;
    public Satchets(Properties properties, int type1) {
        super(properties);
        this.type = type1;
    }
}
