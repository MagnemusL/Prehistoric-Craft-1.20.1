package net.smazeee.prehistoriccraft.compat;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.smazeee.prehistoriccraft.PrehistoricCraft;
import net.smazeee.prehistoriccraft.block.ModBlocks;
import net.smazeee.prehistoriccraft.recipe.AcidShowerRecipe;
import net.smazeee.prehistoriccraft.recipe.ExtractionMachineRecipe;

public class ExtractionMachineRecipeCategory implements IRecipeCategory<ExtractionMachineRecipe> {
    public static final ResourceLocation UID = new ResourceLocation(PrehistoricCraft.MODID, "extraction");
    public static final ResourceLocation GUI_TEXTURE = new ResourceLocation(PrehistoricCraft.MODID, "textures/gui/extraction_machine_gui.png");

    private final IDrawable background;
    private final IDrawable icon;

    public static final RecipeType<ExtractionMachineRecipe> EXTRACTION_TYPE = new RecipeType<>(UID, ExtractionMachineRecipe.class);

    public ExtractionMachineRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(GUI_TEXTURE, 0, 0, 100 /*175*/, 100 /*206*/);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.EXTRACTION_MACHINE.get()));
    }

    @Override
    public RecipeType<ExtractionMachineRecipe> getRecipeType() {
        return EXTRACTION_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("title.prehistoriccraft.extraction_machine");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder iRecipeLayoutBuilder, ExtractionMachineRecipe extractionMachineRecipe, IFocusGroup iFocusGroup) {
        iRecipeLayoutBuilder.addSlot(RecipeIngredientRole.INPUT, 80, 18).addIngredients(extractionMachineRecipe.getIngredients().get(0));

        iRecipeLayoutBuilder.addSlot(RecipeIngredientRole.OUTPUT, 62, 65).addItemStack(extractionMachineRecipe.getResultItem(null));
    }
}
