package net.smazeee.prehistoriccraft.item.client;

import net.minecraft.resources.ResourceLocation;
import net.smazeee.prehistoriccraft.PrehistoricCraft;
import net.smazeee.prehistoriccraft.item.custom.ExtractionMachineBlockItem;
import software.bernie.geckolib.model.GeoModel;

public class ExtractionMachineBlockItemModel extends GeoModel<ExtractionMachineBlockItem> {
    @Override
    public ResourceLocation getModelResource(ExtractionMachineBlockItem animatable) {
        return new ResourceLocation(PrehistoricCraft.MODID, "geo/extraction_machine.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ExtractionMachineBlockItem animatable) {
        return new ResourceLocation(PrehistoricCraft.MODID, "textures/block/extraction_machine.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ExtractionMachineBlockItem animatable) {
        return new ResourceLocation(PrehistoricCraft.MODID, "animations/extraction_machine.animation.json");
    }
}
