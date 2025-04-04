package net.seentro.prehistoriccraft.entities.ai.behaviours;

import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.ai.memory.WalkTarget;
import net.minecraft.world.phys.Vec3;
import net.seentro.prehistoriccraft.entities.ai.ShoreRandomPos;
import net.tslat.smartbrainlib.api.core.behaviour.ExtendedBehaviour;
import net.tslat.smartbrainlib.object.SquareRadius;
import net.tslat.smartbrainlib.util.BrainUtils;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

public class SupplyWaterNeedsBehaviour<E extends PathfinderMob> extends ExtendedBehaviour<E> {
    /*private static final List<Pair<MemoryModuleType<?>, MemoryStatus>> MEMORY_REQUIREMENTS;

    @Override
    protected List<Pair<MemoryModuleType<?>, MemoryStatus>> getMemoryRequirements() {
        return MEMORY_REQUIREMENTS;
    }

    @Override
    protected void start(E entity) {
        Vec3 targetPos = ShoreRandomPos.getPos(entity, 20, 10);
        PrehistoricCraft.LOGGER.info(String.valueOf(targetPos));
        BrainUtils.setMemory(entity, MemoryModuleType.WALK_TARGET, new WalkTarget(targetPos, 1, 1));
    }

    static {
        MEMORY_REQUIREMENTS = ObjectArrayList.of(new Pair[]{
                Pair.of(MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE, MemoryStatus.REGISTERED),
                Pair.of(MemoryModuleType.PATH, MemoryStatus.VALUE_ABSENT),
                Pair.of(MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_PRESENT)});
    }*/

private static final List<Pair<MemoryModuleType<?>, MemoryStatus>> MEMORY_REQUIREMENTS;
protected BiFunction<E, Vec3, Float> speedModifier = (entity, targetPos) -> 1.0F;
protected SquareRadius radius = new SquareRadius((double)10.0F, (double)7.0F);
protected BiPredicate<E, Vec3> positionPredicate = (entity, pos) -> true;

public SupplyWaterNeedsBehaviour() {
}

protected List<Pair<MemoryModuleType<?>, MemoryStatus>> getMemoryRequirements() {
    return MEMORY_REQUIREMENTS;
}

public SupplyWaterNeedsBehaviour<E> setRadius(double radius) {
    return this.setRadius(radius, radius);
}

public SupplyWaterNeedsBehaviour<E> setRadius(double xz, double y) {
    this.radius = new SquareRadius(xz, y);
    return this;
}

public SupplyWaterNeedsBehaviour<E> speedModifier(float modifier) {
    return this.speedModifier((entity, targetPos) -> modifier);
}

public SupplyWaterNeedsBehaviour<E> speedModifier(BiFunction<E, Vec3, Float> function) {
    this.speedModifier = function;
    return this;
}

public SupplyWaterNeedsBehaviour<E> walkTargetPredicate(BiPredicate<E, Vec3> predicate) {
    this.positionPredicate = predicate;
    return this;
}

protected void start(E entity) {
    Vec3 targetPos = this.getTargetPos(entity);
    if (!this.positionPredicate.test(entity, targetPos)) {
        targetPos = null;
    }

    if (targetPos == null) {
        BrainUtils.clearMemory(entity, MemoryModuleType.WALK_TARGET);
    } else {
        BrainUtils.setMemory(entity, MemoryModuleType.WALK_TARGET, new WalkTarget(targetPos, (Float)this.speedModifier.apply(entity, targetPos), 0));
    }

}

protected @Nullable Vec3 getTargetPos(E entity) {
    return ShoreRandomPos.getPos(entity, (int)this.radius.xzRadius(), (int)this.radius.yRadius());
}

static {
    MEMORY_REQUIREMENTS = ObjectArrayList.of(new Pair[]{Pair.of(MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT)});
}
}
