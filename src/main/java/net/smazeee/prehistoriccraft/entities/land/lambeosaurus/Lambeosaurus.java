package net.smazeee.prehistoriccraft.entities.land.lambeosaurus;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.level.Level;
import net.smazeee.prehistoriccraft.entities.ModEntityTypes;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;

public class Lambeosaurus extends Animal implements GeoEntity {
    private static final EntityDataAccessor<Boolean> IS_DRINKING = SynchedEntityData.defineId(Lambeosaurus.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IS_EATING = SynchedEntityData.defineId(Lambeosaurus.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IS_SLEEPING = SynchedEntityData.defineId(Lambeosaurus.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IS_CLEANING = SynchedEntityData.defineId(Lambeosaurus.class, EntityDataSerializers.BOOLEAN);

    private static final EntityDataAccessor<Integer> HYDRATION_LEVEL = SynchedEntityData.defineId(Lambeosaurus.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> NUTRITION_LEVEL = SynchedEntityData.defineId(Lambeosaurus.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> CLEANLINESS_LEVEL = SynchedEntityData.defineId(Lambeosaurus.class, EntityDataSerializers.INT);

    public Lambeosaurus(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return null;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(IS_DRINKING, false);
        entityData.define(IS_EATING, false);
        entityData.define(IS_SLEEPING, false);
        entityData.define(IS_CLEANING, false);
        entityData.define(HYDRATION_LEVEL, 100);
        entityData.define(NUTRITION_LEVEL, 100);
        entityData.define(CLEANLINESS_LEVEL, 100);
    }

    /* GECKOLIB */

    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> animationState) {
        boolean isAlreadyDrinking = false;
        boolean isAlreadyEating = false;
        boolean isAlreadySleeping = false;
        boolean isAlreadyCleaning = false;

        if (isDrinking() && !isAlreadyDrinking) {
            isAlreadyDrinking = true;
            animationState.getController().setAnimation(RawAnimation.begin().then("drink", Animation.LoopType.LOOP));
            this.addToHydrationLevel(20);
            //animationState.getController().setAnimation(RawAnimation.begin().then("drink_start", Animation.LoopType.PLAY_ONCE));
            //animationState.getController().setAnimation(RawAnimation.begin().then("drink", Animation.LoopType.LOOP));
            //if (getHydrationLevel() > 80) {
                //animationState.getController().setAnimation(RawAnimation.begin().then("drink_stop", Animation.LoopType.PLAY_ONCE));
                isAlreadyDrinking = false;
            //}
            return PlayState.CONTINUE;
        } else if (isEating() && !isAlreadyEating) {
            isAlreadyEating = true;
            animationState.getController().setAnimation(RawAnimation.begin().then("eat", Animation.LoopType.LOOP));
            this.addToNutritionLevel(20);
            //animationState.getController().setAnimation(RawAnimation.begin().then("eat_start", Animation.LoopType.PLAY_ONCE));
            //animationState.getController().setAnimation(RawAnimation.begin().then("eat", Animation.LoopType.LOOP));
            //if (getNutritionLevel() > 80) {
            //animationState.getController().setAnimation(RawAnimation.begin().then("eat_stop", Animation.LoopType.PLAY_ONCE));
            isAlreadyEating = false;
            //}
            return PlayState.CONTINUE;
        }
        if (getIsSleeping() && !isAlreadySleeping) {
            isAlreadySleeping = true;
            animationState.getController().setAnimation(RawAnimation.begin().then("sleep", Animation.LoopType.LOOP));
            //animationState.getController().setAnimation(RawAnimation.begin().then("sleep_start", Animation.LoopType.PLAY_ONCE));
            //animationState.getController().setAnimation(RawAnimation.begin().then("sleep", Animation.LoopType.LOOP));
            //if (getSleepingLevel() > 100) {
            //animationState.getController().setAnimation(RawAnimation.begin().then("sleep_stop", Animation.LoopType.PLAY_ONCE));
            isAlreadySleeping = false;
            //}
            return PlayState.CONTINUE;
        }
        if (isCleaning() && !isAlreadyCleaning) {
            isAlreadyCleaning = true;
            animationState.getController().setAnimation(RawAnimation.begin().then("clean", Animation.LoopType.LOOP));
            //animationState.getController().setAnimation(RawAnimation.begin().then("drink_start", Animation.LoopType.PLAY_ONCE));
            //animationState.getController().setAnimation(RawAnimation.begin().then("drink", Animation.LoopType.LOOP));
            //if (getCleanlinessLevel() > 100) {
            //animationState.getController().setAnimation(RawAnimation.begin().then("drink_stop", Animation.LoopType.PLAY_ONCE));
            isAlreadyCleaning = false;
            //}
            return PlayState.CONTINUE;
        }

        if(animationState.isMoving()) {
            animationState.getController().setAnimation(RawAnimation.begin().then("walk", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }

        animationState.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    /* GETS AND SETS */

    public boolean isDrinking() {
        return this.entityData.get(IS_DRINKING);
    }
    public boolean isEating() {
        return this.entityData.get(IS_DRINKING);
    }
    public boolean getIsSleeping() {
        return this.entityData.get(IS_SLEEPING);
    }
    public boolean isCleaning() {
        return this.entityData.get(IS_CLEANING);
    }
    public void setDrinkingStatus(boolean isDrinking) {
        entityData.set(IS_DRINKING, isDrinking);
    }
    public void setEatingStatus(boolean isEating) {
        entityData.set(IS_EATING, isEating);
    }
    public void setSleepingStatus(boolean isSleeping) {
        entityData.set(IS_SLEEPING, isSleeping);
    }
    public void setCleaningStatus(boolean isCleaning) {
        entityData.set(IS_CLEANING, isCleaning);
    }
    public int getHydrationLevel() {
        return entityData.get(HYDRATION_LEVEL);
    }
    public int getNutritionLevel() {
        return entityData.get(NUTRITION_LEVEL);
    }
    public int getCleanliness() {
        return entityData.get(CLEANLINESS_LEVEL);
    }
    public void setHydrationLevel(int hydrationLevel) {
        entityData.set(HYDRATION_LEVEL, hydrationLevel);
    }
    public void setNutritionLevel(int nutritionLevel) {
        entityData.set(NUTRITION_LEVEL, nutritionLevel);
    }
    public void setCleanliness(int cleanliness) {
        entityData.set(CLEANLINESS_LEVEL, cleanliness);
    }
    public void addToHydrationLevel(int amountToAdd) {entityData.set(HYDRATION_LEVEL, getHydrationLevel() + amountToAdd);}
    public void addToNutritionLevel(int amountToAdd) {entityData.set(NUTRITION_LEVEL, getNutritionLevel() + amountToAdd);}
    public void addToCleanlinessLevel(int amountToAdd) {entityData.set(CLEANLINESS_LEVEL, getCleanliness() + amountToAdd);}
}