package net.smazeee.prehistoriccraft.block;

import net.minecraft.client.resources.model.Material;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.smazeee.prehistoriccraft.PrehistoricCraft;
import net.smazeee.prehistoriccraft.block.custom.AcidShowerBlock;
import net.smazeee.prehistoriccraft.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, PrehistoricCraft.MODID);

    public static final RegistryObject<Block> CAMBRIAN_FOSSILIFEROUS_STONE = registerBlock("CAMBRIAN_FOSSILIFEROUS_STONE".toLowerCase(), () -> new DropExperienceBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
    public static final RegistryObject<Block> PRECAMBRIAN_FOSSILIFEROUS_STONE = registerBlock("PRECAMBRIAN_FOSSILIFEROUS_STONE".toLowerCase(), () -> new DropExperienceBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
    public static final RegistryObject<Block> CARBONIFEROUS_FOSSILIFEROUS_STONE = registerBlock("CARBONIFEROUS_FOSSILIFEROUS_STONE".toLowerCase(), () -> new DropExperienceBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
    public static final RegistryObject<Block> CRETACEOUS_FOSSILIFEROUS_STONE = registerBlock("CRETACEOUS_FOSSILIFEROUS_STONE".toLowerCase(), () -> new DropExperienceBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
    public static final RegistryObject<Block> DEVONIAN_FOSSILIFEROUS_STONE = registerBlock("DEVONIAN_FOSSILIFEROUS_STONE".toLowerCase(), () -> new DropExperienceBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
    public static final RegistryObject<Block> JURASSIC_FOSSILIFEROUS_STONE = registerBlock("JURASSIC_FOSSILIFEROUS_STONE".toLowerCase(), () -> new DropExperienceBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
    public static final RegistryObject<Block> ORDOVICIAN_FOSSILIFEROUS_STONE = registerBlock("ORDOVICIAN_FOSSILIFEROUS_STONE".toLowerCase(), () -> new DropExperienceBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
    public static final RegistryObject<Block> PERMIAN_FOSSILIFEROUS_STONE = registerBlock("PERMIAN_FOSSILIFEROUS_STONE".toLowerCase(), () -> new DropExperienceBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
    public static final RegistryObject<Block> SILURIAN_FOSSILIFEROUS_STONE = registerBlock("SILURIAN_FOSSILIFEROUS_STONE".toLowerCase(), () -> new DropExperienceBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
    public static final RegistryObject<Block> TRIASSIC_FOSSILIFEROUS_STONE = registerBlock("TRIASSIC_FOSSILIFEROUS_STONE".toLowerCase(), () -> new DropExperienceBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
    public static final RegistryObject<Block> AMBER_ORE = registerBlock("AMBER_ORE".toLowerCase(), () -> new DropExperienceBlock(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
    public static final RegistryObject<Block> ACID_SHOWER = registerBlock("acid_shower", () -> new AcidShowerBlock(BlockBehaviour.Properties.of().noLootTable().requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
    public static final RegistryObject<Block> SULFUR_ORE = registerBlock("sulfur_ore", () -> new Block(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(3.0F, 3.0F)));
    public static final RegistryObject<Block> DEEPSLATE_SULFUR_ORE = registerBlock("deepslate_sulfur_ore", () -> new Block(BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(3.0F, 3.0F)));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
