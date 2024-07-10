package net.smazeee.prehistoriccraft.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundSetActionBarTextPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.smazeee.prehistoriccraft.PrehistoricCraft;
import net.smazeee.prehistoriccraft.block.ModBlocks;
import net.smazeee.prehistoriccraft.util.MultiblockUtil;
import net.smazeee.prehistoriccraft.util.TrunkPlacerUtil;

import java.awt.*;

public class ModGrassBlock extends Block implements MultiblockUtil, TrunkPlacerUtil {
    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.AXIS;

    public ModGrassBlock(Properties properties) {
        super(properties.sound(SoundType.GRASS));
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pPlayer.isCrouching()) {
            boolean canMultiblockForm = switch (pHit.getDirection()) {
                case NORTH -> areAllBlocksInCube(pLevel, pPos.south(), ModBlocks.ORGANIC_LOAMY_DIRT.get(), 3, 3, 1);
                case SOUTH -> areAllBlocksInCube(pLevel, pPos.north(), ModBlocks.ORGANIC_LOAMY_DIRT.get(), 3, 3, 1);
                case WEST -> areAllBlocksInCube(pLevel, pPos.east(), ModBlocks.ORGANIC_LOAMY_DIRT.get(), 3, 3, 1);
                case EAST -> areAllBlocksInCube(pLevel, pPos.west(), ModBlocks.ORGANIC_LOAMY_DIRT.get(), 3, 3, 1);
                default -> false;
            };
            if (canMultiblockForm && !pLevel.isClientSide()) {
                Component textComponent = Component.literal("Multiblock formed at " + pPos);
                ClientboundSetActionBarTextPacket packet = new ClientboundSetActionBarTextPacket(textComponent);
                ((ServerPlayer) pPlayer).connection.send(packet);
                PrehistoricCraft.LOGGER.info("Formed Multiblock");
                for (int i = 0; i < 20; i++) {
                    for (int x = 0; x < 10; x++) {
                        switch (pHit.getDirection()) {
                            case NORTH -> {
                                pLevel.setBlockAndUpdate(pPos.above(i - 1).west(x).south(), ModBlocks.ORGANIC_LOAMY_DIRT.get().defaultBlockState());
                                pLevel.setBlockAndUpdate(pPos.above(i - 1).east(x).south(), ModBlocks.ORGANIC_LOAMY_DIRT.get().defaultBlockState());
                            }
                            case WEST -> {
                                pLevel.setBlockAndUpdate(pPos.above(i - 1).north(x).east(), ModBlocks.ORGANIC_LOAMY_DIRT.get().defaultBlockState());
                                pLevel.setBlockAndUpdate(pPos.above(i - 1).south(x).east(), ModBlocks.ORGANIC_LOAMY_DIRT.get().defaultBlockState());
                            }
                            case SOUTH -> {
                                pLevel.setBlockAndUpdate(pPos.above(i - 1).west(x).north(), ModBlocks.ORGANIC_LOAMY_DIRT.get().defaultBlockState());
                                pLevel.setBlockAndUpdate(pPos.above(i - 1).east(x).north(), ModBlocks.ORGANIC_LOAMY_DIRT.get().defaultBlockState());
                            }
                            case EAST -> {
                                pLevel.setBlockAndUpdate(pPos.above(i - 1).south(x).west(), ModBlocks.ORGANIC_LOAMY_DIRT.get().defaultBlockState());
                                pLevel.setBlockAndUpdate(pPos.above(i - 1).north(x).west(), ModBlocks.ORGANIC_LOAMY_DIRT.get().defaultBlockState());
                            }
                        }
                    }
                }
            }
        } else {
            switch (pHit.getDirection()) {
                case NORTH -> pLevel.setBlockAndUpdate(pPos.north(), pState);
                case WEST -> pLevel.setBlockAndUpdate(pPos.west(), pState);
                case SOUTH -> pLevel.setBlockAndUpdate(pPos.south(), pState);
                case EAST -> pLevel.setBlockAndUpdate(pPos.east(), pState);
                case UP -> pLevel.setBlockAndUpdate(pPos.above(), pState);
                case DOWN -> pLevel.setBlockAndUpdate(pPos.below(), pState);
            }
            handleSounds(pPlayer, pPos, pState, pLevel, new ItemStack(this), SoundType.GRASS.getPlaceSound());
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AXIS);
    }
}
