package net.seentro.prehistoriccraft.plants;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.seentro.prehistoriccraft.util.BlockPosListEntry;
import net.seentro.prehistoriccraft.util.TerritorySavedData;

import java.util.ArrayList;
import java.util.List;

public class Belemnopteris extends BushBlock {
    protected static final float AABB_OFFSET = 3.0F;
    protected static final VoxelShape SHAPE = Block.box(5.0D, 0.0D, 5.0D, 11.0D, 10.0D, 11.0D);
    public static List<BlockPos> TEMP_POS_LIST = new ArrayList<>();

    public Belemnopteris(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        Vec3 vec3 = state.getOffset(getter, pos);
        return SHAPE.move(vec3.x, 0, vec3.z);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide && hand == InteractionHand.MAIN_HAND) {
            TerritorySavedData savedData = TerritorySavedData.get((ServerLevel) level);
            List<BlockPosListEntry> entries = savedData.getEntries();
            if (player.isShiftKeyDown()) {
                // Shift-right-click: Retrieve and show the data
                player.displayClientMessage(Component.literal("Entries: " + entries), true);
            } else {
                // Right-click: Add a new BlockPos list entry
                List<BlockPos> newBlockPosList = new ArrayList<>();
                newBlockPosList.add(pos);
                savedData.addBlockPosListEntry(entries.size() + 1, newBlockPosList); // Using entries.size() as a simple ID
                savedData.setDirty();
                player.displayClientMessage(Component.literal("Added new entry with BlockPos: " + pos), true);
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }
}
