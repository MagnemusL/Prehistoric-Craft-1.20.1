package net.smazeee.prehistoriccraft.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.smazeee.prehistoriccraft.PrehistoricCraft;
import net.smazeee.prehistoriccraft.item.ModItems;
import org.jetbrains.annotations.Nullable;

public class AcidShowerRecipe implements Recipe<SimpleContainer> {
    private final NonNullList<Ingredient> inputItems;
    private final NonNullList<Item> output;
    private final ResourceLocation id;

    public AcidShowerRecipe(NonNullList<Ingredient> inputItems, NonNullList<Item> outputItems, ResourceLocation id) {
        this.inputItems = inputItems;
        this.output = outputItems;
        this.id = id;
    }

    @Override
    public boolean matches(SimpleContainer container, Level level) {
        if(level.isClientSide()) {
            return false;
        }

        return inputItems.get(1).test(container.getItem(1));
    }

    @Override
    public ItemStack assemble(SimpleContainer container, RegistryAccess access) {
        return ItemStack.EMPTY;
    }

    public NonNullList<Item> assembler(SimpleContainer container, RegistryAccess access) {
        ItemStack a = new ItemStack(output.get(1)).copy();
        ItemStack b = new ItemStack(output.get(2)).copy();
        ItemStack c = new ItemStack(output.get(3)).copy();
        NonNullList<Item> copiedOutputs = NonNullList.of(a.getItem(), b.getItem(), c.getItem());
        return copiedOutputs;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess access) {
        return output.copy();
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return this.inputItems;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return null;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<AcidShowerRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
        public static final String ID = "acid_showering";
    }

    public static class Serializer implements RecipeSerializer<AcidShowerRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID =
                new ResourceLocation(PrehistoricCraft.MODID, "acid_showering");

        @Override
        public AcidShowerRecipe fromJson(ResourceLocation id, JsonObject json) {
            JsonArray output = GsonHelper.getAsJsonArray(json, "outputs");
            NonNullList<Item> outputs = NonNullList.withSize(3, Items.AIR);

            JsonArray ingredients = GsonHelper.getAsJsonArray(json, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(1, Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new AcidShowerRecipe(inputs, outputs, id);
        }

        @Override
        public @Nullable AcidShowerRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(buf));
            }

            NonNullList<Item> outputs = buf.readItem();
            return new AcidShowerRecipe(inputs, outputs, id);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, AcidShowerRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());

            for (Ingredient ing : recipe.getIngredients()) {
                ing.toNetwork(buf);
            }
            buf.writeItem(recipe.getResultItem(null));
        }
    }
}
