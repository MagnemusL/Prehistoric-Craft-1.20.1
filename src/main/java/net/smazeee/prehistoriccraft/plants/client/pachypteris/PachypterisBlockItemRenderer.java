package net.smazeee.prehistoriccraft.plants.client.pachypteris;

import net.smazeee.prehistoriccraft.plants.item.PachypterisBlockItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class PachypterisBlockItemRenderer extends GeoItemRenderer<PachypterisBlockItem> {
    public PachypterisBlockItemRenderer() {
        super(new PachypterisBlockItemModel());
    }
}
