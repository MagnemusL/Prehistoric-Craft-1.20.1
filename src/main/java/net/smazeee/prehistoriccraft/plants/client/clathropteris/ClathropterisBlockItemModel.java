package net.smazeee.prehistoriccraft.plants.client.clathropteris;

import net.minecraft.resources.ResourceLocation;
import net.smazeee.prehistoriccraft.PrehistoricCraft;
import net.smazeee.prehistoriccraft.plants.item.BelemnopterisBlockItem;
import net.smazeee.prehistoriccraft.plants.item.ClathropterisBlockItem;
import software.bernie.geckolib.model.GeoModel;

public class ClathropterisBlockItemModel extends GeoModel<ClathropterisBlockItem> {
    @Override
    public ResourceLocation getModelResource(ClathropterisBlockItem animatable) {
        return new ResourceLocation(PrehistoricCraft.MODID, "geo/plants/clathropteris.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ClathropterisBlockItem animatable) {
        return new ResourceLocation(PrehistoricCraft.MODID, "textures/block/clathropteris.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ClathropterisBlockItem animatable) {
        return null;
    }
}
