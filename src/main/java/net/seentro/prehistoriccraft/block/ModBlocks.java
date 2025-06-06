package net.seentro.prehistoriccraft.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.seentro.prehistoriccraft.PrehistoricCraft;
import net.seentro.prehistoriccraft.block.custom.*;
import net.seentro.prehistoriccraft.plants.*;
import net.seentro.prehistoriccraft.item.ModItems;
import net.seentro.prehistoriccraft.plants.neuropteris.NeuropterisShoot;
import net.seentro.prehistoriccraft.plants.neuropteris.NeuropterisTrunk;
import net.seentro.prehistoriccraft.plants.pachypteris.PachypterisShoot;
import net.seentro.prehistoriccraft.plants.pachypteris.PachypterisTrunk;
import net.seentro.prehistoriccraft.plants.paracycas.ParacycasShoot;
import net.seentro.prehistoriccraft.plants.paracycas.ParacycasTrunk;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, PrehistoricCraft.MODID);

    //FOSSILS
    public static final RegistryObject<Block> CAMBRIAN_FOSSILIFEROUS_STONE = registerBlock("cambrian_fossiliferous_stone", () -> new DropExperienceBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
    public static final RegistryObject<Block> PRECAMBRIAN_FOSSILIFEROUS_STONE = registerBlock("precambrian_fossiliferous_stone", () -> new DropExperienceBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
    public static final RegistryObject<Block> CARBONIFEROUS_FOSSILIFEROUS_STONE = registerBlock("carboniferous_fossiliferous_stone", () -> new DropExperienceBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
    public static final RegistryObject<Block> CRETACEOUS_FOSSILIFEROUS_STONE = registerBlock("cretaceous_fossiliferous_stone", () -> new DropExperienceBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
    public static final RegistryObject<Block> DEVONIAN_FOSSILIFEROUS_STONE = registerBlock("devonian_fossiliferous_stone", () -> new DropExperienceBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
    public static final RegistryObject<Block> JURASSIC_FOSSILIFEROUS_STONE = registerBlock("jurassic_fossiliferous_stone", () -> new DropExperienceBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
    public static final RegistryObject<Block> NEOGENE_FOSSILIFEROUS_STONE = registerBlock("neogene_fossiliferous_stone", () -> new DropExperienceBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
    public static final RegistryObject<Block> ORDOVICIAN_FOSSILIFEROUS_STONE = registerBlock("ordovician_fossiliferous_stone", () -> new DropExperienceBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
    public static final RegistryObject<Block> PALEOGENE_FOSSILIFEROUS_STONE = registerBlock("paleogene_fossiliferous_stone", () -> new DropExperienceBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
    public static final RegistryObject<Block> PERMIAN_FOSSILIFEROUS_STONE = registerBlock("permian_fossiliferous_stone", () -> new DropExperienceBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
    public static final RegistryObject<Block> SILURIAN_FOSSILIFEROUS_STONE = registerBlock("silurian_fossiliferous_stone", () -> new DropExperienceBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
    public static final RegistryObject<Block> TRIASSIC_FOSSILIFEROUS_STONE = registerBlock("triassic_fossiliferous_stone", () -> new DropExperienceBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
    public static final RegistryObject<Block> AMBER_ORE = registerBlock("amber_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
    public static final RegistryObject<Block> SULFUR_ORE = registerBlock("sulfur_ore", () -> new Block(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
    public static final RegistryObject<Block> DEEPSLATE_SULFUR_ORE = registerBlock("deepslate_sulfur_ore", () -> new Block(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(3.0F, 3.0F)));

    //BLOCKS
    public static final RegistryObject<Block> AMBER_BLOCK = registerBlock("amber_block", () -> new Block(BlockBehaviour.Properties.of().noOcclusion().sound(SoundType.STONE)));

    //MACHINES
    public static final RegistryObject<Block> ACID_SHOWER = registerBlock("acid_shower", () -> new AcidShowerBlock(BlockBehaviour.Properties.of().noLootTable().requiresCorrectToolForDrops().strength(3.0F, 3.0F)));

    //PLANTS
    public static final RegistryObject<Block> BELEMNOPTERIS = registerBlock("belemnopteris", () -> new Belemnopteris(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).replaceable().instabreak().dynamicShape().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).ignitedByLava().pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> CLATHROPTERIS = registerBlock("clathropteris", () -> new Clathropteris(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).replaceable().instabreak().dynamicShape().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).ignitedByLava().pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> PACHYPTERIS_SHOOT = BLOCKS.register("pachypteris_shoot", () -> new PachypterisShoot(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).instabreak().dynamicShape().sound(SoundType.GRASS).ignitedByLava().noLootTable().pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> PACHYPTERIS_TRUNK = BLOCKS.register("pachypteris_trunk", () -> new PachypterisTrunk(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).ignitedByLava()));
    public static final RegistryObject<Block> NEUROPTERIS_TRUNK = BLOCKS.register("neuropteris_trunk", () -> new NeuropterisTrunk(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).ignitedByLava()));
    public static final RegistryObject<Block> NEUROPTERIS_SHOOT = BLOCKS.register("neuropteris_shoot", () -> new NeuropterisShoot(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).instabreak().dynamicShape().sound(SoundType.GRASS).ignitedByLava().noLootTable().pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> FIELD_HORSETAIL = BLOCKS.register("field_horsetail", () -> new FieldHorsetail(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).replaceable().dynamicShape().instabreak().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).ignitedByLava().pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> CONIOPTERIS = registerBlock("coniopteris", () -> new Coniopteris(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).replaceable().instabreak().dynamicShape().sound(SoundType.GRASS).offsetType(BlockBehaviour.OffsetType.XZ).ignitedByLava().pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> PARACYCAS_TRUNK = BLOCKS.register("paracycas_trunk", () -> new ParacycasTrunk(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).sound(SoundType.WOOD).ignitedByLava()));
    public static final RegistryObject<Block> PARACYCAS_SHOOT = BLOCKS.register("paracycas_shoot", () -> new ParacycasShoot(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).instabreak().dynamicShape().sound(SoundType.GRASS).ignitedByLava().noLootTable().pushReaction(PushReaction.DESTROY)));
    public static final RegistryObject<Block> AQUA_STONY_CORAL = registerBlock("aqua_stony_coral", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRAIN_CORAL).noOcclusion()));
    public static final RegistryObject<Block> BLUE_STONY_CORAL = registerBlock("blue_stony_coral", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRAIN_CORAL).noOcclusion()));
    public static final RegistryObject<Block> GREEN_STONY_CORAL = registerBlock("green_stony_coral", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRAIN_CORAL).noOcclusion()));
    public static final RegistryObject<Block> PINK_STONY_CORAL = registerBlock("pink_stony_coral", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRAIN_CORAL).noOcclusion()));
    public static final RegistryObject<Block> BLUE_SOFT_FAN_CORAL = registerBlock("blue_soft_fan_coral", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRAIN_CORAL).noOcclusion()));
    public static final RegistryObject<Block> BLUE_STAGHORN_CORAL = registerBlock("blue_staghorn_coral", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRAIN_CORAL).noOcclusion()));
    public static final RegistryObject<Block> RED_STAGHORN_CORAL = registerBlock("red_staghorn_coral", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRAIN_CORAL).noOcclusion()));
    public static final RegistryObject<Block> CARNATION_TREE_CORAL = registerBlock("carnation_tree_coral", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRAIN_CORAL).noOcclusion()));
    public static final RegistryObject<Block> ORANGE_BAMBOO_CORAL = registerBlock("orange_bamboo_coral", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRAIN_CORAL).noOcclusion()));
    public static final RegistryObject<Block> RED_BAMBOO_CORAL = registerBlock("red_bamboo_coral", () -> new Block(BlockBehaviour.Properties.copy(Blocks.BRAIN_CORAL).noOcclusion()));
    public static final RegistryObject<Block> FOREST_BEDDING = registerBlock("forest_bedding", () -> new ModGrassBlock(BlockBehaviour.Properties.of().noOcclusion()));

    //SAPLINGS
    // plantType:
    //  0: Neuropteris
    //  1: Pachypteris
    //  2: Paracycas
    public static final RegistryObject<Block> NEUROPTERIS_SAPLING = registerBlock("neuropteris_sapling", () -> new PlantSapling(0, BlockBehaviour.Properties.of().noOcclusion().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> PACHYPTERIS_SAPLING = registerBlock("pachypteris_sapling", () -> new PlantSapling(1, BlockBehaviour.Properties.of().noOcclusion().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> PARACYCAS_SAPLING = registerBlock("paracycas_sapling", () -> new PlantSapling(2, BlockBehaviour.Properties.of().noOcclusion().sound(SoundType.GRASS)));

    //DECORATION BLOCKS
    public static final RegistryObject<Block> LEAF_LITTER = registerBlock("leaf_litter", () -> new Block(BlockBehaviour.Properties.of().noOcclusion().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> LOAMY_DIRT = registerBlock("loamy_dirt", () -> new ModGrassBlock(BlockBehaviour.Properties.of().noOcclusion()));
    public static final RegistryObject<Block> LOAMY_FOREST_BEDDING = registerBlock("loamy_forest_bedding", () -> new ModGrassBlock(BlockBehaviour.Properties.of().noOcclusion()));
    public static final RegistryObject<Block> LOAMY_ORGANIC_FOREST_BEDDING = registerBlock("loamy_organic_forest_bedding", () -> new ModGrassBlock(BlockBehaviour.Properties.of().noOcclusion()));
    public static final RegistryObject<Block> LUSH_WET_DIRT = registerBlock("lush_wet_dirt", () -> new ModGrassBlock(BlockBehaviour.Properties.of().noOcclusion()));
    public static final RegistryObject<Block> LUSH_WET_LOAMY_DIRT = registerBlock("lush_wet_loamy_dirt", () -> new ModGrassBlock(BlockBehaviour.Properties.of().noOcclusion()));
    public static final RegistryObject<Block> LUSH_WET_MUDDY_DIRT = registerBlock("lush_wet_muddy_dirt", () -> new ModGrassBlock(BlockBehaviour.Properties.of().noOcclusion()));
    public static final RegistryObject<Block> LUSH_WET_ORANGE_SANDY_DIRT = registerBlock("lush_wet_orange_sandy_dirt", () -> new ModSandBlock(BlockBehaviour.Properties.of().noOcclusion()));
    public static final RegistryObject<Block> LUSH_WET_SANDY_DIRT = registerBlock("lush_wet_sandy_dirt", () -> new ModSandBlock(BlockBehaviour.Properties.of().noOcclusion()));
    public static final RegistryObject<Block> MUDDY_DIRT = registerBlock("muddy_dirt", () -> new Block(BlockBehaviour.Properties.of().noOcclusion().sound(SoundType.GRASS)));
    public static final RegistryObject<Block> MUDDY_FOREST_BEDDING = registerBlock("muddy_forest_bedding", () -> new ModGrassBlock(BlockBehaviour.Properties.of().noOcclusion()));
    public static final RegistryObject<Block> MUDDY_ORGANIC_FOREST_BEDDING = registerBlock("muddy_organic_forest_bedding", () -> new ModGrassBlock(BlockBehaviour.Properties.of().noOcclusion()));
    public static final RegistryObject<Block> ORANGE_SANDY_DIRT = registerBlock("orange_sandy_dirt", () -> new Block(BlockBehaviour.Properties.of().noOcclusion().sound(SoundType.SAND)));
    public static final RegistryObject<Block> ORGANIC_DIRT = registerBlock("organic_dirt", () -> new ModGrassBlock(BlockBehaviour.Properties.of().noOcclusion()));
    public static final RegistryObject<Block> ORGANIC_LOAMY_DIRT = registerBlock("organic_loamy_dirt", () -> new ModGrassBlock(BlockBehaviour.Properties.of().noOcclusion()));
    public static final RegistryObject<Block> ORGANIC_MUDDY_DIRT = registerBlock("organic_muddy_dirt", () -> new ModGrassBlock(BlockBehaviour.Properties.of().noOcclusion()));
    public static final RegistryObject<Block> ORGANIC_ORANGE_SANDY_DIRT = registerBlock("organic_orange_sandy_dirt", () -> new ModSandBlock(BlockBehaviour.Properties.of().noOcclusion()));
    public static final RegistryObject<Block> ORGANIC_SANDY_DIRT = registerBlock("organic_sandy_dirt", () -> new ModSandBlock(BlockBehaviour.Properties.of().noOcclusion()));
    public static final RegistryObject<Block> SANDY_DIRT = registerBlock("sandy_dirt", () -> new Block(BlockBehaviour.Properties.of().noOcclusion().sound(SoundType.SAND)));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
