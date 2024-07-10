package net.smazeee.prehistoriccraft;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.smazeee.prehistoriccraft.entities.ModEntityTypes;
import net.smazeee.prehistoriccraft.entities.water.dayongaspis.Dayongaspis;

@Mod.EventBusSubscriber(modid = PrehistoricCraft.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents {
    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.DAYONGASPIS.get(), Dayongaspis.setAttributes());
    }
}
