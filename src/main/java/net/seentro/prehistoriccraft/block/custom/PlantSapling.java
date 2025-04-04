package net.seentro.prehistoriccraft.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.seentro.prehistoriccraft.block.ModBlocks;
import net.seentro.prehistoriccraft.util.MultiblockPlantUtil;

public class PlantSapling extends BushBlock implements BonemealableBlock, MultiblockPlantUtil {
    public static final IntegerProperty STAGE = BlockStateProperties.STAGE;
    protected static final float AABB_OFFSET = 6.0F;
    protected static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D);
    private final int plantType;

    public PlantSapling(int plantType, BlockBehaviour.Properties pProperties) {
        super(pProperties);
        this.plantType = plantType;
        this.registerDefaultState(this.stateDefinition.any().setValue(STAGE, Integer.valueOf(0)));
    }

    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    /**
     * Performs a random tick on a block.
     */
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (!pLevel.isAreaLoaded(pPos, 1)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light
        if (pLevel.getMaxLocalRawBrightness(pPos.above()) >= 9 && pRandom.nextInt(7) == 0) {
            this.growPlant(pLevel, pPos, pState, pRandom);
        }

    }

    public void growPlant(ServerLevel level, BlockPos pos, BlockState state, RandomSource randomSource) {
        if (state.getValue(STAGE) == 0) {
            level.setBlock(pos, state.cycle(STAGE), 4);
        } else if (!level.isClientSide) {
            switch (plantType) {
                case 0 -> placeTrunk(pos, level, randomSource, 3, ModBlocks.PACHYPTERIS_TRUNK.get(), ModBlocks.PACHYPTERIS_SHOOT.get());
                case 1 -> placeTrunk(pos, level, randomSource, 4, ModBlocks.NEUROPTERIS_TRUNK.get(), ModBlocks.NEUROPTERIS_SHOOT.get());
                case 2 -> placeTrunk(pos, level, randomSource, 5, ModBlocks.PARACYCAS_TRUNK.get(), ModBlocks.PARACYCAS_SHOOT.get());
            }
        }
    }


    /**
     * @return whether bonemeal can be used on this block
     */
    public boolean isValidBonemealTarget(LevelReader pLevel, BlockPos pPos, BlockState pState, boolean pIsClient) {
        return true;
    }

    public boolean isBonemealSuccess(Level pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        return (double)pLevel.random.nextFloat() < 0.45D;
    }

    public void performBonemeal(ServerLevel pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        this.growPlant(pLevel, pPos, pState, pRandom);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(STAGE);
    }
}
