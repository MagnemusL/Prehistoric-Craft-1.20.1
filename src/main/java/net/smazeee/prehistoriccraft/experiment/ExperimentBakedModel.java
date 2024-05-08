package net.smazeee.prehistoriccraft.experiment;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.ChunkRenderTypeSet;
import net.minecraftforge.client.model.IDynamicBakedModel;
import net.minecraftforge.client.model.data.ModelData;
import net.minecraftforge.client.model.geometry.IGeometryBakingContext;
import net.smazeee.prehistoriccraft.PrehistoricCraft;
import net.smazeee.prehistoriccraft.block.bakedmodel.CablePatterns;
import net.smazeee.prehistoriccraft.block.bakedmodel.CablePatterns.Pattern;
import net.smazeee.prehistoriccraft.block.bakedmodel.CablePatterns.QuadSetting;
import net.smazeee.prehistoriccraft.block.custom.cables.CableBlock;
import net.smazeee.prehistoriccraft.util.ConnectorType;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static net.smazeee.prehistoriccraft.block.bakedmodel.CablePatterns.SpriteIdx.*;
import static net.smazeee.prehistoriccraft.util.BakedModelHelper.quad;
import static net.smazeee.prehistoriccraft.util.BakedModelHelper.v;
import static net.smazeee.prehistoriccraft.util.ConnectorType.*;

public class ExperimentBakedModel implements IDynamicBakedModel {

    private final IGeometryBakingContext context;

    private TextureAtlasSprite spriteNormal;
    private TextureAtlasSprite spriteEnd;

    public ExperimentBakedModel(IGeometryBakingContext context) {
        this.context = context;
    }

    private void initTextures() {
        spriteNormal = getTexture("block/experiment");
        spriteEnd = getTexture("block/experiment_end");
    }

    public TextureAtlasSprite getSpriteNormal(CablePatterns.SpriteIdx idx) {
        return switch (idx) {
            case SPRITE_NONE -> null;
            case SPRITE_END -> spriteEnd;
            case SPRITE_STRAIGHT -> spriteNormal;
            case SPRITE_CORNER -> null;
            case SPRITE_THREE -> null;
            case SPRITE_CROSS -> null;
        };
    }

    // All textures are baked on a big texture atlas. This function gets the texture from that atlas
    private TextureAtlasSprite getTexture(String path) {
        return Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(new ResourceLocation(PrehistoricCraft.MODID, path));
    }

    @Override
    public boolean usesBlockLight() {
        return false;
    }

    @Override
    @NotNull
    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @NotNull RandomSource rand, @NotNull ModelData extraData, @Nullable RenderType layer) {
        initTextures();
        List<BakedQuad> quads = new ArrayList<>();
        TextureAtlasSprite sprite = spriteNormal;
        Function<CablePatterns.SpriteIdx, TextureAtlasSprite> spriteGetter = this::getSpriteNormal;


        double o = .3;

        quads.add(quad(v(1 - o, 1, o), v(1 - o, 1, 1 - o), v(1 - o, 1 - o, 1 - o), v(1 - o, 1 - o, o), sprite));
        quads.add(quad(v(o, 1, 1 - o), v(o, 1, o), v(o, 1 - o, o), v(o, 1 - o, 1 - o), sprite));
        quads.add(quad(v(o, 1, o), v(1 - o, 1, o), v(1 - o, 1 - o, o), v(o, 1 - o, o), sprite));
        quads.add(quad(v(o, 1 - o, 1 - o), v(1 - o, 1 - o, 1 - o), v(1 - o, 1, 1 - o), v(o, 1, 1 - o), sprite));
        return quads;
    }

    @Override
    public boolean useAmbientOcclusion() {
        return true;
    }

    @Override
    public boolean isGui3d() {
        return false;
    }

    @Override
    public boolean isCustomRenderer() {
        return false;
    }

    // Because we can potentially mimic other blocks we need to render on all render types
    @Override
    @Nonnull
    public ChunkRenderTypeSet getRenderTypes(@NotNull BlockState state, @NotNull RandomSource rand, @NotNull ModelData data) {
        return ChunkRenderTypeSet.all();
    }

    @Nonnull
    @Override
    public TextureAtlasSprite getParticleIcon() {
        return spriteNormal;
    }

    // To let our cable/facade render correctly as an item (both in inventory and on the ground) we
    // get the correct transforms from the context
    @Nonnull
    @Override
    public ItemTransforms getTransforms() {
        return context.getTransforms();
    }

    @Nonnull
    @Override
    public ItemOverrides getOverrides() {
        return ItemOverrides.EMPTY;
    }

}
