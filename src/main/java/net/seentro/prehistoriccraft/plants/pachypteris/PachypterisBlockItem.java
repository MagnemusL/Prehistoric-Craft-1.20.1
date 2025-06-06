package net.seentro.prehistoriccraft.plants.pachypteris;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.seentro.prehistoriccraft.block.ModBlocks;
import net.seentro.prehistoriccraft.util.MultiblockPlantUtil;

public class PachypterisBlockItem extends BlockItem implements MultiblockPlantUtil {
    public PachypterisBlockItem(Properties pProperties) {
        super(ModBlocks.PACHYPTERIS_TRUNK.get(), pProperties);
    }

    @Override
    public InteractionResult place(BlockPlaceContext context) {
        if (!context.getLevel().isClientSide()) {
            placeTrunk(context, 3, new ItemStack(this), ModBlocks.PACHYPTERIS_TRUNK.get(), ModBlocks.PACHYPTERIS_SHOOT.get());
        }
        return InteractionResult.sidedSuccess(!context.getLevel().isClientSide());
    }
}
