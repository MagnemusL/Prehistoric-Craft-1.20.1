package net.smazeee.prehistoriccraft.entities;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import net.smazeee.prehistoriccraft.util.Age;
import net.smazeee.prehistoriccraft.util.Gender;
import org.jetbrains.annotations.Nullable;

public class LandDinosaur extends Animal {
    private static final EntityDataAccessor<Boolean> IS_DRINKING = SynchedEntityData.defineId(LandDinosaur.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IS_EATING = SynchedEntityData.defineId(LandDinosaur.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IS_SLEEPING = SynchedEntityData.defineId(LandDinosaur.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IS_CLEANING = SynchedEntityData.defineId(LandDinosaur.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IS_RUNNING = SynchedEntityData.defineId(LandDinosaur.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IS_SCARED = SynchedEntityData.defineId(LandDinosaur.class, EntityDataSerializers.BOOLEAN);

    private static final EntityDataAccessor<Integer> HYDRATION_LEVEL = SynchedEntityData.defineId(LandDinosaur.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> NUTRITION_LEVEL = SynchedEntityData.defineId(LandDinosaur.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Float> TIREDNESS_LEVEL = SynchedEntityData.defineId(LandDinosaur.class, EntityDataSerializers.FLOAT);
    private static final EntityDataAccessor<Integer> CLEANLINESS_LEVEL = SynchedEntityData.defineId(LandDinosaur.class, EntityDataSerializers.INT);

    public LandDinosaur(EntityType<? extends Animal> pEntityType, Level pLevel) {
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
        entityData.define(IS_RUNNING, false);
        entityData.define(IS_SCARED, false);
        entityData.define(HYDRATION_LEVEL, 100);
        entityData.define(NUTRITION_LEVEL, 100);
        entityData.define(TIREDNESS_LEVEL, 0f);
        entityData.define(CLEANLINESS_LEVEL, 100);
    }

    @Override
    public void tick() {
        if (isRunning()) {
            this.setSpeed(6f);
        } else {
            this.setSpeed(3f);
        }
    }

    public boolean isDrinking() {return this.entityData.get(IS_DRINKING);}
    public boolean isEating() {return this.entityData.get(IS_DRINKING);}
    public boolean isSleeping() {return this.entityData.get(IS_SLEEPING);}
    public boolean isCleaning() {return this.entityData.get(IS_CLEANING);}
    public boolean isRunning() {return this.entityData.get(IS_RUNNING);}
    public boolean isScared() {return this.entityData.get(IS_SCARED);}
    public void setDrinkingStatus(boolean isDrinking) {entityData.set(IS_DRINKING, isDrinking);}
    public void setEatingStatus(boolean isEating) {entityData.set(IS_EATING, isEating);}
    public void setSleepingStatus(boolean isSleeping) {entityData.set(IS_SLEEPING, isSleeping);}
    public void setCleaningStatus(boolean isCleaning) {entityData.set(IS_CLEANING, isCleaning);}
    public void setRunningStatus(boolean isRunning) {entityData.set(IS_RUNNING, isRunning);}
    public void setScaredStatus(boolean isScared) {entityData.set(IS_SCARED, isScared);}
    public int getHydrationLevel() {return entityData.get(HYDRATION_LEVEL);}
    public int getNutritionLevel() {return entityData.get(NUTRITION_LEVEL);}
    public float getTirednessLevel() {return entityData.get(TIREDNESS_LEVEL);}
    public int getCleanliness() {return entityData.get(CLEANLINESS_LEVEL);}
    public void setHydrationLevel(int hydrationLevel) {entityData.set(HYDRATION_LEVEL, hydrationLevel);}
    public void setNutritionLevel(int nutritionLevel) {entityData.set(NUTRITION_LEVEL, nutritionLevel);}
    public void setTirednessLevel(float tirednessLevel) {entityData.set(TIREDNESS_LEVEL, tirednessLevel);}
    public void setCleanliness(int cleanliness) {entityData.set(CLEANLINESS_LEVEL, cleanliness);}
    public void addToHydrationLevel(int amountToAdd) {entityData.set(HYDRATION_LEVEL, getHydrationLevel() + amountToAdd);}
    public void addToNutritionLevel(int amountToAdd) {entityData.set(NUTRITION_LEVEL, getNutritionLevel() + amountToAdd);}
    public void addToTirednessLevel(float amountToAdd) {entityData.set(TIREDNESS_LEVEL, getTirednessLevel() + amountToAdd);}
    public void addToCleanlinessLevel(int amountToAdd) {entityData.set(CLEANLINESS_LEVEL, getCleanliness() + amountToAdd);}
    public void removeFromTirednessLevel(int amountToRemove) {entityData.set(TIREDNESS_LEVEL, getTirednessLevel() - amountToRemove);}
}