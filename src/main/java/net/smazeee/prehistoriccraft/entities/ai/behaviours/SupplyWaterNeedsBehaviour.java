package net.smazeee.prehistoriccraft.entities.ai.behaviours;

import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.ai.memory.WalkTarget;
import net.minecraft.world.phys.Vec3;
import net.smazeee.prehistoriccraft.PrehistoricCraft;
import net.smazeee.prehistoriccraft.entities.LandDinosaur;
import net.smazeee.prehistoriccraft.entities.ai.ShoreRandomPos;
import net.smazeee.prehistoriccraft.entities.land.styracosaurus.Styracosaurus;
import net.tslat.smartbrainlib.api.core.behaviour.ExtendedBehaviour;
import net.tslat.smartbrainlib.util.BrainUtils;

import java.util.List;

public class SupplyWaterNeedsBehaviour<E extends LandDinosaur> extends ExtendedBehaviour<E> {
    private static final List<Pair<MemoryModuleType<?>, MemoryStatus>> MEMORY_REQUIREMENTS;

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
    }
}
