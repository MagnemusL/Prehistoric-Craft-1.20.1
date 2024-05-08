package net.smazeee.prehistoriccraft.plants.client.belemnopteris;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.event.level.PistonEvent;
import net.smazeee.prehistoriccraft.PrehistoricCraft;
import net.smazeee.prehistoriccraft.plants.Belemnopteris;
import net.smazeee.prehistoriccraft.plants.entity.BelemnopterisBlockEntity;
import software.bernie.geckolib.model.GeoModel;

public class BelemnopterisModel extends GeoModel<BelemnopterisBlockEntity> {
    @Override
    public ResourceLocation getModelResource(BelemnopterisBlockEntity animatable) {
        return new ResourceLocation(PrehistoricCraft.MODID, "geo/plants/belemnopteris.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BelemnopterisBlockEntity animatable) {
        return new ResourceLocation(PrehistoricCraft.MODID, "textures/block/belemnopteris.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BelemnopterisBlockEntity animatable) {
        return null;
    }
}