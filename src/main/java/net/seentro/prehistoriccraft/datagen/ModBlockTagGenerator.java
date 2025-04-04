package net.seentro.prehistoriccraft.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.seentro.prehistoriccraft.PrehistoricCraft;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                                @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, PrehistoricCraft.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider p_256380_) {
        //this.tag(ModTags.Blocks.THING)
        //        .add(ModBlocks.THING.get()).addTag(Tags.Blocks.ORES);

        //this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.THING.get());
        //this.tag(BlockTags.NEEDS_STONE_TOOL).add(ModBlocks.THING.get());
    }

    @Override
    public String getName() {
        return "Block Tags";
    }
}
