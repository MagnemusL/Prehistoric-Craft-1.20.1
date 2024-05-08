package net.smazeee.prehistoriccraft.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.smazeee.prehistoriccraft.PrehistoricCraft;
import net.smazeee.prehistoriccraft.block.ModBlocks;
import net.smazeee.prehistoriccraft.block.bakedmodel.CableModelLoader;
import net.smazeee.prehistoriccraft.block.custom.cables.FacadeBlockColor;

@Mod.EventBusSubscriber(modid = PrehistoricCraft.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {
    @SubscribeEvent
    public static void modelInit(ModelEvent.RegisterGeometryLoaders event) {
        CableModelLoader.register(event);
    }

    @SubscribeEvent
    public static void registerBlockColor(RegisterColorHandlersEvent.Block event) {
        event.register(new FacadeBlockColor(), ModBlocks.FACADE.get());
    }
}
