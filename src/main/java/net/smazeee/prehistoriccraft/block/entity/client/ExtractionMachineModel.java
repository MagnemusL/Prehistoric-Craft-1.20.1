package net.smazeee.prehistoriccraft.block.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.smazeee.prehistoriccraft.PrehistoricCraft;
import net.smazeee.prehistoriccraft.block.entity.ExtractionMachineBlockEntity;
import software.bernie.geckolib.model.GeoModel;

public class ExtractionMachineModel extends GeoModel<ExtractionMachineBlockEntity> {
    @Override
    public ResourceLocation getModelResource(ExtractionMachineBlockEntity animatable) {
        return new ResourceLocation(PrehistoricCraft.MODID, "geo/extraction_machine.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ExtractionMachineBlockEntity animatable) {
        return new ResourceLocation(PrehistoricCraft.MODID, "textures/block/extraction_machine.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ExtractionMachineBlockEntity animatable) {
        return new ResourceLocation(PrehistoricCraft.MODID, "animations/extraction_machine.animation.json");
    }
}
