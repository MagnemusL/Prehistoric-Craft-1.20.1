package net.smazeee.prehistoriccraft.plants.client.neuropteris;

import net.minecraft.resources.ResourceLocation;
import net.smazeee.prehistoriccraft.PrehistoricCraft;
import net.smazeee.prehistoriccraft.plants.item.NeuropterisBlockItem;
import software.bernie.geckolib.model.GeoModel;

public class NeuropterisBlockItemModel extends GeoModel<NeuropterisBlockItem> {
    @Override
    public ResourceLocation getModelResource(NeuropterisBlockItem animatable) {
        return new ResourceLocation(PrehistoricCraft.MODID, "geo/plants/neuropteris.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(NeuropterisBlockItem animatable) {
        return new ResourceLocation(PrehistoricCraft.MODID, "textures/block/neuropteris.png");
    }

    @Override
    public ResourceLocation getAnimationResource(NeuropterisBlockItem animatable) {
        return null;
    }
}
