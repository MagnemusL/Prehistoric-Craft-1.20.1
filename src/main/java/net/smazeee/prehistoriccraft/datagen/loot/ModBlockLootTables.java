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
        //drop(ModBlocks.THING);
        //dropOre(ModBlocks.THING, ModItems.THING);

    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
