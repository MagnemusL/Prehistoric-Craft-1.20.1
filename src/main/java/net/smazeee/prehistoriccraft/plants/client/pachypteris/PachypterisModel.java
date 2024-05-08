package net.smazeee.prehistoriccraft.plants.client.pachypteris;

import net.minecraft.resources.ResourceLocation;
import net.smazeee.prehistoriccraft.PrehistoricCraft;
import net.smazeee.prehistoriccraft.plants.entity.PachypterisBlockEntity;
import software.bernie.geckolib.model.GeoModel;

public class PachypterisModel extends GeoModel<PachypterisBlockEntity> {
    @Override
    public ResourceLocation getModelResource(PachypterisBlockEntity animatable) {
        return new ResourceLocation(PrehistoricCraft.MODID, "geo/plants/pachypteris.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(PachypterisBlockEntity animatable) {
        return new ResourceLocation(PrehistoricCraft.MODID, "textures/block/pachypteris.png");
    }

    @Override
    public ResourceLocation getAnimationResource(PachypterisBlockEntity animatable) {
        return null;
    }
}