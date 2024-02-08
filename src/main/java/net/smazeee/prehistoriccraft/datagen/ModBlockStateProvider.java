package net.smazeee.prehistoriccraft.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.smazeee.prehistoriccraft.PrehistoricCraft;
import net.smazeee.prehistoriccraft.block.ModBlocks;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, PrehistoricCraft.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockItem(ModBlocks.CAMBRIAN_FOSSILIFEROUS_STONE);
        blockItem(ModBlocks.PRECAMBRIAN_FOSSILIFEROUS_STONE);
        blockItem(ModBlocks.PERMIAN_FOSSILIFEROUS_STONE);
        blockItem(ModBlocks.CARBONIFEROUS_FOSSILIFEROUS_STONE);
        blockItem(ModBlocks.CRETACEOUS_FOSSILIFEROUS_STONE);
        blockItem(ModBlocks.DEVONIAN_FOSSILIFEROUS_STONE);
        blockItem(ModBlocks.JURASSIC_FOSSILIFEROUS_STONE);
        blockItem(ModBlocks.ORDOVICIAN_FOSSILIFEROUS_STONE);
        blockItem(ModBlocks.SILURIAN_FOSSILIFEROUS_STONE);
        blockItem(ModBlocks.TRIASSIC_FOSSILIFEROUS_STONE);
        blockItem(ModBlocks.AMBER_ORE);

        blockItem(ModBlocks.SULFUR_ORE);
        blockItem(ModBlocks.DEEPSLATE_SULFUR_ORE);
    }

    private void blockItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void block(RegistryObject<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
