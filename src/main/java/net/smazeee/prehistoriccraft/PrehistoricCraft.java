package net.smazeee.prehistoriccraft;

import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
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
import net.smazeee.prehistoriccraft.entities.ModEntities;
import net.smazeee.prehistoriccraft.entities.water.dayongaspis.DayongaspisRenderer;
import net.smazeee.prehistoriccraft.screen.ExtractionMachineScreen;
import net.smazeee.prehistoriccraft.tabs.ModCreativeModeTabs;
import net.smazeee.prehistoriccraft.item.ModItems;
import net.smazeee.prehistoriccraft.recipe.ModRecipes;
import net.smazeee.prehistoriccraft.screen.AcidShowerScreen;
import net.smazeee.prehistoriccraft.screen.ModMenuTypes;
import org.slf4j.Logger;
import software.bernie.geckolib.GeckoLib;

@Mod(PrehistoricCraft.MODID)
public class PrehistoricCraft {
    public static final String MODID = "prehistoriccraft";
    public static final Logger LOGGER = LogUtils.getLogger();

    public PrehistoricCraft() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);

        ModBlockEntities.register(modEventBus);
        ModCreativeModeTabs.register(modEventBus);

        ModMenuTypes.register(modEventBus);
        ModRecipes.register(modEventBus);

        ModEntities.register(modEventBus);

        GeckoLib.initialize();
        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.AQUA_STONY_CORAL.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.BLUE_STONY_CORAL.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.GREEN_STONY_CORAL.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.PINK_STONY_CORAL.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.BLUE_SOFT_FAN_CORAL.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.BLUE_STAGHORN_CORAL.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.RED_STAGHORN_CORAL.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.CARNATION_TREE_CORAL.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.ORANGE_BAMBOO_CORAL.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.RED_BAMBOO_CORAL.get(), RenderType.cutout());
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            MenuScreens.register(ModMenuTypes.ACID_SHOWER_MENU.get(), AcidShowerScreen::new);
            MenuScreens.register(ModMenuTypes.EXTRACTION_MACHINE_MENU.get(), ExtractionMachineScreen::new);

            BlockEntityRenderers.register(ModBlockEntities.EXTRACTION_MACHINE_BE.get(), ExtractionMachineRenderer::new);

            EntityRenderers.register(ModEntities.DAYONGASPIS.get(), DayongaspisRenderer::new);
        }
    }
}
