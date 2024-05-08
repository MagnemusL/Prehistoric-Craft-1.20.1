package net.smazeee.prehistoriccraft.util;

import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProviderType;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.smazeee.prehistoriccraft.PrehistoricCraft;

public class ModBlockStateProperties {
    public static final DeferredRegister<BlockStateProviderType<?>> BLOCK_STATE_PROPERTIES = DeferredRegister.create(ForgeRegistries.BLOCK_STATE_PROVIDER_TYPES, PrehistoricCraft.MODID);
}
