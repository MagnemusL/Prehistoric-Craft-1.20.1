package net.smazeee.prehistoriccraft.entities.ai.goals;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.block.Block;
import net.smazeee.prehistoriccraft.PrehistoricCraft;
import net.smazeee.prehistoriccraft.util.SearchingUtil;

import java.util.List;

public class LookForFoodLandGoal extends Goal implements SearchingUtil {
    private final Mob entity;
    private final int hungryAt;
    private final int radius;
    private final List<Block> food;
    private final boolean overrideCanUse;
    private BlockPos targetPos;

    public LookForFoodLandGoal(Mob entity, int hungryAt, int radius, List<Block> food, boolean overrideCanUse) {
        this.entity = entity;
        this.hungryAt = hungryAt;
        this.radius = radius;
        this.food = food;
        this.overrideCanUse = overrideCanUse;
    }

    @Override
    public boolean canUse() {
        for (Block food : food) {
            if (isBlockInRadius(entity.level(), entity.blockPosition(), radius, food) != null || overrideCanUse) {
                targetPos = isBlockInRadius(entity.level(), entity.blockPosition(), radius, food);
                return true;
            }
        }
        return false; //entity.getMaxFoodLevel() - entity.getFoodLevel() == hungryAt;
    }

    @Override
    public void stop() {
        this.entity.getNavigation().stop();
        super.stop();
    }

    @Override
    public void start() {
        this.entity.getNavigation().moveTo(this.targetPos.getX(), this.targetPos.getY(), this.targetPos.getZ(), 20D);
        PrehistoricCraft.LOGGER.info(String.valueOf(targetPos));
    }

    @Override
    public boolean canContinueToUse() {
        return canUse();
    }
}