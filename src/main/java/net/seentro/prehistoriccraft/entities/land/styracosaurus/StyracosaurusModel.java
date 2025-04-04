package net.seentro.prehistoriccraft.entities.land.styracosaurus;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.seentro.prehistoriccraft.PrehistoricCraft;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class StyracosaurusModel extends GeoModel<Styracosaurus> {
    @Override
    public ResourceLocation getModelResource(Styracosaurus animatable) {
        return new ResourceLocation(PrehistoricCraft.MODID, "geo/land/styracosaurus/styracosaurus_adult.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(Styracosaurus styracosaurus) {
        return new ResourceLocation(PrehistoricCraft.MODID, "textures/entity/land/styracosaurus/styracosaurus_male_adult.png");
    }

    @Override
    public ResourceLocation getAnimationResource(Styracosaurus animatable) {
        return new ResourceLocation(PrehistoricCraft.MODID, "animations/land/styracosaurus/styracosaurus_adult.animation.json");
    }

    @Override
    public void setCustomAnimations(Styracosaurus animatable, long instanceId, AnimationState<Styracosaurus> animationState) {
        CoreGeoBone neck = getAnimationProcessor().getBone("Neck5");

        if (neck != null) {
            EntityModelData data = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            neck.setRotX(data.headPitch() * Mth.DEG_TO_RAD);
            neck.setRotY(data.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }
}
