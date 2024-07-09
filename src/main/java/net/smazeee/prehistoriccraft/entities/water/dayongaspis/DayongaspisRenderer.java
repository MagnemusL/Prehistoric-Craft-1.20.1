package net.smazeee.prehistoriccraft.entities.water.dayongaspis;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.GeoRenderer;

public class DayongaspisRenderer extends GeoEntityRenderer<Dayongaspis> {
    public DayongaspisRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new DayongaspisModel());
    }
}
