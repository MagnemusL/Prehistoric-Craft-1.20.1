package net.smazeee.prehistoriccraft.plants.client.clathropteris;

import net.smazeee.prehistoriccraft.plants.item.ClathropterisBlockItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class ClathropterisBlockItemRenderer extends GeoItemRenderer<ClathropterisBlockItem> {
    public ClathropterisBlockItemRenderer() {
        super(new ClathropterisBlockItemModel());
    }
}
