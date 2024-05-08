package net.smazeee.prehistoriccraft.plants.client.fieldhorsetail;

import net.minecraft.resources.ResourceLocation;
import net.smazeee.prehistoriccraft.PrehistoricCraft;
import net.smazeee.prehistoriccraft.plants.entity.FieldHorsetailBlockEntity;
import software.bernie.geckolib.model.GeoModel;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class FieldHorsetailModel extends GeoModel<FieldHorsetailBlockEntity> {
    @Override
    public ResourceLocation getModelResource(FieldHorsetailBlockEntity animatable) {
        Random rand = new Random();

        return switch (rand.nextInt(4)) {
            case 0 -> new ResourceLocation(PrehistoricCraft.MODID, "geo/plants/field_horsetail_1.geo.json");
            case 1 -> new ResourceLocation(PrehistoricCraft.MODID, "geo/plants/field_horsetail_2.geo.json");
            case 2 -> new ResourceLocation(PrehistoricCraft.MODID, "geo/plants/field_horsetail_3.geo.json");
            case 3 -> new ResourceLocation(PrehistoricCraft.MODID, "geo/plants/field_horsetail_4.geo.json");
            default -> null;
        };
    }

    @Override
    public ResourceLocation getTextureResource(FieldHorsetailBlockEntity animatable) {
        return new ResourceLocation(PrehistoricCraft.MODID, "textures/block/field_horsetail.png");
    }

    @Override
    public ResourceLocation getAnimationResource(FieldHorsetailBlockEntity animatable) {
        return null;
    }
}