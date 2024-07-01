package com.devmod;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModMenus {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(Registries.MENU, DevMod.MODID);

    public static final Supplier<MenuType<ModCraftingMenu>> MOD_CRAFTING_MENU = MENUS.register(
            "mod_crafting_menu",
            () -> new MenuType<ModCraftingMenu>(ModCraftingMenu::new, FeatureFlags.DEFAULT_FLAGS)
    );
}
