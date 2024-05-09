package net.smazeee.prehistoriccraft.datagen;

import com.google.gson.JsonObject;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.client.model.generators.loaders.ObjModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.smazeee.prehistoriccraft.PrehistoricCraft;
import net.smazeee.prehistoriccraft.block.ModBlocks;
import net.smazeee.prehistoriccraft.block.bakedmodel.CableModelLoader;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, PrehistoricCraft.MODID, exFileHelper);
    }

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

        registerCable();
        registerFacade();
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

    private void registerCable() {
        BlockModelBuilder model = models().getBuilder("cable")
                .parent(models().getExistingFile(mcLoc("cube")))
                .customLoader((builder, helper) -> new CableLoaderBuilder(CableModelLoader.GENERATOR_LOADER, builder, helper, false))
                .end();
        simpleBlock(ModBlocks.CABLE.get(), model);
    }

    private void registerFacade() {
        BlockModelBuilder model = models().getBuilder("facade")
                .parent(models().getExistingFile(mcLoc("cube")))
                .customLoader((builder, helper) -> new CableLoaderBuilder(CableModelLoader.GENERATOR_LOADER, builder, helper, true))
                .end();
        simpleBlock(ModBlocks.FACADE.get(), model);
    }

    public static class CableLoaderBuilder extends CustomLoaderBuilder<BlockModelBuilder> {

        private final boolean facade;

        public CableLoaderBuilder(ResourceLocation loader, BlockModelBuilder parent, ExistingFileHelper existingFileHelper,
                                  boolean facade) {
            super(loader, parent, existingFileHelper);
            this.facade = facade;
        }

        @Override
        public JsonObject toJson(JsonObject json) {
            JsonObject obj = super.toJson(json);
            obj.addProperty("facade", facade);
            return obj;
        }
    }
}
