package net.smazeee.prehistoriccraft.recipe;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.smazeee.prehistoriccraft.PrehistoricCraft;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, PrehistoricCraft.MODID);

    //11:22

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
