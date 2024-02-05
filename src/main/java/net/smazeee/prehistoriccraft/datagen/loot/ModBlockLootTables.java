package net.smazeee.prehistoriccraft.datagen.loot;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
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

        drop(ModBlocks.ACID_SHOWER);
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
