package net.smazeee.prehistoriccraft.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.registries.RegistryObject;
import net.smazeee.prehistoriccraft.block.ModBlocks;
import net.smazeee.prehistoriccraft.item.ModItems;
import net.smazeee.prehistoriccraft.recipe.AcidShowerRecipe;
import net.smazeee.prehistoriccraft.screen.AcidShowerMenu;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class AcidShowerBlockEntity extends BlockEntity implements MenuProvider {
    private static final List<Item> FOSSILS = List.of(ModItems.CAMBRIAN_FOSSIL.get(), ModItems.PRECAMBRIAN_FOSSIL.get(), ModItems.CARBONIFEROUS_FOSSIL.get(), ModItems.CRETACEOUS_FOSSIL.get(), ModItems.DEVONIAN_FOSSIL.get(), ModItems.JURASSIC_FOSSIL.get(), ModItems.ORDOVICIAN_FOSSIL.get(), ModItems.PERMIAN_FOSSIL.get(), ModItems.SILURIAN_FOSSIL.get(), ModItems.TRIASSIC_FOSSIL.get()); //, ModBlocks.CAMBRIAN_FOSSILIFEROUS_STONE.get(), ModBlocks.PRECAMBRIAN_FOSSILIFEROUS_STONE.get(), ModBlocks.CARBONIFEROUS_FOSSILIFEROUS_STONE.get(), ModBlocks.CRETACEOUS_FOSSILIFEROUS_STONE.get(), ModBlocks.DEVONIAN_FOSSILIFEROUS_STONE.get(), ModBlocks.JURASSIC_FOSSILIFEROUS_STONE.get(), ModBlocks.ORDOVICIAN_FOSSILIFEROUS_STONE.get(), ModBlocks.PERMIAN_FOSSILIFEROUS_STONE.get(), ModBlocks.SILURIAN_FOSSILIFEROUS_STONE.get(), ModBlocks.TRIASSIC_FOSSILIFEROUS_STONE.get());

    private final ItemStackHandler itemHandler = new ItemStackHandler(5) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return switch (slot) {
                case 0 -> stack.getItem() == ModItems.AMBER.get();
                case 1 -> true;
                case 2, 3, 4 -> false;
                default -> super.isItemValid(slot, stack);
            };
        }
    };

    private static final int ACID_SLOT = 0;
    private static final int FOSSIL_SLOT = 1;
    private static final int OUTPUT_SLOT_1 = 2;
    private static final int OUTPUT_SLOT_2 = 3;
    private static final int OUTPUT_SLOT_3 = 4;

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 20;

    public AcidShowerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ACID_SHOWER_BE.get(), pos, state);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> AcidShowerBlockEntity.this.progress;
                    case 1 -> AcidShowerBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> AcidShowerBlockEntity.this.progress = value;
                    case 1 -> AcidShowerBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("blockentity.acid_shower");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
        return new AcidShowerMenu(containerId, inventory, this, this.data);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        tag.put("inventory", itemHandler.serializeNBT());
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        itemHandler.deserializeNBT(tag.getCompound("inventory"));
    }

    /* CRAFTING */

    public void tick(Level level, BlockPos pos, BlockState state) {
        if (isOutputSlotEmpty() && hasRecipe()) {
            increaseCraftingProgress();
            setChanged(level, pos, state);

            if (hasProgressFinished()) {
                craftItem();
                resetProgress();
            }
        } else {
            resetProgress();
        }
    }


    private void resetProgress() {
        this.progress = 0;
    }

    private void craftItem() {
        Optional<AcidShowerRecipe> recipe = getCurrentRecipe();
        Item resultItem = recipe.get().getResultItem(getLevel().registryAccess()).getItem();


        this.itemHandler.extractItem(FOSSIL_SLOT, 1, false);

        if(this.itemHandler.getStackInSlot(OUTPUT_SLOT_1).getCount() > this.itemHandler.getStackInSlot(OUTPUT_SLOT_1).getMaxStackSize()) {
            this.itemHandler.setStackInSlot(OUTPUT_SLOT_2, new ItemStack(resultItem, this.itemHandler.getStackInSlot(OUTPUT_SLOT_2).getCount() + 1));
        } else if (this.itemHandler.getStackInSlot(OUTPUT_SLOT_2).getCount() > this.itemHandler.getStackInSlot(OUTPUT_SLOT_2).getMaxStackSize()){
            this.itemHandler.setStackInSlot(OUTPUT_SLOT_3, new ItemStack(resultItem, this.itemHandler.getStackInSlot(OUTPUT_SLOT_1).getCount() + 1));
        } else {
            this.itemHandler.setStackInSlot(OUTPUT_SLOT_1, new ItemStack(resultItem, this.itemHandler.getStackInSlot(OUTPUT_SLOT_1).getCount() + 1));
        }
    }

    private boolean hasProgressFinished() {
        return this.progress >= this.maxProgress;
    }

    private void increaseCraftingProgress() {
        this.progress++;
    }

    private boolean hasRecipe() {
        Optional<AcidShowerRecipe> recipe = getCurrentRecipe();

        if (recipe.isEmpty()) {
            return false;
        }
        ItemStack resultItem = recipe.get().getResultItem(getLevel().registryAccess());

        return canInsertAmount(resultItem.getCount())
                && canInsertItem(resultItem.getItem());
    }

    private Optional<AcidShowerRecipe> getCurrentRecipe() {
        SimpleContainer inventory = new SimpleContainer(this.itemHandler.getSlots());
        for(int i = 0; i < this.itemHandler.getSlots(); i++) {
            inventory.setItem(i, this.itemHandler.getStackInSlot(i));
        }

        return this.level.getRecipeManager().getRecipeFor(AcidShowerRecipe.Type.INSTANCE, inventory, level);
    }

    private boolean canInsertItem(Item item) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT_1).is(item) && this.itemHandler.getStackInSlot(OUTPUT_SLOT_2).is(item) && this.itemHandler.getStackInSlot(OUTPUT_SLOT_3).is(item) || this.itemHandler.getStackInSlot(OUTPUT_SLOT_1).isEmpty() && this.itemHandler.getStackInSlot(OUTPUT_SLOT_2).isEmpty() && this.itemHandler.getStackInSlot(OUTPUT_SLOT_3).isEmpty();
    }

    private boolean canInsertAmount(int count) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT_1).getMaxStackSize() > this.itemHandler.getStackInSlot(OUTPUT_SLOT_1).getCount() + count;
    }

    private boolean isOutputSlotEmpty() {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT_3).isEmpty() ||
                this.itemHandler.getStackInSlot(OUTPUT_SLOT_3).getCount() < this.itemHandler.getStackInSlot(OUTPUT_SLOT_3).getMaxStackSize();
    }
}