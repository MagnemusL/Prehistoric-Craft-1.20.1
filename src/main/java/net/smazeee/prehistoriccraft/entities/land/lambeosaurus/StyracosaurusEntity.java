package net.smazeee.prehistoriccraft.entities.land.lambeosaurus;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.smazeee.prehistoriccraft.entities.LandDinosaur;
import net.smazeee.prehistoriccraft.util.Age;
import net.smazeee.prehistoriccraft.util.Gender;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;

import java.util.Random;

public class StyracosaurusEntity extends LandDinosaur implements GeoEntity {
    public Gender gender;
    public Age age = Age.JUVENILE;

    public StyracosaurusEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier setAttributes() {
        return LandDinosaur.createMobAttributes().add(Attributes.MAX_HEALTH, 30D).add(Attributes.MOVEMENT_SPEED, 3f).build();
    }

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
        if (isScared()) {
            setRunningStatus(true);
        } else {
            setRunningStatus(false);
        }
        addToTirednessLevel(0.5f);
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        Random random = new Random();
        gender = random.nextBoolean() ? Gender.MALE : Gender.FEMALE;
        return pSpawnData;
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
            animationState.getController().setAnimation(RawAnimation.begin().then("walk", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }
        if (isRunning()) {
            animationState.getController().setAnimation(RawAnimation.begin().then("run", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }

        animationState.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}