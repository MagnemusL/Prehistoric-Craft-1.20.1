package net.seentro.prehistoriccraft.plants.paracycas;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.seentro.prehistoriccraft.block.ModBlocks;
import net.seentro.prehistoriccraft.util.MultiblockPlantUtil;

public class ParacycasBlockItem extends BlockItem implements MultiblockPlantUtil {
    public ParacycasBlockItem(Properties pProperties) {
        super(ModBlocks.PARACYCAS_TRUNK.get(), pProperties);
    }

    @Override
    public InteractionResult place(BlockPlaceContext context) {
        if (!context.getLevel().isClientSide()) {
            placeTrunk(context, 5, new ItemStack(this), ModBlocks.PARACYCAS_TRUNK.get(), ModBlocks.PARACYCAS_SHOOT.get());
        }
        return InteractionResult.sidedSuccess(!context.getLevel().isClientSide());
    }
}
