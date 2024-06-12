package net.smazeee.prehistoriccraft.block.entity.client;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.smazeee.prehistoriccraft.block.entity.ExtractionMachineBlockEntity;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class ExtractionMachineRenderer extends GeoBlockRenderer<ExtractionMachineBlockEntity> {
    public ExtractionMachineRenderer(BlockEntityRendererProvider.Context context) {
        super(new ExtractionMachineModel());
    }
}
