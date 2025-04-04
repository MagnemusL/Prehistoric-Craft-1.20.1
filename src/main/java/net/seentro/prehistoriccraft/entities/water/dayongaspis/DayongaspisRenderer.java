package net.seentro.prehistoriccraft.entities.water.dayongaspis;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class DayongaspisRenderer extends GeoEntityRenderer<DayongaspisEntity> {
    public DayongaspisRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DayongaspisModel());
    }
}
