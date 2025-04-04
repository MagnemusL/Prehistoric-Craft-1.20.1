package net.seentro.prehistoriccraft.block.entity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.seentro.prehistoriccraft.PrehistoricCraft;
import net.seentro.prehistoriccraft.block.ModBlocks;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, PrehistoricCraft.MODID);

    public static final RegistryObject<BlockEntityType<AcidShowerBlockEntity>> ACID_SHOWER_BE = BLOCK_ENTITIES.register("acid_shower_block_entity", () -> BlockEntityType.Builder.of(AcidShowerBlockEntity::new, ModBlocks.ACID_SHOWER.get()).build(null));
    public static final RegistryObject<BlockEntityType<ExtractionMachineBlockEntity>> EXTRACTION_MACHINE_BE = BLOCK_ENTITIES.register("extraction_machine_block_entity", () -> BlockEntityType.Builder.of(ExtractionMachineBlockEntity::new, ModBlocks.EXTRACTION_MACHINE.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
