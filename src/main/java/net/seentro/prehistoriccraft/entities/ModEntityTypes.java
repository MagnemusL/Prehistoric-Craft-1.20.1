package net.seentro.prehistoriccraft.entities;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.seentro.prehistoriccraft.PrehistoricCraft;
import net.seentro.prehistoriccraft.entities.land.styracosaurus.Styracosaurus;
import net.seentro.prehistoriccraft.entities.water.dayongaspis.DayongaspisEntity;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, PrehistoricCraft.MODID);

    public static final RegistryObject<EntityType<DayongaspisEntity>> DAYONGASPIS = ENTITY_TYPES.register("dayongaspis",
            () -> EntityType.Builder.of(DayongaspisEntity::new, MobCategory.WATER_AMBIENT).sized(0.15f, 0.1f).build(new ResourceLocation(PrehistoricCraft.MODID, "dayongaspis").toString()));

    public static final RegistryObject<EntityType<Styracosaurus>> STYRACOSAURUS = ENTITY_TYPES.register("styracosaurus",
            () -> EntityType.Builder.of(Styracosaurus::new, MobCategory.CREATURE).sized(2f, 2f).build(new ResourceLocation(PrehistoricCraft.MODID, "styracosaurus").toString()));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
