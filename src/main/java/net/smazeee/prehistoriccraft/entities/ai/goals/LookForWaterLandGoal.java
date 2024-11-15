package net.smazeee.prehistoriccraft.entities.ai.goals;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.smazeee.prehistoriccraft.PrehistoricCraft;
import net.smazeee.prehistoriccraft.util.SearchingUtil;

public class LookForWaterLandGoal extends Goal implements SearchingUtil {
    private final Mob entity;
    private final int thirstyAt;
    private final int radius;
    private final boolean overrideCanUse;
    private BlockPos targetPos;

    public LookForWaterLandGoal(Mob entity, int hungryAt, int radius, boolean overrideCanUse) {
        this.entity = entity;
        this.thirstyAt = hungryAt;
        this.radius = radius;
        this.overrideCanUse = overrideCanUse;
    }

    @Override
    public boolean canUse() {
        if (isWaterInRadius(entity.level(), entity.blockPosition(), radius) != null || overrideCanUse) {
            targetPos = isWaterInRadius(entity.level(), entity.blockPosition(), radius);
            return true;
        }
        return false; //entity.getMaxWaterLevel() - entity.getWaterLevel() == thirstyAt;
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