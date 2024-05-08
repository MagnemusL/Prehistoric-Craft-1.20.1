package net.smazeee.prehistoriccraft.experiment;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;
import net.smazeee.prehistoriccraft.block.entity.ModBlockEntities;
import net.smazeee.prehistoriccraft.util.AdaptedEnergyHandler;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class ExperimentBlockEntity extends BlockEntity {
    private Set<BlockPos> outputs = null;

    protected ExperimentBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public ExperimentBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.EXPERIMENT_BE.get(), pos, state);
    }
}