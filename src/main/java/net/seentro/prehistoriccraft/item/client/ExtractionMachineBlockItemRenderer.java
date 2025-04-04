package net.seentro.prehistoriccraft.item.client;

import net.seentro.prehistoriccraft.item.custom.ExtractionMachineBlockItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class ExtractionMachineBlockItemRenderer extends GeoItemRenderer<ExtractionMachineBlockItem> {
    public ExtractionMachineBlockItemRenderer() {
        super(new ExtractionMachineBlockItemModel());
    }
}
