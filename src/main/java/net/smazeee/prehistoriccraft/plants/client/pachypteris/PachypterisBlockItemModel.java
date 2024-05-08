package net.smazeee.prehistoriccraft.plants.client.pachypteris;

import net.minecraft.resources.ResourceLocation;
import net.smazeee.prehistoriccraft.PrehistoricCraft;
import net.smazeee.prehistoriccraft.plants.item.PachypterisBlockItem;
import software.bernie.geckolib.model.GeoModel;

public class PachypterisBlockItemModel extends GeoModel<PachypterisBlockItem> {
    @Override
    public ResourceLocation getModelResource(PachypterisBlockItem animatable) {
        return new ResourceLocation(PrehistoricCraft.MODID, "geo/plants/pachypteris.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(PachypterisBlockItem animatable) {
        return new ResourceLocation(PrehistoricCraft.MODID, "textures/block/pachypteris.png");
    }

    @Override
    public ResourceLocation getAnimationResource(PachypterisBlockItem animatable) {
        return null;
    }
}
