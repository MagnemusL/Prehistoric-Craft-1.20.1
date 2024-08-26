package net.smazeee.prehistoriccraft.entities.land.lambeosaurus;

import net.minecraft.resources.ResourceLocation;
import net.smazeee.prehistoriccraft.PrehistoricCraft;
import software.bernie.geckolib.model.GeoModel;

public class StyracosaurusModel extends GeoModel<StyracosaurusEntity> {
    @Override
    public ResourceLocation getModelResource(StyracosaurusEntity animatable) {
        switch (animatable.age) {
            case HATCHLING -> new ResourceLocation(PrehistoricCraft.MODID, "geo/land/styracosaurus/styracosaurus_hatchling.geo.json");
            case JUVENILE -> new ResourceLocation(PrehistoricCraft.MODID, "geo/land/styracosaurus/styracosaurus_juvenile.geo.json");
            case TEEN -> new ResourceLocation(PrehistoricCraft.MODID, "geo/land/styracosaurus/styracosaurus_teen.geo.json");
            case SUBADULT -> new ResourceLocation(PrehistoricCraft.MODID, "geo/land/styracosaurus/styracosaurus_subadult.geo.json");
            case ADULT -> new ResourceLocation(PrehistoricCraft.MODID, "geo/land/styracosaurus/styracosaurus_adult.geo.json");
        };
        return new ResourceLocation(PrehistoricCraft.MODID, "geo/land/styracosaurus/styracosaurus_adult.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(StyracosaurusEntity animatable) {
        return null;
    }

    @Override
    public ResourceLocation getAnimationResource(StyracosaurusEntity animatable) {
        return new ResourceLocation(PrehistoricCraft.MODID, "animation/land/styracosaurus/styracosaurus_male_adult.animation.json");
    }
}
