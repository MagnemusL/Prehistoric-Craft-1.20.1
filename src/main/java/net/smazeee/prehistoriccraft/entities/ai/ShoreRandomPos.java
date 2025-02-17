package net.smazeee.prehistoriccraft.entities.ai;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.util.GoalUtils;
import net.minecraft.world.entity.ai.util.RandomPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.function.ToDoubleFunction;

public class ShoreRandomPos {
    @Nullable
    public static Vec3 getPos(PathfinderMob pMob, int pRadius, int pVerticalRange) {
        return getPos(pMob, pRadius, pVerticalRange, pMob::getWalkTargetValue);
    }

    @Nullable
    public static Vec3 getPos(PathfinderMob pMob, int pRadius, int pYRange, ToDoubleFunction<BlockPos> pToDoubleFunction) {
        boolean flag = GoalUtils.mobRestricted(pMob, pRadius);
        return RandomPos.generateRandomPos(() -> {
            BlockPos blockpos = RandomPos.generateRandomDirection(pMob.getRandom(), pRadius, pYRange);
            BlockPos blockpos1 = generateRandomPosTowardDirection(pMob, pRadius, flag, blockpos);
            return blockpos1 == null ? null : movePosUpOutOfSolid(pMob, blockpos1);
        }, pToDoubleFunction);
    }

    @Nullable
    public static BlockPos movePosUpOutOfSolid(PathfinderMob pMob, BlockPos pPos) {
        pPos = RandomPos.moveUpOutOfSolid(pPos, pMob.level().getMaxBuildHeight(), (p_148534_) -> {
            return GoalUtils.isSolid(pMob, p_148534_);
        });
        return isShore(pMob, pPos) && !GoalUtils.hasMalus(pMob, pPos) ? pPos : null;
    }

    private static boolean isShore(PathfinderMob mob, BlockPos pos) {
        Level level = mob.level();
        return true;//level.getFluidState(pos).isEmpty() && level.getFluidState(pos).is(FluidTags.WATER);
    }

    @Nullable
    public static BlockPos generateRandomPosTowardDirection(PathfinderMob pMob, int pRadius, boolean pShortCircuit, BlockPos pPos) {
        BlockPos blockpos = RandomPos.generateRandomPosTowardDirection(pMob, pRadius, pMob.getRandom(), pPos);
        return !GoalUtils.isOutsideLimits(blockpos, pMob) && !GoalUtils.isRestricted(pShortCircuit, pMob, blockpos) && !GoalUtils.isNotStable(pMob.getNavigation(), blockpos) ? blockpos : null;
    }
}
