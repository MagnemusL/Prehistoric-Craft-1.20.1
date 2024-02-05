package net.smazeee.prehistoriccraft.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.smazeee.prehistoriccraft.PrehistoricCraft;

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

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
