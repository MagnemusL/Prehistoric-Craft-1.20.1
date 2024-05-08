package net.smazeee.prehistoriccraft.plants.client.neuropteris;

import net.minecraft.resources.ResourceLocation;
import net.smazeee.prehistoriccraft.PrehistoricCraft;
import net.smazeee.prehistoriccraft.plants.entity.NeuropterisBlockEntity;
import software.bernie.geckolib.model.GeoModel;

public class NeuropterisModel extends GeoModel<NeuropterisBlockEntity> {
    @Override
    public ResourceLocation getModelResource(NeuropterisBlockEntity animatable) {
        return new ResourceLocation(PrehistoricCraft.MODID, "geo/plants/neuropteris.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(NeuropterisBlockEntity animatable) {
        return new ResourceLocation(PrehistoricCraft.MODID, "textures/block/neuropteris.png");
    }

    @Override
    public ResourceLocation getAnimationResource(NeuropterisBlockEntity animatable) {
        return null;
    }
}