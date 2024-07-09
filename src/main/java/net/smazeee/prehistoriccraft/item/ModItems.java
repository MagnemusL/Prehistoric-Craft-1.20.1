package net.smazeee.prehistoriccraft.item;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.smazeee.prehistoriccraft.PrehistoricCraft;
import net.smazeee.prehistoriccraft.block.ModBlocks;
import net.smazeee.prehistoriccraft.entities.ModEntities;
import net.smazeee.prehistoriccraft.item.custom.ExtractionMachineBlockItem;
import net.smazeee.prehistoriccraft.item.custom.FluidContainer;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, PrehistoricCraft.MODID);

    public static final RegistryObject<Item> CAMBRIAN_FOSSIL = ITEMS.register("cambrian_fossil", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PRECAMBRIAN_FOSSIL = ITEMS.register("precambrian_fossil", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CARBONIFEROUS_FOSSIL = ITEMS.register("carboniferous_fossil", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CRETACEOUS_FOSSIL = ITEMS.register("cretaceous_fossil", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DEVONIAN_FOSSIL = ITEMS.register("devonian_fossil", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> JURASSIC_FOSSIL = ITEMS.register("jurassic_fossil", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> NEOGENE_FOSSIL = ITEMS.register("neogene_fossil", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ORDOVICIAN_FOSSIL = ITEMS.register("ordovician_fossil", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PALEOGENE_FOSSIL = ITEMS.register("paleogene_fossil", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PERMIAN_FOSSIL = ITEMS.register("permian_fossil", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SILURIAN_FOSSIL = ITEMS.register("silurian_fossil", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> TRIASSIC_FOSSIL = ITEMS.register("triassic_fossil", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> AMBER = ITEMS.register("amber", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SULFUR = ITEMS.register("sulfur", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SULFURIC_ACID_BUCKET = ITEMS.register("sulfuric_acid_bucket", () -> new FluidContainer(new Item.Properties()));
    public static final RegistryObject<Item> EGGY = ITEMS.register("eggy", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CRACKED_EGGY = ITEMS.register("cracked_eggy", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> EMPTY_FLUID_CONTAINER = ITEMS.register("empty_fluid_container", () -> new FluidContainer(new Item.Properties()));

    public static final RegistryObject<Item> EXTRACTION_MACHINE_BLOCK_ITEM = ITEMS.register("extraction_machine", () -> new ExtractionMachineBlockItem(ModBlocks.EXTRACTION_MACHINE.get(), new Item.Properties()));

    //SPAWN EGGS
    public static final RegistryObject<Item> DAYONGASPIS_SPAWN_EGG = ITEMS.register("dayongaspis_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.DAYONGASPIS, 0x997102, 0x80692b, new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
