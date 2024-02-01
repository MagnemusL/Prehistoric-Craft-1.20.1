package net.smazeee.prehistoriccraft.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.smazeee.prehistoriccraft.datagen.loot.ModBlockLootTables;
import net.smazeee.prehistoriccraft.datagen.loot.ModEntityLootTables;

import java.util.List;
import java.util.Set;

public class ModLootTableProvider {
    public static LootTableProvider create(PackOutput packOutput) {
        return new LootTableProvider(packOutput, Set.of(), List.of(new LootTableProvider
                .SubProviderEntry(ModBlockLootTables::new, LootContextParamSets.BLOCK)));
                //new LootTableProvider.SubProviderEntry(ModEntityLootTables::new, LootContextParamSets.ENTITY)));
    }
}
