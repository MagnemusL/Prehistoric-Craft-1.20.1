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

    public static final RegistryObject<CreativeModeTab> PLANTS_TAB = CREATIVE_MODE_TABS.register("plants_tab", () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.BELEMNOPTERIS.get())).title(Component.translatable("creativetab.plants_tab")).displayItems((displayParameters, output) -> {
        output.accept(ModBlocks.BELEMNOPTERIS.get());
        output.accept(ModBlocks.CLATHROPTERIS.get());
        output.accept(ModBlocks.PACHYPTERIS.get());
        output.accept(ModBlocks.NEUROPTERIS.get());
        output.accept(ModBlocks.CONIOPTERIS.get());
        output.accept(ModBlocks.PARACYCAS.get());
        output.accept(ModBlocks.FIELD_HORSETAIL.get());
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

    public static final RegistryObject<CreativeModeTab> FOSSILS_TAB = CREATIVE_MODE_TABS.register("fossils_tab", () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.CAMBRIAN_FOSSILIFEROUS_STONE.get())).title(Component.translatable("creativetab.fossils_tab")).displayItems((displayParameters, output) -> {
        output.accept(ModBlocks.CAMBRIAN_FOSSILIFEROUS_STONE.get());
        output.accept(ModBlocks.PRECAMBRIAN_FOSSILIFEROUS_STONE.get());
        output.accept(ModBlocks.CARBONIFEROUS_FOSSILIFEROUS_STONE.get());
        output.accept(ModBlocks.CRETACEOUS_FOSSILIFEROUS_STONE.get());
        output.accept(ModBlocks.DEVONIAN_FOSSILIFEROUS_STONE.get());
        output.accept(ModBlocks.JURASSIC_FOSSILIFEROUS_STONE.get());
        output.accept(ModBlocks.NEOGENE_FOSSILIFEROUS_STONE.get());
        output.accept(ModBlocks.ORDOVICIAN_FOSSILIFEROUS_STONE.get());
        output.accept(ModBlocks.PALEOGENE_FOSSILIFEROUS_STONE.get());
        output.accept(ModBlocks.PERMIAN_FOSSILIFEROUS_STONE.get());
        output.accept(ModBlocks.SILURIAN_FOSSILIFEROUS_STONE.get());
        output.accept(ModBlocks.TRIASSIC_FOSSILIFEROUS_STONE.get());
        output.accept(ModItems.CAMBRIAN_FOSSIL.get());
        output.accept(ModItems.PRECAMBRIAN_FOSSIL.get());
        output.accept(ModItems.CARBONIFEROUS_FOSSIL.get());
        output.accept(ModItems.CRETACEOUS_FOSSIL.get());
        output.accept(ModItems.DEVONIAN_FOSSIL.get());
        output.accept(ModItems.JURASSIC_FOSSIL.get());
        output.accept(ModItems.NEOGENE_FOSSIL.get());
        output.accept(ModItems.ORDOVICIAN_FOSSIL.get());
        output.accept(ModItems.PALEOGENE_FOSSIL.get());
        output.accept(ModItems.PERMIAN_FOSSIL.get());
        output.accept(ModItems.SILURIAN_FOSSIL.get());
        output.accept(ModItems.TRIASSIC_FOSSIL.get());
        output.accept(ModItems.EXTRACTION_MACHINE_BLOCK_ITEM.get());
        output.accept(ModItems.SULFUR.get());
        output.accept(ModItems.SULFURIC_ACID_BUCKET.get());
        output.accept(ModBlocks.SULFUR_ORE.get());
    }).build());

    public static final RegistryObject<CreativeModeTab> DECORATION_TAB = CREATIVE_MODE_TABS.register("decoration_tab", () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.LEAF_LITTER.get())).title(Component.translatable("creativetab.decorations_tab")).displayItems((displayParameters, output) -> {
        output.accept(ModBlocks.FOREST_BEDDING.get());
        output.accept(ModBlocks.LEAF_LITTER.get());
        output.accept(ModBlocks.LOAMY_DIRT.get());
        output.accept(ModBlocks.LOAMY_FOREST_BEDDING.get());
        output.accept(ModBlocks.LOAMY_ORGANIC_FOREST_BEDDING.get());
        output.accept(ModBlocks.LUSH_WET_DIRT.get());
        output.accept(ModBlocks.LUSH_WET_LOAMY_DIRT.get());
        output.accept(ModBlocks.LUSH_WET_MUDDY_DIRT.get());
        output.accept(ModBlocks.LUSH_WET_ORANGE_SANDY_DIRT.get());
        output.accept(ModBlocks.LUSH_WET_SANDY_DIRT.get());
        output.accept(ModBlocks.MUDDY_DIRT.get());
        output.accept(ModBlocks.MUDDY_FOREST_BEDDING.get());
        output.accept(ModBlocks.MUDDY_ORGANIC_FOREST_BEDDING.get());
        output.accept(ModBlocks.ORANGE_SANDY_DIRT.get());
        output.accept(ModBlocks.ORGANIC_DIRT.get());
        output.accept(ModBlocks.ORGANIC_LOAMY_DIRT.get());
        output.accept(ModBlocks.ORGANIC_MUDDY_DIRT.get());
        output.accept(ModBlocks.ORGANIC_ORANGE_SANDY_DIRT.get());
        output.accept(ModBlocks.ORGANIC_SANDY_DIRT.get());
        output.accept(ModBlocks.SANDY_DIRT.get());
    }).build());

    public static final RegistryObject<CreativeModeTab> ENTITY_TAB = CREATIVE_MODE_TABS.register("entity_tab", () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.LEAF_LITTER.get())).title(Component.translatable("creativetab.entity_tab")).displayItems((displayParameters, output) -> {
        output.accept(ModItems.DAYONGASPIS_SPAWN_EGG.get());
        output.accept(ModItems.EGGY.get());
        output.accept(ModItems.CRACKED_EGGY.get());
    }).build());

    public static void register (IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
