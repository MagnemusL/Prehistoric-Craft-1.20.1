package net.smazeee.prehistoriccraft.entities;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.smazeee.prehistoriccraft.PrehistoricCraft;
import net.smazeee.prehistoriccraft.entities.water.dayongaspis.Dayongaspis;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, PrehistoricCraft.MODID);

    public static final RegistryObject<EntityType<Dayongaspis>> DAYONGASPIS = ENTITY_TYPES.register("dayongaspis",
            () -> EntityType.Builder.of(Dayongaspis::new, MobCategory.WATER_AMBIENT).sized(0.15f, 0.1f).build(new ResourceLocation(PrehistoricCraft.MODID, "tiger").toString()));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
