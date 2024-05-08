package net.smazeee.prehistoriccraft.plants.client.neuropteris;

import net.smazeee.prehistoriccraft.plants.item.NeuropterisBlockItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class NeuropterisBlockItemRenderer extends GeoItemRenderer<NeuropterisBlockItem> {
    public NeuropterisBlockItemRenderer() {
        super(new NeuropterisBlockItemModel());
    }
}
