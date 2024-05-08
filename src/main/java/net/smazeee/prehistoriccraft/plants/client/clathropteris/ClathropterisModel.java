package net.smazeee.prehistoriccraft.plants.client.clathropteris;

import net.minecraft.resources.ResourceLocation;
import net.smazeee.prehistoriccraft.PrehistoricCraft;
import net.smazeee.prehistoriccraft.plants.entity.ClathropterisBlockEntity;
import software.bernie.geckolib.model.GeoModel;

public class ClathropterisModel extends GeoModel<ClathropterisBlockEntity> {
    @Override
    public ResourceLocation getModelResource(ClathropterisBlockEntity animatable) {
        return new ResourceLocation(PrehistoricCraft.MODID, "geo/plants/clathropteris.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ClathropterisBlockEntity animatable) {
        return new ResourceLocation(PrehistoricCraft.MODID, "textures/block/clathropteris.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ClathropterisBlockEntity animatable) {
        return null;
    }
}