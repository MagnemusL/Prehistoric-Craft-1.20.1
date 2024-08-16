package net.smazeee.prehistoriccraft.entities.land.lambeosaurus;

import net.minecraft.resources.ResourceLocation;
import net.smazeee.prehistoriccraft.PrehistoricCraft;
import software.bernie.geckolib.model.GeoModel;

public class LambeosaurusModel extends GeoModel<Lambeosaurus> {
    @Override
    public ResourceLocation getModelResource(Lambeosaurus animatable) {
        return new ResourceLocation(PrehistoricCraft.MODID, "geo/land/lambeosaurus/lambeosaurus_male_adult.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Lambeosaurus animatable) {
        return new ResourceLocation(PrehistoricCraft.MODID, "textures/entity/land/lambeosaurus/lambeosaurus_male_adult.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Lambeosaurus animatable) {
        return new ResourceLocation(PrehistoricCraft.MODID, "animation/land/lambeosaurus/lambeosaurus_male_adult.animation.json");
    }
}
