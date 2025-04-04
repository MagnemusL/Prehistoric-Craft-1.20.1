package net.seentro.prehistoriccraft.entities.ai.goals;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.seentro.prehistoriccraft.PrehistoricCraft;

import java.util.EnumSet;
import java.util.Random;

public class GoToBottomFishGoal extends Goal {
    private final Mob fish;
    private final Level level;
    private double speed;
    private BlockPos targetPos;

    public GoToBottomFishGoal(Mob fish) {
        this.fish = fish;
        this.level = fish.level();
        this.speed = 0.5D;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public void stop() {
        this.fish.getNavigation().stop();
        super.stop();
    }

    @Override
    public boolean canUse() {
        return this.fish.isInWater();
    }

    @Override
    public void start() {
        this.targetPos = findBottomPos(this.fish.blockPosition());
        this.fish.getNavigation().moveTo(this.targetPos.getX(), this.targetPos.getY(), this.targetPos.getZ(), this.speed);
        PrehistoricCraft.LOGGER.info(String.valueOf(targetPos));
    }

    @Override
    public void tick() {
        if (this.targetPos != null) {
            this.fish.getNavigation().moveTo(this.targetPos.getX(), this.targetPos.getY(), this.targetPos.getZ(), this.speed);
        }
    }

    @Override
    public boolean canContinueToUse() {
        return this.targetPos != null && this.fish.isInWater() && !this.fish.blockPosition().equals(this.targetPos);
    }

    private BlockPos findBottomPos(BlockPos pos) {
        while (level.getBlockState(pos.below()) == Blocks.WATER.defaultBlockState()) {
            pos = pos.below();
        }
        pos = pos.above();
        Random random = new Random();
        return new BlockPos(pos.getX(), pos.getY() + random.nextInt(1), pos.getZ());
    }
}