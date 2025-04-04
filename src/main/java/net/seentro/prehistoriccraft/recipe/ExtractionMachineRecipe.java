package net.seentro.prehistoriccraft.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.seentro.prehistoriccraft.PrehistoricCraft;
import org.jetbrains.annotations.Nullable;

public class ExtractionMachineRecipe implements Recipe<SimpleContainer> {
    private final NonNullList<Ingredient> inputItems;
    private final ItemStack output;
    private final ResourceLocation id;
    private final int processingTime;
    private final int energyRequired;

    public ExtractionMachineRecipe(NonNullList<Ingredient> inputItems, ItemStack output, ResourceLocation id, int processingTime, int energyRequired) {
        this.inputItems = inputItems;
        this.output = output;
        this.id = id;
        this.processingTime = processingTime;
        this.energyRequired = energyRequired;
    }

    @Override
    public boolean matches(SimpleContainer container, Level level) {
        if(level.isClientSide()) {
            return false;
        }

        return inputItems.get(0).test(container.getItem(1));
    }

    @Override
    public ItemStack assemble(SimpleContainer container, RegistryAccess access) {
        return output.copy();
        //return inputItems.get(1).test(container.getItem(1)) ? getOutput().copy() : ItemStack.EMPTY;
    }

    public ItemStack getOutput()
    {
        return output;
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

    public int getProcessingTime() {
        return processingTime;
    }

    public int getEnergyRequired() {
        return energyRequired;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<ExtractionMachineRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
        public static final String ID = "extracting";
    }

    public static class Serializer implements RecipeSerializer<ExtractionMachineRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID =
                new ResourceLocation(PrehistoricCraft.MODID, "extracting");

        @Override
        public ExtractionMachineRecipe fromJson(ResourceLocation id, JsonObject json) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "output"));
            int processingTime = GsonHelper.getAsInt(json, "processingTime");
            int energyRequired = GsonHelper.getAsInt(json, "energy");

            JsonArray ingredients = GsonHelper.getAsJsonArray(json, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(1, Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new ExtractionMachineRecipe(inputs, output, id, processingTime, energyRequired);
        }

        @Override
        public @Nullable ExtractionMachineRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);
            int processingTime = buf.readInt();
            int energyRequired = buf.readInt();

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(buf));
            }

            ItemStack output = buf.readItem();
            return new ExtractionMachineRecipe(inputs, output, id, processingTime, energyRequired);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, ExtractionMachineRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());

            for (Ingredient ing : recipe.getIngredients()) {
                ing.toNetwork(buf);
            }
            buf.writeItemStack(recipe.getResultItem(null), false);
            buf.writeInt(recipe.getProcessingTime());
            buf.writeInt(recipe.getEnergyRequired());
        }
    }
}
