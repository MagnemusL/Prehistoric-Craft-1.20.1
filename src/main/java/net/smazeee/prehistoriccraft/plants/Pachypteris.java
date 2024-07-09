package net.smazeee.prehistoriccraft.plants;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class Pachypteris extends BushBlock {
    protected static final float AABB_OFFSET = 3.0F;
    protected static final VoxelShape SHAPE = Block.box(5.8D, 0.0D, 5.8D, 10.2D, 32.0D, 10.2D);

    public Pachypteris(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        Vec3 vec3 = state.getOffset(getter, pos);
        return SHAPE.move(vec3.x, 0, vec3.z);
    }
}
