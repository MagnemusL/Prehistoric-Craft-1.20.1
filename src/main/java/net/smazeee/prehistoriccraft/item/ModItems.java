package net.smazeee.prehistoriccraft.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.smazeee.prehistoriccraft.PrehistoricCraft;
import net.smazeee.prehistoriccraft.block.ModBlocks;
import net.smazeee.prehistoriccraft.entities.ModEntityTypes;
import net.smazeee.prehistoriccraft.item.custom.*;
import net.smazeee.prehistoriccraft.item.custom.satchets.*;
import net.smazeee.prehistoriccraft.plants.NeuropterisBlockItem;
import net.smazeee.prehistoriccraft.plants.PachypterisBlockItem;
import net.smazeee.prehistoriccraft.plants.ParacycasBlockItem;

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
    //public static final RegistryObject<Item> CLEANED_FOSSIL_STONE = ITEMS.register("cleaned_fossil_stone", () -> new Item(new Item.Properties()));
    //public static final RegistryObject<Item> CLEANED_FOSSIL_DEEPSLATE = ITEMS.register("cleaned_fossil_deepslate", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> AMBER = ITEMS.register("amber", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SULFUR = ITEMS.register("sulfur", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SULFURIC_ACID_BUCKET = ITEMS.register("sulfuric_acid_bucket", () -> new FluidContainer(new Item.Properties()));
    public static final RegistryObject<Item> EGGY = ITEMS.register("eggy", () -> new BatteryItem(new Item.Properties()));
    public static final RegistryObject<Item> CRACKED_EGGY = ITEMS.register("cracked_eggy", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> EMPTY_FLUID_CONTAINER = ITEMS.register("empty_fluid_container", () -> new FluidContainer(new Item.Properties()));
    public static final RegistryObject<Item> EXTRACTION_MACHINE_BLOCK_ITEM = ITEMS.register("extraction_machine", () -> new ExtractionMachineBlockItem(ModBlocks.EXTRACTION_MACHINE.get(), new Item.Properties()));
    public static final RegistryObject<Item> STYRACOSAURUS_SPAWN_EGG = ITEMS.register("styracosaurus_spawn_egg", () -> new ForgeSpawnEggItem(ModEntityTypes.STYRACOSAURUS, 0x997102, 0x80692b, new Item.Properties()));
    public static final RegistryObject<Item> DAYONGASPIS_SPAWN_EGG = ITEMS.register("dayongaspis_spawn_egg", () -> new ForgeSpawnEggItem(ModEntityTypes.DAYONGASPIS, 0x997102, 0x80692b, new Item.Properties()));
    public static final RegistryObject<Item> DAYONGASPIS_BUCKET = ITEMS.register("dayongaspis_bucket", () -> new MobBucketItem(() -> ModEntityTypes.DAYONGASPIS.get(), () -> Fluids.WATER, () -> SoundEvents.BUCKET_EMPTY_FISH, (new Item.Properties()).stacksTo(1)));
    public static final RegistryObject<Item> EMPTY_SATCHET = ITEMS.register("empty_satchet", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BELEMNOPTERIS_SEEDS_SATCHET = ITEMS.register("belemnopteris_seeds_satchet", () -> new BelemnopterisSatchet( new Item.Properties()));
    public static final RegistryObject<Item> CLATHROPTERIS_SPORES_SATCHET = ITEMS.register("clathropteris_spores_satchet", () -> new ClathropterisSatchet(new Item.Properties()));
    public static final RegistryObject<Item> FIELD_HORSETAIL_SPORES_SATCHET = ITEMS.register("field_horsetail_spores_satchet", () -> new FieldHorsetailSatchet(new Item.Properties()));
    public static final RegistryObject<Item> NEUROPTERIS_SEEDS_SATCHET = ITEMS.register("neuropteris_seeds_satchet", () -> new NeuropterisSatchet(new Item.Properties()));
    public static final RegistryObject<Item> PACHYPTERIS_SEEDS_SATCHET = ITEMS.register("pachypteris_seeds_satchet", () -> new PachypterisSatchet(new Item.Properties()));
    //public static final RegistryObject<Item> ANCIENT_PLANT_TISSUE = ITEMS.register("ancient_plant_tissue", () -> new Item(new Item.Properties()));
    //public static final RegistryObject<Item> ANCIENT_ANIMAL_TISSUE = ITEMS.register("ancient_animal_tissue", () -> new Item(new Item.Properties()));
    //public static final RegistryObject<Item> ANCIENT_BLOOD = ITEMS.register("ancient_blood", () -> new Item(new Item.Properties()));
    //public static final RegistryObject<Item> BLOOD_PHIAL = ITEMS.register("blood_phial", () -> new Item(new Item.Properties()));
    //public static final RegistryObject<Item> BATTERY = ITEMS.register("battery", () -> new Item(new Item.Properties()));
    //public static final RegistryObject<Item> BLICE = ITEMS.register("blice", () -> new Item(new Item.Properties()));
    //public static final RegistryObject<Item> BLICE_BOTTLE = ITEMS.register("blice_bottle", () -> new Item(new Item.Properties()));
    //public static final RegistryObject<Item> PALEOENCYCLOPEDIA = ITEMS.register("paleoencyclopedia", () -> new Item(new Item.Properties()));
    //public static final RegistryObject<Item> PALEOKEEPERS_NOTEBOOK = ITEMS.register("paleokeepers_notebook", () -> new Item(new Item.Properties()));

    /* BLOCK ITEMS */
    public static final RegistryObject<Item> PACHYPTERIS_TRUNK = ITEMS.register("pachypteris_trunk", () -> new PachypterisBlockItem(new Item.Properties()));
    public static final RegistryObject<Item> PACHYPTERIS_SHOOT = ITEMS.register("pachypteris_shoot", () -> new BlockItem(ModBlocks.PACHYPTERIS_SHOOT.get(), new Item.Properties()));
    public static final RegistryObject<Item> NEUROPTERIS_TRUNK = ITEMS.register("neuropteris_trunk", () -> new NeuropterisBlockItem(new Item.Properties()));
    public static final RegistryObject<Item> NEUROPTERIS_SHOOT = ITEMS.register("neuropteris_shoot", () -> new BlockItem(ModBlocks.NEUROPTERIS_SHOOT.get(), new Item.Properties()));
    public static final RegistryObject<Item> PARACYCAS_TRUNK = ITEMS.register("paracycas_trunk", () -> new ParacycasBlockItem(new Item.Properties()));
    public static final RegistryObject<Item> PARACYCAS_SHOOT = ITEMS.register("paracycas_shoot", () -> new BlockItem(ModBlocks.PARACYCAS_SHOOT.get(), new Item.Properties()));
    public static final RegistryObject<Item> FIELD_HORSETAIL = ITEMS.register("field_horsetail", () -> new FieldHorsetailBlockItem(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
