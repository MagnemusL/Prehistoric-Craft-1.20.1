package net.smazeee.prehistoriccraft.plants.client.fieldhorsetail;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.smazeee.prehistoriccraft.plants.entity.FieldHorsetailBlockEntity;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class FieldHorsetailRenderer extends GeoBlockRenderer<FieldHorsetailBlockEntity> {
    public FieldHorsetailRenderer(BlockEntityRendererProvider.Context context) {
        super(new FieldHorsetailModel());
    }

    @Override
    public void preRender(PoseStack poseStack, FieldHorsetailBlockEntity animatable, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        if(!isReRender) {
            BlockPos pos = animatable.getBlockPos();
            Level level = animatable.getLevel();
            BlockState state = level.getBlockState(pos);
            state.getOffset(level, pos);
            poseStack.translate(state.getOffset(level, pos).x, state.getOffset(level, pos).y, state.getOffset(level, pos).z);
        }
    }
}
