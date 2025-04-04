package net.seentro.prehistoriccraft.entities.ai.goals;

import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class FishSwimGoal extends RandomStrollGoal {
    public FishSwimGoal(AbstractFish pFish) {
        super(pFish, 1.0D, 40);
    }

    @Nullable
    protected Vec3 getPosition() {
        return BehaviorUtils.getRandomSwimmablePos(this.mob, 10, 2);
    }

    public boolean canUse() {
            return super.canUse();
        }
}
