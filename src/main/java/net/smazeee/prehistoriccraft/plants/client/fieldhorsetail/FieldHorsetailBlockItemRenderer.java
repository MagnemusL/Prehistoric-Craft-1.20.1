package net.smazeee.prehistoriccraft.plants.client.fieldhorsetail;

import net.smazeee.prehistoriccraft.plants.item.FieldHorsetailBlockItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class FieldHorsetailBlockItemRenderer extends GeoItemRenderer<FieldHorsetailBlockItem> {
    public FieldHorsetailBlockItemRenderer() {
        super(new FieldHorsetailBlockItemModel());
    }
}
