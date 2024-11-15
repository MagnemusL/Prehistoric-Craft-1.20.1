package net.smazeee.prehistoriccraft.entities.land.styracosaurus;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Dynamic;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.behavior.RandomLookAround;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.schedule.Activity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.smazeee.prehistoriccraft.entities.LandDinosaur;
import net.smazeee.prehistoriccraft.entities.ai.behaviours.SupplyWaterNeedsBehaviour;
import net.smazeee.prehistoriccraft.util.Gender;
import net.tslat.smartbrainlib.api.SmartBrainOwner;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.behaviour.custom.look.LookAtTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.MoveToWalkTarget;
import net.tslat.smartbrainlib.api.core.sensor.ExtendedSensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.HurtBySensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.NearbyLivingEntitySensor;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class StyracosaurusEntity extends LandDinosaur implements GeoEntity, SmartBrainOwner<StyracosaurusEntity> {
    public StyracosaurusEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier setAttributes() {
        return LandDinosaur.createMobAttributes().add(Attributes.MAX_HEALTH, 30D).add(Attributes.MOVEMENT_SPEED, 3f).build();
    }

//    protected void registerGoals() {
//        this.goalSelector.addGoal(0, new FloatGoal(this));
//        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
//        this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.1D));
//        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
//        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
//        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
//    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return null;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
    }

    @Override
    public void tick() {
        /*if (isScared()) {
            setRunningStatus(true);
        } else {
            setRunningStatus(false);
        }
        addToTirednessLevel(0.05f);
         */
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
        //Random random = new Random();
        //setGender(random.nextBoolean() ? Gender.MALE : Gender.FEMALE);
        return pSpawnData;
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        setGender(pCompound.getBoolean("Gender") ? Gender.MALE : Gender.FEMALE);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putBoolean("Gender", getGender());
    }

    /* GECKOLIB */
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> animationState) {
        if (isDrinking()) {
            animationState.getController().setAnimation(RawAnimation.begin().then("drinking", Animation.LoopType.LOOP));
            addToHydrationLevel(10);
            return PlayState.CONTINUE;
        }
        if (isEating()) {
            animationState.getController().setAnimation(RawAnimation.begin().then("eating", Animation.LoopType.LOOP));
            addToNutritionLevel(10);
            return PlayState.CONTINUE;
        }
        if (isSleeping() && getTirednessLevel() > 50) {
            animationState.getController().setAnimation(RawAnimation.begin().then("layingdown", Animation.LoopType.PLAY_ONCE)
                    .then("sleeping", Animation.LoopType.LOOP));
            setSleepingStatus(true);
            removeFromTirednessLevel(10);
            return PlayState.CONTINUE;
        } else if (isSleeping() && getTirednessLevel() < 30) {
            setSleepingStatus(false);
            animationState.getController().setAnimation(RawAnimation.begin().then("gettingup", Animation.LoopType.PLAY_ONCE));
            return PlayState.CONTINUE;
        }
        if (animationState.isMoving()) {
            animationState.getController().setAnimation(RawAnimation.begin().then("walking", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }
        if (isRunning()) {
            animationState.getController().setAnimation(RawAnimation.begin().then("run", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }

        animationState.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }

    /* BRAIN */

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public List<? extends ExtendedSensor<StyracosaurusEntity>> getSensors() {
        return ObjectArrayList.of(
                new NearbyLivingEntitySensor<>(),
                new HurtBySensor<>()
        );
    }

    @Override
    public BrainActivityGroup<StyracosaurusEntity> getCoreTasks() {
        return BrainActivityGroup.coreTasks(
                new LookAtTarget<>(),
                new MoveToWalkTarget<>()
        );
    }

    @Override
    public BrainActivityGroup<StyracosaurusEntity> getIdleTasks() {
        return BrainActivityGroup.idleTasks(
                new LookAtTarget<>(),
                new SupplyWaterNeedsBehaviour<>()
        );
    }

    @Override
    public BrainActivityGroup<StyracosaurusEntity> getFightTasks() {
        return BrainActivityGroup.fightTasks();
    }
}