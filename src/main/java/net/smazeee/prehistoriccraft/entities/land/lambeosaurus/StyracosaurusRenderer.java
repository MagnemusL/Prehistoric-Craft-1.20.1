package net.smazeee.prehistoriccraft.entities.land.lambeosaurus;

import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.smazeee.prehistoriccraft.PrehistoricCraft;
import net.smazeee.prehistoriccraft.util.Gender;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

import java.util.Map;

public class StyracosaurusRenderer extends GeoEntityRenderer<StyracosaurusEntity> {
    private static final Map<Gender, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(Gender.class), map -> {
                map.put(Gender.MALE,
                        new ResourceLocation(PrehistoricCraft.MODID, "textures/entity/styracosaurus/styracosaurus_male_adult.png"));
                map.put(Gender.FEMALE,
                        new ResourceLocation(PrehistoricCraft.MODID, "textures/entity/styracosaurus/styracosaurus_female_adult.png"));
            });

    public StyracosaurusRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new StyracosaurusModel());
    }

    @Override
    public ResourceLocation getTextureLocation(StyracosaurusEntity animatable) {
        return LOCATION_BY_VARIANT.get(animatable.gender);
    }
}
