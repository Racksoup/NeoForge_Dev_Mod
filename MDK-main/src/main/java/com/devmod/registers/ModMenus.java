package com.devmod.registers;

import com.devmod.DevMod;
import com.devmod.menus.ModCraftingMenu;
import com.devmod.menus.ModDeathBoxMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModMenus {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(Registries.MENU, DevMod.MODID);

    public static final Supplier<MenuType<ModCraftingMenu>> MOD_CRAFTING_MENU = MENUS.register(
            "mod_crafting_menu",
            () -> new MenuType<>(ModCraftingMenu::new, FeatureFlags.DEFAULT_FLAGS)
    );
    public static final Supplier<MenuType<ModDeathBoxMenu>> MOD_DEATH_BOX_MENU = MENUS.register(
            "mod_death_box_menu",
            () -> new MenuType<>(ModDeathBoxMenu::new, FeatureFlags.DEFAULT_FLAGS)
    );
}
