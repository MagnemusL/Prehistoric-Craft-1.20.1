package net.smazeee.prehistoriccraft.entities.land.styracosaurus;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.schedule.Activity;
import net.minecraft.world.level.Level;
import net.smazeee.prehistoriccraft.PrehistoricCraft;
import net.smazeee.prehistoriccraft.entities.LandDinosaur;
import net.smazeee.prehistoriccraft.entities.ai.behaviours.SupplyWaterNeedsBehaviour;
import net.tslat.smartbrainlib.api.SmartBrainOwner;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.SmartBrainProvider;
import net.tslat.smartbrainlib.api.core.behaviour.FirstApplicableBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.OneRandomBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.custom.look.LookAtTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.Idle;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.FleeTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.MoveToWalkTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetRandomWalkTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.SetPlayerLookTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.SetRandomLookTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.TargetOrRetaliate;
import net.tslat.smartbrainlib.api.core.sensor.ExtendedSensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.HurtBySensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.NearbyLivingEntitySensor;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;

import java.util.List;
import java.util.Map;
import java.util.Random;

@SuppressWarnings("unchecked")
public class Styracosaurus extends LandDinosaur implements SmartBrainOwner<Styracosaurus>, GeoEntity {
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    private int idleToPlay = 1;

    public Styracosaurus(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier setAttributes() {
        return LandDinosaur.createMobAttributes().add(Attributes.MAX_HEALTH, 20D).add(Attributes.MOVEMENT_SPEED, 0.2f).build();
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        pCompound.putInt("idleToPlay", idleToPlay);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        idleToPlay = pCompound.getInt("idleToPlay");
    }

    @Override
    protected Brain.@NotNull Provider<?> brainProvider() {
        return new SmartBrainProvider<>(this);
    }

    @Override
    protected void customServerAiStep() {
        tickBrain(this);
    }

    @Override
    public List<ExtendedSensor<? extends Styracosaurus>> getSensors() {
        return ObjectArrayList.of(
                new NearbyLivingEntitySensor<>(),
                new HurtBySensor<>()
        );
    }

    @Override
    public BrainActivityGroup<Styracosaurus> getCoreTasks() {
        return BrainActivityGroup.coreTasks(
                new FleeTarget<>().speedModifier(2f).fleeDistance(20).startCondition(entity -> entity.getHealth() < 20),
                new LookAtTarget<>(),
                new MoveToWalkTarget<>(),
                new SupplyWaterNeedsBehaviour<>()//.startCondition(LandDinosaur::needsWater)
        );
    }

    @Override
    public BrainActivityGroup<? extends Styracosaurus> getIdleTasks() {
        return BrainActivityGroup.idleTasks(
                new FirstApplicableBehaviour<Styracosaurus>(
                        new SetPlayerLookTarget<>(),
                        new SetRandomLookTarget<>()),
                new OneRandomBehaviour<>(
                        new SetRandomWalkTarget<>(),
                        new Idle<>().runFor(entity -> entity.getRandom().nextInt(30, 100))));
    }

    @Override
    public BrainActivityGroup<? extends Styracosaurus> getFightTasks() {
        return SmartBrainOwner.super.getFightTasks();
    }

    @Override
    public Map<Activity, BrainActivityGroup<? extends Styracosaurus>> getAdditionalTasks() {
        return SmartBrainOwner.super.getAdditionalTasks();
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> animationState) {
        if(animationState.isMoving()) {
            idleToPlay = this.getRandom().nextInt(1, 4);
            return animationState.setAndContinue(RawAnimation.begin().thenLoop("walking"));
        }

        return switch (idleToPlay) {
            case 1 -> animationState.setAndContinue(RawAnimation.begin().thenLoop("idle"));
            case 2 -> animationState.setAndContinue(RawAnimation.begin().thenPlay("idle2").thenLoop("idle"));
            case 3 -> animationState.setAndContinue(RawAnimation.begin().then("idle3", Animation.LoopType.PLAY_ONCE).thenWait(20).thenLoop("idle"));
            default -> PlayState.CONTINUE;
        };
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}
