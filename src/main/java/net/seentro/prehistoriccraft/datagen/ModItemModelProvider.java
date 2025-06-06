package net.seentro.prehistoriccraft.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.seentro.prehistoriccraft.PrehistoricCraft;
import net.seentro.prehistoriccraft.item.ModItems;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, PrehistoricCraft.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.CAMBRIAN_FOSSIL);
        simpleItem(ModItems.PRECAMBRIAN_FOSSIL);
        simpleItem(ModItems.CARBONIFEROUS_FOSSIL);
        simpleItem(ModItems.CRETACEOUS_FOSSIL);
        simpleItem(ModItems.DEVONIAN_FOSSIL);
        simpleItem(ModItems.JURASSIC_FOSSIL);
        simpleItem(ModItems.NEOGENE_FOSSIL);
        simpleItem(ModItems.ORDOVICIAN_FOSSIL);
        simpleItem(ModItems.PALEOGENE_FOSSIL);
        simpleItem(ModItems.PERMIAN_FOSSIL);
        simpleItem(ModItems.SILURIAN_FOSSIL);
        simpleItem(ModItems.TRIASSIC_FOSSIL);
        simpleItem(ModItems.AMBER);

        //simpleItem(ModItems.CLEANED_FOSSIL_STONE);
        //simpleItem(ModItems.CLEANED_FOSSIL_DEEPSLATE);

        simpleItem(ModItems.SULFUR);
        simpleItem(ModItems.SULFURIC_ACID_BUCKET);

        simpleItem(ModItems.EGGY);
        simpleItem(ModItems.CRACKED_EGGY);

        simpleItem(ModItems.EMPTY_FLUID_CONTAINER);

        simpleItem(ModItems.EMPTY_SATCHET);
        simpleItem(ModItems.BELEMNOPTERIS_SEEDS_SATCHET);
        simpleItem(ModItems.CLATHROPTERIS_SPORES_SATCHET);
        simpleItem(ModItems.FIELD_HORSETAIL_SPORES_SATCHET);
        simpleItem(ModItems.NEUROPTERIS_SEEDS_SATCHET);
        simpleItem(ModItems.PACHYPTERIS_SEEDS_SATCHET);

        //simpleItem(ModItems.ANCIENT_PLANT_TISSUE);
        //simpleItem(ModItems.ANCIENT_ANIMAL_TISSUE);
        //simpleItem(ModItems.ANCIENT_BLOOD);
        //simpleItem(ModItems.BLOOD_PHIAL);
        //simpleItem(ModItems.BATTERY);
        //simpleItem(ModItems.BLICE);
        //simpleItem(ModItems.BLICE_BOTTLE);
        //simpleItem(ModItems.PALEOENCYCLOPEDIA);
        //simpleItem(ModItems.PALEOKEEPERS_NOTEBOOK);

        withExistingParent(ModItems.DAYONGASPIS_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.STYRACOSAURUS_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        simpleItem(ModItems.DAYONGASPIS_BUCKET);
    }

    public ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(PrehistoricCraft.MODID, "item/" + item.getId().getPath()));
    }
}
