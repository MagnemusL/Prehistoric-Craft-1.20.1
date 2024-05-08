package net.smazeee.prehistoriccraft.plants.client.belemnopteris;

import net.minecraft.resources.ResourceLocation;
import net.smazeee.prehistoriccraft.PrehistoricCraft;
import net.smazeee.prehistoriccraft.plants.item.BelemnopterisBlockItem;
import software.bernie.geckolib.model.GeoModel;

public class BelemnopterisBlockItemModel extends GeoModel<BelemnopterisBlockItem> {
    @Override
    public ResourceLocation getModelResource(BelemnopterisBlockItem animatable) {
        return new ResourceLocation(PrehistoricCraft.MODID, "geo/plants/belemnopteris.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BelemnopterisBlockItem animatable) {
        return new ResourceLocation(PrehistoricCraft.MODID, "textures/block/belemnopteris.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BelemnopterisBlockItem animatable) {
        return null;
    }
}
