package net.smazeee.prehistoriccraft.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ForcedChunksSavedData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.smazeee.prehistoriccraft.PrehistoricCraft;
import net.smazeee.prehistoriccraft.block.custom.ExtractionMachineBlock;
import net.smazeee.prehistoriccraft.item.ModItems;
import net.smazeee.prehistoriccraft.recipe.AcidShowerRecipe;
import net.smazeee.prehistoriccraft.recipe.ExtractionMachineRecipe;
import net.smazeee.prehistoriccraft.screen.ExtractionMachineMenu;
import net.smazeee.prehistoriccraft.util.InventoryDirectionEntry;
import net.smazeee.prehistoriccraft.util.InventoryDirectionWrapper;
import net.smazeee.prehistoriccraft.util.ModEnergyStorage;
import net.smazeee.prehistoriccraft.util.WrappedHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.RenderUtils;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class ExtractionMachineBlockEntity extends BlockEntity implements MenuProvider, GeoBlockEntity {
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    private final ItemStackHandler itemHandler = new ItemStackHandler(5) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return switch (slot) {
                case BATTERY_SLOT -> stack.getItem() == ModItems.SULFUR.get();
                case INPUT_SLOT -> true;
                case OUTPUT_SLOT_1, OUTPUT_SLOT_2, OUTPUT_SLOT_3 -> false;
                default -> super.isItemValid(slot, stack);
            };
        }
    };

    private static final int BATTERY_SLOT = 0;
    private static final int INPUT_SLOT = 1;
    private static final int OUTPUT_SLOT_1 = 2;
    private static final int OUTPUT_SLOT_2 = 3;
    private static final int OUTPUT_SLOT_3 = 4;

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    private final Map<Direction, LazyOptional<WrappedHandler>> directionWrappedHandlerMap =
            new InventoryDirectionWrapper(itemHandler,
                    new InventoryDirectionEntry(Direction.DOWN, OUTPUT_SLOT_1, false),
                    new InventoryDirectionEntry(Direction.UP, INPUT_SLOT, true),
                    new InventoryDirectionEntry(Direction.NORTH, OUTPUT_SLOT_2, false),
                    new InventoryDirectionEntry(Direction.WEST, INPUT_SLOT, true),
                    new InventoryDirectionEntry(Direction.SOUTH, INPUT_SLOT, true),
                    new InventoryDirectionEntry(Direction.EAST, OUTPUT_SLOT_3, false)).directionsMap;

    private LazyOptional<IEnergyStorage> lazyEnergyHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 0;
    private boolean isCrafting = false;

    private final ModEnergyStorage ENERGY_STORAGE = createEnergyStorage();

    private ModEnergyStorage createEnergyStorage() {
        return new ModEnergyStorage(64000, 200) {
            @Override
            public void onEnergyChanged() {
                setChanged();
                getLevel().sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Block.UPDATE_ALL);
            }
        };
    }


    public ExtractionMachineBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.EXTRACTION_MACHINE_BE.get(), pos, state);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    default -> 0;
                    case 0 -> ExtractionMachineBlockEntity.this.progress;
                    case 1 -> ExtractionMachineBlockEntity.this.maxProgress;
                    case 2 -> ExtractionMachineBlockEntity.this.ENERGY_STORAGE.getEnergyStored();
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> ExtractionMachineBlockEntity.this.progress = value;
                    case 1 -> ExtractionMachineBlockEntity.this.maxProgress = getProcessingTime();
                    case 2 -> ExtractionMachineBlockEntity.this.ENERGY_STORAGE.setEnergy(getCurrentEnergy());
                }
            }

            @Override
            public int getCount() {
                return 3;
            }
        };
    }

    public IEnergyStorage getEnergyStorage() {
        return this.ENERGY_STORAGE;
    }

    public void drops() {
        SimpleContainer inv = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inv.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inv);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("blockentity.extraction_machine");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new ExtractionMachineMenu(containerId, inventory, this, this.data);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER) {
            if(side == null) {
                return lazyItemHandler.cast();
            }

            if(directionWrappedHandlerMap.containsKey(side)) {
                Direction localDir = this.getBlockState().getValue(ExtractionMachineBlock.FACING);

                if(side == Direction.DOWN || side == Direction.UP) {
                    return directionWrappedHandlerMap.get(side).cast();
                }

                return switch (localDir) {
                    default -> directionWrappedHandlerMap.get(side.getOpposite()).cast();
                    case EAST -> directionWrappedHandlerMap.get(side.getClockWise()).cast();
                    case SOUTH -> directionWrappedHandlerMap.get(side).cast();
                    case WEST -> directionWrappedHandlerMap.get(side.getCounterClockWise()).cast();
                };
            }
        }

        if(cap == ForgeCapabilities.ENERGY) {
            return lazyEnergyHandler.cast();
        }

        return super.getCapability(cap);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
        lazyEnergyHandler = LazyOptional.of(() -> ENERGY_STORAGE);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
        lazyEnergyHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("inv", itemHandler.serializeNBT());
        tag.putInt("progress", progress);
        tag.putInt("energy", ENERGY_STORAGE.getEnergyStored());
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        itemHandler.deserializeNBT(tag.getCompound("inv"));
        progress = tag.getInt("progress");
        ENERGY_STORAGE.setEnergy(tag.getInt("energy"));
    }

    /*SYNCING*/

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag nbt = super.getUpdateTag();
        var prehistoricCraftData = new CompoundTag();
        prehistoricCraftData.putBoolean("processing", this.isCrafting);
        nbt.put(PrehistoricCraft.MODID, prehistoricCraftData);
        return nbt;
    }

    @Override
    public void handleUpdateTag(CompoundTag nbt) {
        CompoundTag prehistoricCraftData = nbt.getCompound(PrehistoricCraft.MODID);
        this.isCrafting = prehistoricCraftData.getBoolean("processing");
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        handleUpdateTag(Objects.requireNonNull(pkt.getTag()));
    }

    /*GECKOLIB*/

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> tAnimationState) {
        if (getIsCrafting()) {
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("work", Animation.LoopType.DEFAULT));
            return PlayState.CONTINUE;
        }
        return PlayState.STOP;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public double getTick(Object blockEntity) {
        return RenderUtils.getCurrentTick();
    }

    /*PROCESSING*/

    public void setIsCrafting(boolean toState) {
        this.isCrafting = toState;
    }

    public boolean getIsCrafting() {
        return this.isCrafting;
    }

    public void tick(Level level, BlockPos pos, BlockState state) {
        fillUpOnEnergy();

        if (isOutputSlotEmpty() && hasRecipe()) {
            increaseCraftingProgress();
            extractEnergy();
            setIsCrafting(true);

            if (hasProgressFinished()) {
                craftItem();
                resetProgress();
                setIsCrafting(false);
            }
        } else {
            resetProgress();
            setIsCrafting(false);
        }
        level.sendBlockUpdated(worldPosition, state, state, Block.UPDATE_ALL);
    }

    private void extractEnergy() {
        Optional<ExtractionMachineRecipe> recipe = getCurrentRecipe();
        this.ENERGY_STORAGE.extractEnergy(recipe.get().getEnergyRequired(), false);
    }

    private void fillUpOnEnergy() {
        if(hasEnergyItemInSlot(BATTERY_SLOT)) {
            this.ENERGY_STORAGE.receiveEnergy(3200, false);
            PrehistoricCraft.LOGGER.info(String.valueOf(this.ENERGY_STORAGE.getEnergyStored()));
        }
    }

    private boolean hasEnergyItemInSlot(int batterySlot) {
        return !this.itemHandler.getStackInSlot(batterySlot).isEmpty() &&
                this.itemHandler.getStackInSlot(batterySlot).getItem() == ModItems.SULFUR.get();
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private int getProcessingTime() {
        Optional<ExtractionMachineRecipe> recipe = getCurrentRecipe();
        return recipe.get().getProcessingTime();
    }

    private int getCurrentEnergy() {
        return this.ENERGY_STORAGE.getEnergyStored();
    }

    private void setProcessingTime(int time) {
        this.maxProgress = time;
    }

    private void craftItem() {
        Optional<ExtractionMachineRecipe> recipe = getCurrentRecipe();
        Item resultItem = recipe.get().getResultItem(getLevel().registryAccess()).getItem();

        setProcessingTime(recipe.get().getProcessingTime());
        this.itemHandler.extractItem(INPUT_SLOT, 1, false);

        if(this.itemHandler.getStackInSlot(OUTPUT_SLOT_1).getCount() >= this.itemHandler.getStackInSlot(OUTPUT_SLOT_1).getMaxStackSize()) {
            this.itemHandler.setStackInSlot(OUTPUT_SLOT_2, new ItemStack(resultItem, this.itemHandler.getStackInSlot(OUTPUT_SLOT_2).getCount() + 1));
        } else if (this.itemHandler.getStackInSlot(OUTPUT_SLOT_2).getCount() >= this.itemHandler.getStackInSlot(OUTPUT_SLOT_2).getMaxStackSize()){
            this.itemHandler.setStackInSlot(OUTPUT_SLOT_3, new ItemStack(resultItem, this.itemHandler.getStackInSlot(OUTPUT_SLOT_1).getCount() + 1));
        } else {
            this.itemHandler.setStackInSlot(OUTPUT_SLOT_1, new ItemStack(resultItem, this.itemHandler.getStackInSlot(OUTPUT_SLOT_1).getCount() + 1));
        }
    }

    private boolean hasProgressFinished() {
        Optional<ExtractionMachineRecipe> recipe = getCurrentRecipe();
        return this.progress >= recipe.get().getProcessingTime();
    }

    private void increaseCraftingProgress() {
        this.progress++;
    }

    private boolean hasRecipe() {
        Optional<ExtractionMachineRecipe> recipe = getCurrentRecipe();

        if (recipe.isEmpty()) {
            return false;
        }
        ItemStack resultItem = recipe.get().getResultItem(getLevel().registryAccess());

        return canInsertAmount(resultItem.getCount())
                && canInsertItem(resultItem.getItem()) && hasEnoughEnergy();
    }

    private boolean hasEnoughEnergy() {
        Optional<ExtractionMachineRecipe> recipe = getCurrentRecipe();
        return this.ENERGY_STORAGE.getEnergyStored() >= recipe.get().getEnergyRequired() * 1;
    }

    private Optional<ExtractionMachineRecipe> getCurrentRecipe() {
        SimpleContainer inventory = new SimpleContainer(this.itemHandler.getSlots());
        for(int i = 0; i < this.itemHandler.getSlots(); i++) {
            inventory.setItem(i, this.itemHandler.getStackInSlot(i));
        }

        return this.level.getRecipeManager().getRecipeFor(ExtractionMachineRecipe.Type.INSTANCE, inventory, level);
    }

    private boolean canInsertItem(Item item) {

        if (this.itemHandler.getStackInSlot(OUTPUT_SLOT_1).is(item) || this.itemHandler.getStackInSlot(OUTPUT_SLOT_1).isEmpty()) {
            return true;
        }
        if (this.itemHandler.getStackInSlot(OUTPUT_SLOT_2).is(item) || this.itemHandler.getStackInSlot(OUTPUT_SLOT_2).isEmpty()) {
            return true;
        }
        if (this.itemHandler.getStackInSlot(OUTPUT_SLOT_3).is(item) || this.itemHandler.getStackInSlot(OUTPUT_SLOT_3).isEmpty()) {
            return true;
        }

        return false;
    }

    private boolean canInsertAmount(int count) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT_1).getMaxStackSize() > this.itemHandler.getStackInSlot(OUTPUT_SLOT_1).getCount() + count ||
                this.itemHandler.getStackInSlot(OUTPUT_SLOT_2).getMaxStackSize() > this.itemHandler.getStackInSlot(OUTPUT_SLOT_2).getCount() + count ||
                this.itemHandler.getStackInSlot(OUTPUT_SLOT_3).getMaxStackSize() > this.itemHandler.getStackInSlot(OUTPUT_SLOT_3).getCount() + count;
    }

    private boolean isOutputSlotEmpty() {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT_1).isEmpty() ||
                this.itemHandler.getStackInSlot(OUTPUT_SLOT_1).getCount() < this.itemHandler.getStackInSlot(OUTPUT_SLOT_1).getMaxStackSize() ||
                this.itemHandler.getStackInSlot(OUTPUT_SLOT_2).isEmpty() ||
                this.itemHandler.getStackInSlot(OUTPUT_SLOT_2).getCount() < this.itemHandler.getStackInSlot(OUTPUT_SLOT_2).getMaxStackSize() ||
                this.itemHandler.getStackInSlot(OUTPUT_SLOT_3).isEmpty() ||
                this.itemHandler.getStackInSlot(OUTPUT_SLOT_3).getCount() < this.itemHandler.getStackInSlot(OUTPUT_SLOT_3).getMaxStackSize();
    }
}
