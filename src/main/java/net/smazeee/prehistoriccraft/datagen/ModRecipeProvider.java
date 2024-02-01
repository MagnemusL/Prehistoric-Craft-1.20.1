package net.smazeee.prehistoriccraft.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.smazeee.prehistoriccraft.PrehistoricCraft;
import net.smazeee.prehistoriccraft.item.ModItems;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private static final List<ItemLike> SMELTABLES = List.of();

    public ModRecipeProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        /*ShapedRecipeBuilder.shaped((RecipeCategory.MISC, ModBlocks.THING.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.THING.get())
                .unlockedBy("has_thing", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.THING.get()).build()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.THING.get(), 9)
                .requires(ModBlocks.THING.get())
                .unlockedBy("has_thing", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(ModItems.THING.get()).build()))
                .save(consumer);
         */
        //smeltOres(consumer, SMELTABLES, RecipeCategory.MISC, ModItems.THING.get(), 0.25f, 200, 100, "thing");
    }

    private void smeltOres(Consumer<FinishedRecipe> consumer, List<ItemLike> itemLikes, RecipeCategory category, ItemLike result, float experience, int cookingTimeFurnace, int cookingTimeBlasting, String group) {
        oreSmelting(consumer, itemLikes, category, result, experience, cookingTimeFurnace, group);
        oreBlasting(consumer, itemLikes, category, result, experience, cookingTimeBlasting, group);
    }

    protected static void oreSmelting(Consumer<FinishedRecipe> recipeConsumer, List<ItemLike> itemLikes, RecipeCategory category, ItemLike result,
                                      float experience, int cookingTime, String group) {
        oreCooking(recipeConsumer, RecipeSerializer.SMELTING_RECIPE, itemLikes, category, result,
                experience, cookingTime, group, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> recipeConsumer, List<ItemLike> itemLikes, RecipeCategory category, ItemLike result,
                                      float experience, int cookingTime, String group) {
        oreCooking(recipeConsumer, RecipeSerializer.BLASTING_RECIPE, itemLikes, category, result,
                experience, cookingTime, group, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> recipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> cookingSerializer,
                                     List<ItemLike> ingredients, RecipeCategory catogory, ItemLike result, float experience, int cookingTime, String group, String recipeName) {
        for(ItemLike itemlike : ingredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), catogory, result, experience, cookingTime,
                    cookingSerializer).group(group).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeConsumer, PrehistoricCraft.MODID + ":" + getItemName(result) + recipeName + "_" + getItemName(itemlike));
        }

    }
}
