package net.smazeee.prehistoriccraft.datagen;

import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.smazeee.prehistoriccraft.PrehistoricCraft;
import net.smazeee.prehistoriccraft.block.ModBlocks;
import net.smazeee.prehistoriccraft.block.custom.ModGrassBlock;

import static net.minecraftforge.client.model.generators.ModelProvider.BLOCK_FOLDER;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, PrehistoricCraft.MODID, exFileHelper);
    }

    public ResourceLocation forest_bedding_top = new ResourceLocation(PrehistoricCraft.MODID, "block/forest_bedding_top");
    public ResourceLocation org_forest_bedding_top = new ResourceLocation(PrehistoricCraft.MODID, "block/organic_forest_bedding_top");
    public ResourceLocation lush_wet_top = new ResourceLocation(PrehistoricCraft.MODID, "block/lush_wet_dirt_top");

    private String name(Block block) {
        return key(block).getPath();
    }

    private ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }

    @Override
    protected void registerStatesAndModels() {
        blockItem(ModBlocks.CAMBRIAN_FOSSILIFEROUS_STONE);
        blockItem(ModBlocks.PRECAMBRIAN_FOSSILIFEROUS_STONE);
        blockItem(ModBlocks.CARBONIFEROUS_FOSSILIFEROUS_STONE);
        blockItem(ModBlocks.CRETACEOUS_FOSSILIFEROUS_STONE);
        blockItem(ModBlocks.DEVONIAN_FOSSILIFEROUS_STONE);
        blockItem(ModBlocks.JURASSIC_FOSSILIFEROUS_STONE);
        blockItem(ModBlocks.NEOGENE_FOSSILIFEROUS_STONE);
        blockItem(ModBlocks.ORDOVICIAN_FOSSILIFEROUS_STONE);
        blockItem(ModBlocks.PALEOGENE_FOSSILIFEROUS_STONE);
        blockItem(ModBlocks.PERMIAN_FOSSILIFEROUS_STONE);
        blockItem(ModBlocks.SILURIAN_FOSSILIFEROUS_STONE);
        blockItem(ModBlocks.TRIASSIC_FOSSILIFEROUS_STONE);
        blockItem(ModBlocks.AMBER_ORE);

        blockItem(ModBlocks.SULFUR_ORE);
        blockItem(ModBlocks.DEEPSLATE_SULFUR_ORE);

        blockItemCross(ModBlocks.AQUA_STONY_CORAL);
        blockItemCross(ModBlocks.BLUE_STONY_CORAL);
        blockItemCross(ModBlocks.GREEN_STONY_CORAL);
        blockItemCross(ModBlocks.PINK_STONY_CORAL);
        blockItemCross(ModBlocks.BLUE_SOFT_FAN_CORAL);
        blockItemCross(ModBlocks.BLUE_STAGHORN_CORAL);
        blockItemCross(ModBlocks.RED_STAGHORN_CORAL);
        blockItemCross(ModBlocks.CARNATION_TREE_CORAL);
        blockItemCross(ModBlocks.ORANGE_BAMBOO_CORAL);
        blockItemCross(ModBlocks.RED_BAMBOO_CORAL);

        blockItem(ModBlocks.LEAF_LITTER);
        blockItem(ModBlocks.LOAMY_DIRT);
        blockItem(ModBlocks.MUDDY_DIRT);
        blockItem(ModBlocks.ORANGE_SANDY_DIRT);
        blockItem(ModBlocks.SANDY_DIRT);

        normalGrass(ModBlocks.ORGANIC_DIRT, 1);
        normalGrass(ModBlocks.ORGANIC_LOAMY_DIRT, 2);
        normalGrass(ModBlocks.ORGANIC_MUDDY_DIRT, 3);
        normalGrass(ModBlocks.ORGANIC_ORANGE_SANDY_DIRT, 4);
        normalGrass(ModBlocks.ORGANIC_SANDY_DIRT, 5);

        lushWet(ModBlocks.LUSH_WET_DIRT, 1);
        lushWet(ModBlocks.LUSH_WET_LOAMY_DIRT, 2);
        lushWet(ModBlocks.LUSH_WET_MUDDY_DIRT, 3);
        lushWet(ModBlocks.LUSH_WET_ORANGE_SANDY_DIRT, 4);
        lushWet(ModBlocks.LUSH_WET_SANDY_DIRT, 5);

        forestBedding(ModBlocks.FOREST_BEDDING, false, 1);
        forestBedding(ModBlocks.LOAMY_FOREST_BEDDING, false, 2);
        forestBedding(ModBlocks.LOAMY_ORGANIC_FOREST_BEDDING, true, 2);
        forestBedding(ModBlocks.MUDDY_FOREST_BEDDING, false, 3);
        forestBedding(ModBlocks.MUDDY_ORGANIC_FOREST_BEDDING, true, 3);
    }

    public ModelFile crossBlock(Block block) {
        return models().cross(name(block), blockTexture(block));
    }

    private void blockItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void blockItemCross(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), crossBlock(blockRegistryObject.get()));
    }

    private void block(RegistryObject<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    public void axisBlockNormal(Block block, ResourceLocation side, ResourceLocation bottom, ResourceLocation top) {
        axisBlockNormal(block,
                models().cubeBottomTop(name(block), side, bottom, top));
    }

    public void axisBlockNormal(Block block, ModelFile horizontal) {
        getVariantBuilder(block)
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Y)
                .modelForState().modelFile(horizontal).addModel()
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Z)
                .modelForState().modelFile(horizontal).addModel()
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.X)
                .modelForState().modelFile(horizontal).addModel();
        simpleBlockItem(block, horizontal);
    }

    public void normalGrass(RegistryObject<Block> block, int type) {
        switch (type) {
            case 1 -> axisBlockNormal(block.get(), extendNormal(blockTexture(block.get()), "_side"), blockTexture(Blocks.DIRT), extendNormal(blockTexture(block.get()), "_top"));
            case 2 -> axisBlockNormal(block.get(), extendNormal(blockTexture(block.get()), "_side"), new ResourceLocation(PrehistoricCraft.MODID, "block/loamy_dirt"), extendNormal(blockTexture(block.get()), "_top"));
            case 3 -> axisBlockNormal(block.get(), extendNormal(blockTexture(block.get()), "_side"), new ResourceLocation(PrehistoricCraft.MODID, "block/muddy_dirt"), extendNormal(blockTexture(block.get()), "_top"));
            case 4 -> axisBlockNormal(block.get(), extendNormal(blockTexture(block.get()), "_side"), new ResourceLocation(PrehistoricCraft.MODID, "block/orange_sandy_dirt"), extendNormal(blockTexture(block.get()), "_top"));
            case 5 -> axisBlockNormal(block.get(), extendNormal(blockTexture(block.get()), "_side"), new ResourceLocation(PrehistoricCraft.MODID, "block/sandy_dirt"), extendNormal(blockTexture(block.get()), "_top"));
        }
    }

    public void lushWet(RegistryObject<Block> block, int type) {
        switch (type) {
            case 1 -> axisBlockNormal(block.get(), extendNormal(blockTexture(block.get()), "_side"), blockTexture(Blocks.DIRT), lush_wet_top);
            case 2 -> axisBlockNormal(block.get(), extendNormal(blockTexture(block.get()), "_side"), new ResourceLocation(PrehistoricCraft.MODID, "block/loamy_dirt"), lush_wet_top);
            case 3 -> axisBlockNormal(block.get(), extendNormal(blockTexture(block.get()), "_side"), new ResourceLocation(PrehistoricCraft.MODID, "block/muddy_dirt"), lush_wet_top);
            case 4 -> axisBlockNormal(block.get(), extendNormal(blockTexture(block.get()), "_side"), new ResourceLocation(PrehistoricCraft.MODID, "block/orange_sandy_dirt"), lush_wet_top);
            case 5 -> axisBlockNormal(block.get(), extendNormal(blockTexture(block.get()), "_side"), new ResourceLocation(PrehistoricCraft.MODID, "block/sandy_dirt"), lush_wet_top);
        }
    }

    public void forestBedding(RegistryObject<Block> block, boolean organic, int type) {
        if (!organic) {
            switch (type) {
                case 1 -> axisBlockNormal(block.get(), extendNormal(blockTexture(block.get()), "_side"), blockTexture(Blocks.DIRT), forest_bedding_top);
                case 2 -> axisBlockNormal(block.get(), extendNormal(blockTexture(block.get()), "_side"), new ResourceLocation(PrehistoricCraft.MODID, "block/loamy_dirt"), forest_bedding_top);
                case 3 -> axisBlockNormal(block.get(), extendNormal(blockTexture(block.get()), "_side"), new ResourceLocation(PrehistoricCraft.MODID, "block/muddy_dirt"), forest_bedding_top);
                case 4 -> axisBlockNormal(block.get(), extendNormal(blockTexture(block.get()), "_side"), new ResourceLocation(PrehistoricCraft.MODID, "block/orange_sandy_dirt"), forest_bedding_top);
                case 5 -> axisBlockNormal(block.get(), extendNormal(blockTexture(block.get()), "_side"), new ResourceLocation(PrehistoricCraft.MODID, "block/sandy_dirt"), forest_bedding_top);
            }
        } else {
            switch (type) {
                case 1 -> axisBlockNormal(block.get(), extendNormal(blockTexture(block.get()), "_side"), blockTexture(Blocks.DIRT), org_forest_bedding_top);
                case 2 -> axisBlockNormal(block.get(), extendNormal(blockTexture(block.get()), "_side"), new ResourceLocation(PrehistoricCraft.MODID, "block/loamy_dirt"), org_forest_bedding_top);
                case 3 -> axisBlockNormal(block.get(), extendNormal(blockTexture(block.get()), "_side"), new ResourceLocation(PrehistoricCraft.MODID, "block/muddy_dirt"), org_forest_bedding_top);
                case 4 -> axisBlockNormal(block.get(), extendNormal(blockTexture(block.get()), "_side"), new ResourceLocation(PrehistoricCraft.MODID, "block/orange_sandy_dirt"), org_forest_bedding_top);
                case 5 -> axisBlockNormal(block.get(), extendNormal(blockTexture(block.get()), "_side"), new ResourceLocation(PrehistoricCraft.MODID, "block/sandy_dirt"), org_forest_bedding_top);
            }
        }
    }

    private ResourceLocation extendNormal(ResourceLocation rl, String suffix) {
        return new ResourceLocation(rl.getNamespace(), rl.getPath() + suffix);
    }
}
