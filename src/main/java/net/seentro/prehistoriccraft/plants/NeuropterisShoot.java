package net.seentro.prehistoriccraft.plants;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.seentro.prehistoriccraft.PrehistoricCraft;
import net.seentro.prehistoriccraft.block.ModBlocks;
import net.seentro.prehistoriccraft.item.ModItems;

public class NeuropterisShoot extends BushBlock {
    protected static final VoxelShape SHAPE = Block.box(5.8D, 0.0D, 5.8D, 10.2D, 6.0D, 10.2D);

    public NeuropterisShoot(Properties properties) {
        super(properties);
    }

    @Override
    protected boolean mayPlaceOn(BlockState pState, BlockGetter pLevel, BlockPos pPos) {
        return pState.is(ModBlocks.NEUROPTERIS_TRUNK.get());
    }

    @Override
    public void playerWillDestroy(Level pLevel, BlockPos pPos, BlockState pState, Player pPlayer) {
        if (!pPlayer.isCreative()) {
            if (pPlayer.getItemInHand(InteractionHand.MAIN_HAND).getItem() == Items.SHEARS || pPlayer.getItemInHand(InteractionHand.OFF_HAND).getItem() == Items.SHEARS) {
                popResource(pLevel, pPos, new ItemStack(ModItems.NEUROPTERIS_SHOOT.get()));
            } else {
                popResource(pLevel, pPos, new ItemStack(ModBlocks.NEUROPTERIS_SAPLING.get()));
            }
        }
        super.playerWillDestroy(pLevel, pPos, pState, pPlayer);
    }

    @Override
    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        BlockPos blockpos = pPos.below();
        if (!pLevel.isClientSide() && !this.mayPlaceOn(pLevel.getBlockState(blockpos), pLevel, blockpos)) {
            ((LevelAccessor)pLevel).destroyBlock(pPos, false);
            PrehistoricCraft.LOGGER.info("Destroyed block at x: " + pPos.getX() + ", y: " + pPos.getY() + ", and z: " + pPos.getZ());
        }
        if (pState.getBlock() == this) //Forge: This function is called during world gen and placement, before this block is set, so if we are not 'here' then assume it's the pre-check.
            return pLevel.getBlockState(blockpos).canSustainPlant(pLevel, blockpos, Direction.UP, this);
        return this.mayPlaceOn(pLevel.getBlockState(blockpos), pLevel, blockpos);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
}
