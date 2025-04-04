package net.seentro.prehistoriccraft.plants;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.seentro.prehistoriccraft.block.ModBlocks;
import net.seentro.prehistoriccraft.util.MultiblockPlantUtil;

public class NeuropterisBlockItem extends BlockItem implements MultiblockPlantUtil {
    public NeuropterisBlockItem(Properties pProperties) {
        super(ModBlocks.NEUROPTERIS_TRUNK.get(), pProperties);
    }

    @Override
    public InteractionResult place(BlockPlaceContext context) {
        if (!context.getLevel().isClientSide()) {
            placeTrunk(context, 4, new ItemStack(this), ModBlocks.NEUROPTERIS_TRUNK.get(), ModBlocks.NEUROPTERIS_SHOOT.get());
        }
        return InteractionResult.sidedSuccess(!context.getLevel().isClientSide());
    }
}
