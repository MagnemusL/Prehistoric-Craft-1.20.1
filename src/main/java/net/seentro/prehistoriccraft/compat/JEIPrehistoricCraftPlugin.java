package net.seentro.prehistoriccraft.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;
import net.seentro.prehistoriccraft.PrehistoricCraft;
import net.seentro.prehistoriccraft.recipe.AcidShowerRecipe;
import net.seentro.prehistoriccraft.screen.AcidShowerScreen;

import java.util.List;

@JeiPlugin
public class JEIPrehistoricCraftPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(PrehistoricCraft.MODID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        /*ACID SHOWER*/
        registration.addRecipeCategories(new AcidShowerRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();
        /*ACID SHOWER*/
        List<AcidShowerRecipe> sprayingRecipes = recipeManager.getAllRecipesFor(AcidShowerRecipe.Type.INSTANCE);
        registration.addRecipes(AcidShowerRecipeCategory.ACID_SHOWERING_TYPE, sprayingRecipes);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        /*ACID SHOWER*/
        registration.addRecipeClickArea(AcidShowerScreen.class, 64, 61, 46, 12,
                AcidShowerRecipeCategory.ACID_SHOWERING_TYPE);
    }
}
