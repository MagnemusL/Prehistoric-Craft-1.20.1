package net.smazeee.prehistoriccraft.entities.land.styracosaurus;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.smazeee.prehistoriccraft.PrehistoricCraft;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.object.DataTicket;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class StyracosaurusModel extends GeoModel<Styracosaurus> {
    @Override
    public ResourceLocation getModelResource(Styracosaurus animatable) {
        return new ResourceLocation(PrehistoricCraft.MODID, "geo/land/styracosaurus/styracosaurus_adult.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Styracosaurus animatable) {
        return new ResourceLocation(PrehistoricCraft.MODID, "textures/entity/land/styracosaurus/styracosaurus_male_adult.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Styracosaurus animatable) {
        return new ResourceLocation(PrehistoricCraft.MODID, "animations/land/styracosaurus/styracosaurus_adult.animation.json");
    }
}
