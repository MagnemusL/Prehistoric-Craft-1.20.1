package net.seentro.prehistoriccraft.screen;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.seentro.prehistoriccraft.PrehistoricCraft;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, PrehistoricCraft.MODID);

    public static final RegistryObject<MenuType<AcidShowerMenu>> ACID_SHOWER_MENU = registerMenu(AcidShowerMenu::new, "acid_shower_menu");
    public static final RegistryObject<MenuType<ExtractionMachineMenu>> EXTRACTION_MACHINE_MENU = registerMenu(ExtractionMachineMenu::new, "extraction_machine_menu");

    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenu(IContainerFactory<T> factory, String name) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
