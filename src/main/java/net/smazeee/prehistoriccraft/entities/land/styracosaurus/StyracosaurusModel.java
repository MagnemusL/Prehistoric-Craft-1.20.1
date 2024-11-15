package net.smazeee.prehistoriccraft.entities.land.styracosaurus;

import net.minecraft.resources.ResourceLocation;
import net.smazeee.prehistoriccraft.PrehistoricCraft;
import software.bernie.geckolib.model.GeoModel;

public class StyracosaurusModel extends GeoModel<StyracosaurusEntity> {
    @Override
    public ResourceLocation getModelResource(StyracosaurusEntity animatable) {
        /*switch (animatable.getAge()) {
            case 0 -> new ResourceLocation(PrehistoricCraft.MODID, "geo/land/styracosaurus/styracosaurus_hatchling.geo.json");
            case 1 -> new ResourceLocation(PrehistoricCraft.MODID, "geo/land/styracosaurus/styracosaurus_juvenile.geo.json");
            case 2 -> new ResourceLocation(PrehistoricCraft.MODID, "geo/land/styracosaurus/styracosaurus_teen.geo.json");
            case 3 -> new ResourceLocation(PrehistoricCraft.MODID, "geo/land/styracosaurus/styracosaurus_subadult.geo.json");
            case 4 -> new ResourceLocation(PrehistoricCraft.MODID, "geo/land/styracosaurus/styracosaurus_adult.geo.json");
        };
        */
        return new ResourceLocation(PrehistoricCraft.MODID, "geo/land/styracosaurus/styracosaurus_adult.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(StyracosaurusEntity animatable) {
        return null;
    }

    @Override
    public ResourceLocation getAnimationResource(StyracosaurusEntity animatable) {
        return new ResourceLocation(PrehistoricCraft.MODID, "animations/land/styracosaurus/styracosaurus_adult.animation.json");
    }
}
