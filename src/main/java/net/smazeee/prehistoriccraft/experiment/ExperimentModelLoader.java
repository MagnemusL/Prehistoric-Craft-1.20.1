package net.smazeee.prehistoriccraft.experiment;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBaker;
import net.minecraft.client.resources.model.ModelState;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.ModelEvent;
import net.minecraftforge.client.model.geometry.IGeometryBakingContext;
import net.minecraftforge.client.model.geometry.IGeometryLoader;
import net.minecraftforge.client.model.geometry.IUnbakedGeometry;
import net.smazeee.prehistoriccraft.PrehistoricCraft;
import net.smazeee.prehistoriccraft.block.bakedmodel.CableBakedModel;

import java.util.function.Function;

public class ExperimentModelLoader implements IGeometryLoader<ExperimentModelLoader.ExperimentModelGeometry> {

    public static final ResourceLocation GENERATOR_LOADER = new ResourceLocation(PrehistoricCraft.MODID, "experimentloader");

    public static void register(ModelEvent.RegisterGeometryLoaders event) {
        event.register("experimentloader", new ExperimentModelLoader());
    }


    @Override
    public ExperimentModelGeometry read(JsonObject jsonObject, JsonDeserializationContext deserializationContext) throws JsonParseException {
        return new ExperimentModelGeometry();
    }

    public static class ExperimentModelGeometry implements IUnbakedGeometry<ExperimentModelGeometry> {

        public ExperimentModelGeometry() {
        }

        @Override
        public BakedModel bake(IGeometryBakingContext context, ModelBaker baker, Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelState, ItemOverrides overrides, ResourceLocation modelLocation) {
            return new ExperimentBakedModel(context);
        }
    }
}
