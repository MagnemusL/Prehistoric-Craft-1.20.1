package net.seentro.prehistoriccraft.compat;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.seentro.prehistoriccraft.PrehistoricCraft;
import net.seentro.prehistoriccraft.block.ModBlocks;
import net.seentro.prehistoriccraft.recipe.AcidShowerRecipe;
import org.jetbrains.annotations.Nullable;

public class AcidShowerRecipeCategory implements IRecipeCategory<AcidShowerRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(PrehistoricCraft.MODID, "acid_showering");
    public static final ResourceLocation TEXTURE = new ResourceLocation(PrehistoricCraft.MODID, "textures/gui/acid_shower_gui.png");

    private final IDrawable background;
    private final IDrawable icon;

    public static final RecipeType<AcidShowerRecipe> ACID_SHOWERING_TYPE = new RecipeType<>(UID, AcidShowerRecipe.class);

    public AcidShowerRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 175, 206);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.ACID_SHOWER.get()));
    }

    @Override
    public RecipeType<AcidShowerRecipe> getRecipeType() {
        return ACID_SHOWERING_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("title.prehistoriccraft.acid_shower");
    }

    //Deprecated but no replacement
    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void draw(AcidShowerRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        IRecipeCategory.super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder iRecipeLayoutBuilder, AcidShowerRecipe acidShowerRecipe, IFocusGroup iFocusGroup) {
        iRecipeLayoutBuilder.addSlot(RecipeIngredientRole.INPUT, 79, 44).addIngredients(acidShowerRecipe.getIngredients().get(0));

        iRecipeLayoutBuilder.addSlot(RecipeIngredientRole.OUTPUT, 61, 75).addItemStack(acidShowerRecipe.getResultItem(null));
    }
}
