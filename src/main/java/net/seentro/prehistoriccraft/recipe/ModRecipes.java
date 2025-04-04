package net.seentro.prehistoriccraft.recipe;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.seentro.prehistoriccraft.PrehistoricCraft;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, PrehistoricCraft.MODID);

    public static final RegistryObject<RecipeSerializer<AcidShowerRecipe>> ACID_SHOWER_SERIALIZER = SERIALIZERS.register("acid_showering", () -> AcidShowerRecipe.Serializer.INSTANCE);
    public static final RegistryObject<RecipeSerializer<ExtractionMachineRecipe>> EXTRACTION_MACHINE_SERIALIZER = SERIALIZERS.register("extracting", () -> ExtractionMachineRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
