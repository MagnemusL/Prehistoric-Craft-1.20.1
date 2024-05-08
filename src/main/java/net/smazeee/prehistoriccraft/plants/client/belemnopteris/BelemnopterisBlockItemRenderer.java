package net.smazeee.prehistoriccraft.plants.client.belemnopteris;

import net.smazeee.prehistoriccraft.plants.item.BelemnopterisBlockItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class BelemnopterisBlockItemRenderer extends GeoItemRenderer<BelemnopterisBlockItem> {
    public BelemnopterisBlockItemRenderer() {
        super(new BelemnopterisBlockItemModel());
    }
}
