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
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.smazeee.prehistoriccraft.entities.LandDinosaur;
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
    public StyracosaurusEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier setAttributes() {
        return LandDinosaur.createMobAttributes().add(Attributes.MAX_HEALTH, 30D).add(Attributes.MOVEMENT_SPEED, 3f).build();
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
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
        addToTirednessLevel(0.05f);
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
        Random random = new Random();
        setGender(random.nextBoolean() ? Gender.MALE : Gender.FEMALE);
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

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
}