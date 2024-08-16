package net.smazeee.prehistoriccraft;

import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.DimensionDataStorage;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.smazeee.prehistoriccraft.block.ModBlocks;
import net.smazeee.prehistoriccraft.block.entity.ModBlockEntities;
import net.smazeee.prehistoriccraft.block.entity.client.ExtractionMachineRenderer;
import net.smazeee.prehistoriccraft.entities.ModEntityTypes;
import net.smazeee.prehistoriccraft.entities.water.dayongaspis.Dayongaspis;
import net.smazeee.prehistoriccraft.entities.water.dayongaspis.DayongaspisRenderer;
import net.smazeee.prehistoriccraft.screen.ExtractionMachineScreen;
import net.smazeee.prehistoriccraft.tabs.ModCreativeModeTabs;
import net.smazeee.prehistoriccraft.item.ModItems;
import net.smazeee.prehistoriccraft.recipe.ModRecipes;
import net.smazeee.prehistoriccraft.screen.AcidShowerScreen;
import net.smazeee.prehistoriccraft.screen.ModMenuTypes;
import net.smazeee.prehistoriccraft.util.TerritoryUtil;
import org.slf4j.Logger;
import software.bernie.geckolib.GeckoLib;

import java.util.ArrayList;
import java.util.List;

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
        event.put(ModEntityTypes.DAYONGASPIS.get(), Dayongaspis.setAttributes());
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        TerritoryUtil.get(event.getServer().overworld());
        PrehistoricCraft.LOGGER.info("Instantiated Territory Util");
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            MenuScreens.register(ModMenuTypes.ACID_SHOWER_MENU.get(), AcidShowerScreen::new);
            MenuScreens.register(ModMenuTypes.EXTRACTION_MACHINE_MENU.get(), ExtractionMachineScreen::new);

            BlockEntityRenderers.register(ModBlockEntities.EXTRACTION_MACHINE_BE.get(), ExtractionMachineRenderer::new);

            EntityRenderers.register(ModEntityTypes.DAYONGASPIS.get(), DayongaspisRenderer::new);
        }
    }

    @Mod.EventBusSubscriber(modid = PrehistoricCraft.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class ModEventsForge {
    }

    @Mod.EventBusSubscriber(modid = PrehistoricCraft.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEvents {
    }
}