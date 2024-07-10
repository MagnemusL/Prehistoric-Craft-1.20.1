package net.smazeee.prehistoriccraft.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.smazeee.prehistoriccraft.block.ModBlocks;

public class TestBlockItem extends BlockItem {
    public TestBlockItem(Properties pProperties) {
        super(ModBlocks.TEST_OBJ.get(), pProperties);
    }

    @Override
    public InteractionResult place(BlockPlaceContext pContext) {
        Level level = pContext.getLevel();
        BlockPos pos =  pContext.getClickedPos();
        for (int y = 0; y < 2; y++) {
            for (int x = 0; x < 2; x++) {
                switch (pContext.getHorizontalDirection()) {
                    case NORTH -> level.setBlockAndUpdate(pos.west(x).above(y), Blocks.ANCIENT_DEBRIS.defaultBlockState());
                    case WEST -> level.setBlockAndUpdate(pos.south(x).above(y), Blocks.ANCIENT_DEBRIS.defaultBlockState());
                    case SOUTH -> level.setBlockAndUpdate(pos.east(x).above(y), Blocks.ANCIENT_DEBRIS.defaultBlockState());
                    case EAST -> level.setBlockAndUpdate(pos.north(x).above(y), Blocks.ANCIENT_DEBRIS.defaultBlockState());
                }
            }
        }
        return InteractionResult.SUCCESS;
    }
}
