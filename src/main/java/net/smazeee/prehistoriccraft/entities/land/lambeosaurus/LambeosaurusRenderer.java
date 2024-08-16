package net.smazeee.prehistoriccraft.entities.land.lambeosaurus;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class LambeosaurusRenderer extends GeoEntityRenderer<Lambeosaurus> {
    public LambeosaurusRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new LambeosaurusModel());
    }
}
