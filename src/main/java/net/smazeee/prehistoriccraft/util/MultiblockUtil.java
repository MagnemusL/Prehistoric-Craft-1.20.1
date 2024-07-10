package net.smazeee.prehistoriccraft.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public interface MultiblockUtil {
    default boolean areAllBlocksInCube(Level level, BlockPos centerPos, Block block, int xSize, int ySize, int zSize) {
        BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();

        int xOffset = xSize / 2;
        int yOffset = ySize / 2;
        int zOffset = zSize / 2;

        for (int x = -xOffset; x <= xOffset; x++) {
            for (int y = -yOffset; y <= yOffset; y++) {
                for (int z = -zOffset; z <= zOffset; z++) {
                    mutablePos.set(centerPos.getX() + x, centerPos.getY() + y, centerPos.getZ() + z);
                    BlockState state = level.getBlockState(mutablePos);
                    if (state.getBlock() != block) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}