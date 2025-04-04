package net.seentro.prehistoriccraft;

import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.seentro.prehistoriccraft.block.ModBlocks;
import net.seentro.prehistoriccraft.block.entity.ModBlockEntities;
import net.seentro.prehistoriccraft.block.entity.client.ExtractionMachineRenderer;
import net.seentro.prehistoriccraft.entities.ModEntityTypes;
import net.seentro.prehistoriccraft.entities.land.styracosaurus.Styracosaurus;
import net.seentro.prehistoriccraft.entities.land.styracosaurus.StyracosaurusRenderer;
import net.seentro.prehistoriccraft.entities.water.dayongaspis.DayongaspisEntity;
import net.seentro.prehistoriccraft.entities.water.dayongaspis.DayongaspisRenderer;
import net.seentro.prehistoriccraft.screen.ExtractionMachineScreen;
import net.seentro.prehistoriccraft.tabs.ModCreativeModeTabs;
import net.seentro.prehistoriccraft.item.ModItems;
import net.seentro.prehistoriccraft.recipe.ModRecipes;
import net.seentro.prehistoriccraft.screen.AcidShowerScreen;
import net.seentro.prehistoriccraft.screen.ModMenuTypes;
import net.seentro.prehistoriccraft.util.TerritoryUtil;
import org.slf4j.Logger;
import software.bernie.geckolib.GeckoLib;

@Mod(PrehistoricCraft.MODID)
public class PrehistoricCraft {
    public static final String MODID = "prehistoriccraft";
    public static final Logger LOGGER = LogUtils.getLogger();

    public PrehistoricCraft() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModEntityTypes.register(modEventBus);

        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);

        ModBlockEntities.register(modEventBus);
        ModCreativeModeTabs.register(modEventBus);

        ModMenuTypes.register(modEventBus);
        ModRecipes.register(modEventBus);

        GeckoLib.initialize();
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::entityAttributeEvent);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(ModEntityTypes.DAYONGASPIS.get(), DayongaspisEntity.setAttributes());
        event.put(ModEntityTypes.STYRACOSAURUS.get(), Styracosaurus.setAttributes());
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        TerritoryUtil.get(event.getServer().overworld());
        PrehistoricCraft.LOGGER.info("Instantiated Territories");
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            MenuScreens.register(ModMenuTypes.ACID_SHOWER_MENU.get(), AcidShowerScreen::new);
            MenuScreens.register(ModMenuTypes.EXTRACTION_MACHINE_MENU.get(), ExtractionMachineScreen::new);

            BlockEntityRenderers.register(ModBlockEntities.EXTRACTION_MACHINE_BE.get(), ExtractionMachineRenderer::new);

            EntityRenderers.register(ModEntityTypes.DAYONGASPIS.get(), DayongaspisRenderer::new);
            EntityRenderers.register(ModEntityTypes.STYRACOSAURUS.get(), StyracosaurusRenderer::new);
        }
    }

    @Mod.EventBusSubscriber(modid = PrehistoricCraft.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class ModEventsForge {
    }

    @Mod.EventBusSubscriber(modid = PrehistoricCraft.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEvents {
    }
}