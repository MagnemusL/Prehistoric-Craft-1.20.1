package net.smazeee.prehistoriccraft.datagen.loot;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;
import net.smazeee.prehistoriccraft.block.ModBlocks;
import net.smazeee.prehistoriccraft.item.ModItems;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    private void drop(RegistryObject<Block> block) {
        this.dropSelf(block.get());
    }

    private void dropPillar(RegistryObject<RotatedPillarBlock> block) {
        this.dropSelf(block.get());
    }

    private void dropOre(RegistryObject<Block> block, RegistryObject<Item> item) {
        this.add(block.get(), block1 -> createOreDrop(block.get(), item.get()));
    }

    @Override
    protected void generate() {
        dropOre(ModBlocks.CAMBRIAN_FOSSILIFEROUS_STONE, ModItems.CAMBRIAN_FOSSIL);
        dropOre(ModBlocks.PRECAMBRIAN_FOSSILIFEROUS_STONE, ModItems.PRECAMBRIAN_FOSSIL);
        dropOre(ModBlocks.CARBONIFEROUS_FOSSILIFEROUS_STONE, ModItems.CARBONIFEROUS_FOSSIL);
        dropOre(ModBlocks.CRETACEOUS_FOSSILIFEROUS_STONE, ModItems.CRETACEOUS_FOSSIL);
        dropOre(ModBlocks.DEVONIAN_FOSSILIFEROUS_STONE, ModItems.DEVONIAN_FOSSIL);
        dropOre(ModBlocks.JURASSIC_FOSSILIFEROUS_STONE, ModItems.JURASSIC_FOSSIL);
        dropOre(ModBlocks.ORDOVICIAN_FOSSILIFEROUS_STONE, ModItems.ORDOVICIAN_FOSSIL);
        dropOre(ModBlocks.PERMIAN_FOSSILIFEROUS_STONE, ModItems.PERMIAN_FOSSIL);
        dropOre(ModBlocks.SILURIAN_FOSSILIFEROUS_STONE, ModItems.SILURIAN_FOSSIL);
        dropOre(ModBlocks.TRIASSIC_FOSSILIFEROUS_STONE, ModItems.TRIASSIC_FOSSIL);
        dropOre(ModBlocks.AMBER_ORE, ModItems.AMBER);

        dropOre(ModBlocks.SULFUR_ORE, ModItems.SULFUR);
        dropOre(ModBlocks.DEEPSLATE_SULFUR_ORE, ModItems.SULFUR);

        this.add(ModBlocks.BELEMNOPTERIS.get(), (block) -> createShearsDispatchTable(block, this.applyExplosionDecay(block, LootItem.lootTableItem(Items.WHEAT_SEEDS).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 0.0F))))));
        this.add(ModBlocks.CLATHROPTERIS.get(), (block) -> createShearsDispatchTable(block, this.applyExplosionDecay(block, LootItem.lootTableItem(Items.WHEAT_SEEDS).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 0.0F))))));
        this.add(ModBlocks.PACHYPTERIS.get(), (block) -> createShearsDispatchTable(block, this.applyExplosionDecay(block, LootItem.lootTableItem(Items.WHEAT_SEEDS).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 0.0F))))));
        this.add(ModBlocks.NEUROPTERIS.get(), (block) -> createShearsDispatchTable(block, this.applyExplosionDecay(block, LootItem.lootTableItem(Items.WHEAT_SEEDS).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 0.0F))))));
        this.add(ModBlocks.FIELD_HORSETAIL.get(), (block) -> createShearsDispatchTable(block, this.applyExplosionDecay(block, LootItem.lootTableItem(Items.WHEAT_SEEDS).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 0.0F))))));
        this.add(ModBlocks.CONIOPTERIS.get(), (block) -> createShearsDispatchTable(block, this.applyExplosionDecay(block, LootItem.lootTableItem(Items.WHEAT_SEEDS).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 0.0F))))));
        this.add(ModBlocks.PARACYCAS.get(), (block) -> createShearsDispatchTable(block, this.applyExplosionDecay(block, LootItem.lootTableItem(Items.WHEAT_SEEDS).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 0.0F))))));

        drop(ModBlocks.AQUA_STONY_CORAL);
        drop(ModBlocks.BLUE_STONY_CORAL);
        drop(ModBlocks.GREEN_STONY_CORAL);
        drop(ModBlocks.PINK_STONY_CORAL);
        drop(ModBlocks.BLUE_SOFT_FAN_CORAL);
        drop(ModBlocks.BLUE_STAGHORN_CORAL);
        drop(ModBlocks.RED_STAGHORN_CORAL);
        drop(ModBlocks.CARNATION_TREE_CORAL);
        drop(ModBlocks.ORANGE_BAMBOO_CORAL);
        drop(ModBlocks.RED_BAMBOO_CORAL);

        drop(ModBlocks.FOREST_BEDDING);
        drop(ModBlocks.LEAF_LITTER);
        drop(ModBlocks.LOAMY_DIRT);
        drop(ModBlocks.LOAMY_FOREST_BEDDING);
        drop(ModBlocks.LOAMY_ORGANIC_FOREST_BEDDING);
        drop(ModBlocks.LUSH_WET_DIRT);
        drop(ModBlocks.LUSH_WET_LOAMY_DIRT);
        drop(ModBlocks.LUSH_WET_MUDDY_DIRT);
        drop(ModBlocks.LUSH_WET_ORANGE_SANDY_DIRT);
        drop(ModBlocks.LUSH_WET_SANDY_DIRT);
        drop(ModBlocks.MUDDY_DIRT);
        drop(ModBlocks.MUDDY_FOREST_BEDDING);
        drop(ModBlocks.MUDDY_ORGANIC_FOREST_BEDDING);
        drop(ModBlocks.ORANGE_SANDY_DIRT);
        drop(ModBlocks.ORGANIC_DIRT);
        drop(ModBlocks.ORGANIC_LOAMY_DIRT);
        drop(ModBlocks.ORGANIC_MUDDY_DIRT);
        drop(ModBlocks.ORGANIC_ORANGE_SANDY_DIRT);
        drop(ModBlocks.ORGANIC_SANDY_DIRT);
        drop(ModBlocks.SANDY_DIRT);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
