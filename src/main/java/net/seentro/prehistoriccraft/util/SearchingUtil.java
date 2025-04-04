package net.seentro.prehistoriccraft.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public interface SearchingUtil {
    default BlockPos isBlockInRadius(Level level, BlockPos centerPos, int radius, Block targetBlock) {
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                BlockPos searchPos = centerPos.offset(x, 0, z);
                BlockPos groundPos = findGroundPos(level, searchPos);
                if (groundPos != null) {
                    if (level.getBlockState(groundPos.above()).getBlock() == targetBlock) {
                        return groundPos.above();
                    }
                }
            }
        }
        return null;
    }

    default BlockPos isWaterInRadius(Level level, BlockPos centerPos, int radius) {
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                BlockPos searchPos = centerPos.offset(x, 0, z);
                BlockPos groundPos = findGroundPos(level, searchPos);
                if (groundPos != null) {
                    if (level.getBlockState(groundPos).getBlock() == Blocks.WATER) {
                        return groundPos.above();
                    }
                }
            }
        }
        return null;
    }

    private static BlockPos findGroundPos(Level level, BlockPos pos) {
        for (int y = level.getMinBuildHeight(); y <= level.getMaxBuildHeight(); y++) {
            BlockPos currentPos = pos.atY(y);
            BlockState state = level.getBlockState(currentPos);
            if (!state.isAir()) {
                return currentPos;
            }
        }
        return null;
    }
}
