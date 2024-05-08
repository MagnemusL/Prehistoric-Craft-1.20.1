package net.smazeee.prehistoriccraft.plants;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.smazeee.prehistoriccraft.PrehistoricCraft;
import net.smazeee.prehistoriccraft.block.ModBlocks;
import net.smazeee.prehistoriccraft.plants.entity.BelemnopterisBlockEntity;
import net.smazeee.prehistoriccraft.plants.entity.ClathropterisBlockEntity;
import net.smazeee.prehistoriccraft.plants.entity.FieldHorsetailBlockEntity;
import net.smazeee.prehistoriccraft.plants.entity.NeuropterisBlockEntity;
import net.smazeee.prehistoriccraft.plants.entity.PachypterisBlockEntity;

public class PlantBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, PrehistoricCraft.MODID);

    public static final RegistryObject<BlockEntityType<BelemnopterisBlockEntity>> BELEMNOPTERIS_BE = BLOCK_ENTITIES.register("belemnopteris_block_entity", () -> BlockEntityType.Builder.of(BelemnopterisBlockEntity::new, ModBlocks.BELEMNOPTERIS.get()).build(null));
    public static final RegistryObject<BlockEntityType<ClathropterisBlockEntity>> CLATHROPTERIS_BE = BLOCK_ENTITIES.register("clathropteris_block_entity", () -> BlockEntityType.Builder.of(ClathropterisBlockEntity::new, ModBlocks.CLATHROPTERIS.get()).build(null));
    public static final RegistryObject<BlockEntityType<PachypterisBlockEntity>> PACHYPTERIS_BE = BLOCK_ENTITIES.register("pachypteris_block_entity", () -> BlockEntityType.Builder.of(PachypterisBlockEntity::new, ModBlocks.PACHYPTERIS.get()).build(null));
    public static final RegistryObject<BlockEntityType<NeuropterisBlockEntity>> NEUROPTERIS_BE = BLOCK_ENTITIES.register("neuropteris_block_entity", () -> BlockEntityType.Builder.of(NeuropterisBlockEntity::new, ModBlocks.NEUROPTERIS.get()).build(null));
    public static final RegistryObject<BlockEntityType<FieldHorsetailBlockEntity>> FIELD_HORSETAIL_BE = BLOCK_ENTITIES.register("field_horsetail_block_entity", () -> BlockEntityType.Builder.of(FieldHorsetailBlockEntity::new, ModBlocks.FIELD_HORSETAIL.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
