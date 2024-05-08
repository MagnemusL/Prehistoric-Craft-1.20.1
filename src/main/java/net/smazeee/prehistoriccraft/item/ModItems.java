package net.smazeee.prehistoriccraft.item;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.smazeee.prehistoriccraft.PrehistoricCraft;
import net.smazeee.prehistoriccraft.block.ModBlocks;
import net.smazeee.prehistoriccraft.item.custom.FacadeBlockItem;
import net.smazeee.prehistoriccraft.item.custom.FluidContainer;
import net.smazeee.prehistoriccraft.plants.item.BelemnopterisBlockItem;
import net.smazeee.prehistoriccraft.plants.item.ClathropterisBlockItem;
import net.smazeee.prehistoriccraft.plants.item.NeuropterisBlockItem;
import net.smazeee.prehistoriccraft.plants.item.PachypterisBlockItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, PrehistoricCraft.MODID);

    public static final RegistryObject<Item> CAMBRIAN_FOSSIL = ITEMS.register("cambrian_fossil", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PRECAMBRIAN_FOSSIL = ITEMS.register("precambrian_fossil", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CARBONIFEROUS_FOSSIL = ITEMS.register("carboniferous_fossil", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CRETACEOUS_FOSSIL = ITEMS.register("cretaceous_fossil", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DEVONIAN_FOSSIL = ITEMS.register("devonian_fossil", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> JURASSIC_FOSSIL = ITEMS.register("jurassic_fossil", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ORDOVICIAN_FOSSIL = ITEMS.register("ordovician_fossil", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PERMIAN_FOSSIL = ITEMS.register("permian_fossil", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SILURIAN_FOSSIL = ITEMS.register("silurian_fossil", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TRIASSIC_FOSSIL = ITEMS.register("triassic_fossil", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> AMBER = ITEMS.register("amber", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SULFUR = ITEMS.register("sulfur", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SULFURIC_ACID_BUCKET = ITEMS.register("sulfuric_acid_bucket", () -> new FluidContainer(new Item.Properties()));
    public static final RegistryObject<Item> EGGY = ITEMS.register("eggy", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CRACKED_EGGY = ITEMS.register("cracked_eggy", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BELEMNOPTERIS_BLOCK_ITEM = ITEMS.register("belemnopteris", () -> new BelemnopterisBlockItem(ModBlocks.BELEMNOPTERIS.get(), new Item.Properties()));
    public static final RegistryObject<Item> CLATHROPTERIS_BLOCK_ITEM = ITEMS.register("clathropteris", () -> new ClathropterisBlockItem(ModBlocks.CLATHROPTERIS.get(), new Item.Properties()));
    public static final RegistryObject<Item> PACHYPTERIS_BLOCK_ITEM = ITEMS.register("pachypteris", () -> new PachypterisBlockItem(ModBlocks.PACHYPTERIS.get(), new Item.Properties()));
    public static final RegistryObject<Item> NEUROPTERIS_BLOCK_ITEM = ITEMS.register("neuropteris", () -> new NeuropterisBlockItem(ModBlocks.NEUROPTERIS.get(), new Item.Properties()));
    public static final RegistryObject<Item> FIELD_HORSETAIL_BLOCK_ITEM = ITEMS.register("field_horsetail", () -> new NeuropterisBlockItem(ModBlocks.FIELD_HORSETAIL.get(), new Item.Properties()));
    public static final RegistryObject<Item> FACADE_BLOCK_ITEM = ITEMS.register("facade", () -> new FacadeBlockItem(ModBlocks.FACADE.get(), new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
