package net.smazeee.prehistoriccraft.item.client;

import net.smazeee.prehistoriccraft.item.custom.ExtractionMachineBlockItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class ExtractionMachineBlockItemRenderer extends GeoItemRenderer<ExtractionMachineBlockItem> {
    public ExtractionMachineBlockItemRenderer() {
        super(new ExtractionMachineBlockItemModel());
    }
}
