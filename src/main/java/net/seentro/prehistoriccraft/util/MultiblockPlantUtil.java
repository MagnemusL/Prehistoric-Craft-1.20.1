package net.seentro.prehistoriccraft.util;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

public interface MultiblockPlantUtil {
    default void placeTrunk(BlockPlaceContext context, int maxTrunks, ItemStack stack, Block trunk, Block shoot) {
        BlockPos pos = context.getClickedPos();
        Level level = context.getLevel();
        BlockState state = level.getBlockState(pos);
        Player player = context.getPlayer();
        RandomSource randomSource = level.random;
        placeTrunk(pos, level, state, player, randomSource, maxTrunks, stack, trunk, shoot);
    }

    default void placeTrunk(BlockPos pos, Level level, BlockState state, Player player,  RandomSource randomSource, int maxTrunks, ItemStack stack, Block trunk, Block shoot) {
        if (!level.isClientSide) {
            int trunks = randomSource.nextInt(maxTrunks) + 1;
            boolean placed = false;

            for (int i = trunks; i > 0; i--) {
                if (canPlaceTrunks(level, pos, i)) {
                    placeTrunks(level, pos, i, trunk, shoot);
                    handleSounds(player, pos, state, level, stack);
                    placed = true;
                    break;
                }
            }

            if (!placed) {
                level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
            }
        }
    }

    default void placeTrunk(BlockPos pos, Level level, RandomSource randomSource, int maxTrunks, Block trunk, Block shoot) {
        if (!level.isClientSide) {
            int trunks = randomSource.nextInt(maxTrunks) + 1;
            boolean placed = false;

            for (int i = trunks; i > 0; i--) {
                if (canPlaceTrunks(level, pos, i)) {
                    placeTrunks(level, pos, i, trunk, shoot);
                    placed = true;
                    break;
                }
            }

            if (!placed) {
                level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
            }
        }
    }

    private boolean canPlaceTrunks(Level level, BlockPos pos, int trunks) {
        for (int i = 1; i <= trunks + 1; i++) {
            if (!level.isEmptyBlock(pos.above(i))) {
                return false;
            }
        }
        return true;
    }

    private void placeTrunks(Level level, BlockPos pos, int trunks, Block trunk, Block shoot) {
        BlockState trunkState = trunk.defaultBlockState();
        BlockState shootState = shoot.defaultBlockState();

        for (int i = 1; i <= trunks; i++) {
            level.setBlock(pos.above(i - 1), trunkState, 3);
        }

        level.setBlock(pos.above(trunks), shootState, 3);
    }

    default void handleSounds(Player player, BlockPos pos, BlockState state, Level level, ItemStack stack) {
        handleSounds(player, pos, state, level, stack, SoundEvents.GRASS_PLACE);
    }

    default void handleSounds(Player player, BlockPos pos, BlockState state, Level level, ItemStack stack, SoundEvent soundEvent) {
        SoundType soundtype = level.getBlockState(pos).getSoundType(level, pos, player);
        level.playSound(player, pos, soundEvent, SoundSource.BLOCKS, (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);
        level.gameEvent(GameEvent.BLOCK_PLACE, pos, GameEvent.Context.of(player, state));
        if(player instanceof ServerPlayer) {
            CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayer)player, pos, stack);
        }
    }
}