package net.smazeee.prehistoriccraft.util;

import net.minecraftforge.energy.EnergyStorage;

public abstract class ModEnergyStorage extends EnergyStorage {
    public ModEnergyStorage(int capacity, int maxTransfer) {
        super(capacity, maxTransfer);
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        int extractedEnergy = super.extractEnergy(maxExtract, simulate);
        if(extractedEnergy != 0) {
            onEnergyChanged();
        }

        return extractedEnergy;
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        int receivedEnergy = super.receiveEnergy(maxReceive, simulate);
        if(receivedEnergy != 0) {
            onEnergyChanged();
        }

        return receivedEnergy;
    }

    public int setEnergy(int amount) {
        this.energy = amount;
        return energy;
    }

    public abstract void onEnergyChanged();
}
