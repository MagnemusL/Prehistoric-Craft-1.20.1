package net.smazeee.prehistoriccraft.block.entity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.smazeee.prehistoriccraft.PrehistoricCraft;
import net.smazeee.prehistoriccraft.block.ModBlocks;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, PrehistoricCraft.MODID);

    public static final RegistryObject<BlockEntityType<AcidShowerBlockEntity>> ACID_SHOWER_BE = BLOCK_ENTITIES.register("acid_shower_block_entity", () -> BlockEntityType.Builder.of(AcidShowerBlockEntity::new, ModBlocks.ACID_SHOWER.get()).build(null));
    public static final RegistryObject<BlockEntityType<AcidShowerBlockEntity>> MB_TEST_BE = BLOCK_ENTITIES.register("mb_test_block_entity", () -> BlockEntityType.Builder.of(AcidShowerBlockEntity::new, ModBlocks.ACID_SHOWER.get()).build(null));
    public static final RegistryObject<BlockEntityType<CableBlockEntity>> CABLE_BE = BLOCK_ENTITIES.register("cable_block_entity", () -> BlockEntityType.Builder.of(CableBlockEntity::new, ModBlocks.CABLE.get()).build(null));
    public static final RegistryObject<BlockEntityType<FacadeBlockEntity>> FACADE_BE = BLOCK_ENTITIES.register("facade_block_entity", () -> BlockEntityType.Builder.of(FacadeBlockEntity::new, ModBlocks.FACADE.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
