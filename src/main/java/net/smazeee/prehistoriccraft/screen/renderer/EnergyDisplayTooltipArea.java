package net.smazeee.prehistoriccraft.screen.renderer;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraftforge.energy.IEnergyStorage;

import java.util.List;

/*
 *  BluSunrize
 *  Copyright (c) 2021
 *
 *  This code is licensed under "Blu's License of Common Sense"
 *  https://github.com/BluSunrize/ImmersiveEngineering/blob/1.19.2/LICENSE
 *
 *  Slightly Modified Version by: Kaupenjoe
 */
public class EnergyDisplayTooltipArea {
    private final IEnergyStorage energy;

    public EnergyDisplayTooltipArea(IEnergyStorage energy)  {
        this.energy = energy;
    }

    public List<Component> getTooltips() {
        return List.of(Component.literal(energy.canReceive()+" / "+energy.getMaxEnergyStored()+" FE"));
    }
}
