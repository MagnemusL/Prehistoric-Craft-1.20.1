package net.seentro.prehistoriccraft.entities.water.dayongaspis;

import net.minecraft.resources.ResourceLocation;
import net.seentro.prehistoriccraft.PrehistoricCraft;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.model.GeoModel;

public class DayongaspisModel extends GeoModel {
    @Override
    public ResourceLocation getModelResource(GeoAnimatable animatable) {
        return new ResourceLocation(PrehistoricCraft.MODID, "geo/water/dayongaspis.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GeoAnimatable animatable) {
        return new ResourceLocation(PrehistoricCraft.MODID, "textures/entity/water/dayongaspis_texture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(GeoAnimatable animatable) {
        return new ResourceLocation(PrehistoricCraft.MODID, "animations/water/dayongaspis.animation.json");
    }
}
