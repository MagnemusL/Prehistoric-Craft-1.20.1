package net.smazeee.prehistoriccraft.util;

import net.minecraftforge.energy.IEnergyStorage;

public class AdaptedEnergyHandler implements IEnergyStorage {

    private final IEnergyStorage handler;

    public AdaptedEnergyHandler(IEnergyStorage handler) {
        this.handler = handler;
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        return handler.receiveEnergy(maxReceive, simulate);
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        return handler.extractEnergy(maxExtract, simulate);
    }

    @Override
    public int getEnergyStored() {
        return handler.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored() {
        return handler.getMaxEnergyStored();
    }

    @Override
    public boolean canExtract() {
        return handler.canExtract();
    }

    @Override
    public boolean canReceive() {
        return handler.canReceive();
    }
}
