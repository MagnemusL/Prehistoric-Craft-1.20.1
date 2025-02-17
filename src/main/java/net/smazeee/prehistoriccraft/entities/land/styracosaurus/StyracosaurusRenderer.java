package net.smazeee.prehistoriccraft.entities.land.styracosaurus;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

@SuppressWarnings("unchecked")
public class StyracosaurusRenderer extends GeoEntityRenderer<Styracosaurus> {
    public StyracosaurusRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new StyracosaurusModel());
    }


}
