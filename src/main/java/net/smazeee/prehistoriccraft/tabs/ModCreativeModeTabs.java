package net.smazeee.prehistoriccraft.tabs;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.smazeee.prehistoriccraft.PrehistoricCraft;
import net.smazeee.prehistoriccraft.block.ModBlocks;
import net.smazeee.prehistoriccraft.item.ModItems;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PrehistoricCraft.MODID);

    public static final RegistryObject<CreativeModeTab> PLANTS_TAB = CREATIVE_MODE_TABS.register("plants_tab", () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.BELEMNOPTERIS.get())).title(Component.translatable("creativetab.plants_tab")).withSearchBar().displayItems((displayParameters, output) -> {
        output.accept(ModBlocks.BELEMNOPTERIS.get());
        output.accept(ModBlocks.CLATHROPTERIS.get());
        output.accept(ModBlocks.PACHYPTERIS.get());
        output.accept(ModBlocks.NEUROPTERIS.get());
        output.accept(ModBlocks.AQUA_STONY_CORAL.get());
        output.accept(ModBlocks.BLUE_STONY_CORAL.get());
        output.accept(ModBlocks.GREEN_STONY_CORAL.get());
        output.accept(ModBlocks.PINK_STONY_CORAL.get());
        output.accept(ModBlocks.BLUE_SOFT_FAN_CORAL.get());
        output.accept(ModBlocks.BLUE_STAGHORN_CORAL.get());
        output.accept(ModBlocks.RED_STAGHORN_CORAL.get());
        output.accept(ModBlocks.CARNATION_TREE_CORAL.get());
        output.accept(ModBlocks.ORANGE_BAMBOO_CORAL.get());
        output.accept(ModBlocks.RED_BAMBOO_CORAL.get());
    }).build());

    public static void register (IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
