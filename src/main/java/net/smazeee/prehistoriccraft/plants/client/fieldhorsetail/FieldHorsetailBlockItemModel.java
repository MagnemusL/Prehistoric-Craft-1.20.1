package net.smazeee.prehistoriccraft.plants.client.fieldhorsetail;

import net.minecraft.resources.ResourceLocation;
import net.smazeee.prehistoriccraft.PrehistoricCraft;
import net.smazeee.prehistoriccraft.plants.item.FieldHorsetailBlockItem;
import software.bernie.geckolib.model.GeoModel;

public class FieldHorsetailBlockItemModel extends GeoModel<FieldHorsetailBlockItem> {
    @Override
    public ResourceLocation getModelResource(FieldHorsetailBlockItem animatable) {
        return new ResourceLocation(PrehistoricCraft.MODID, "geo/plants/field_horsetail.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(FieldHorsetailBlockItem animatable) {
        return new ResourceLocation(PrehistoricCraft.MODID, "textures/block/field_horsetail.png");
    }

    @Override
    public ResourceLocation getAnimationResource(FieldHorsetailBlockItem animatable) {
        return null;
    }
}
