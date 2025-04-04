package net.seentro.prehistoriccraft.block.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.seentro.prehistoriccraft.PrehistoricCraft;
import net.seentro.prehistoriccraft.block.entity.ExtractionMachineBlockEntity;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;

public class ExtractionMachineModel extends GeoModel<ExtractionMachineBlockEntity> {
    @Override
    public ResourceLocation getModelResource(ExtractionMachineBlockEntity extractionMachineBlockEntity) {
        return new ResourceLocation(PrehistoricCraft.MODID, "geo/extraction_machine.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ExtractionMachineBlockEntity extractionMachineBlockEntity) {
        return new ResourceLocation(PrehistoricCraft.MODID, "textures/block/extraction_machine.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ExtractionMachineBlockEntity animatable) {
        return new ResourceLocation(PrehistoricCraft.MODID, "animations/extraction_machine.animation.json");
    }
}
