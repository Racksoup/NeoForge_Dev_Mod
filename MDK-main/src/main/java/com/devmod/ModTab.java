package com.devmod;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModTab {
    // Creative Tab
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, DevMod.MODID);
    public static final Supplier<CreativeModeTab> DEV_MOD_TAB = CREATIVE_MODE_TABS.register("dev_mod_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable(DevMod.MODID + "_tab"))
            .icon(() -> new ItemStack(ModItems.MY_BLOCK_ITEM.get()))
            .displayItems((params, output) -> {
                output.accept(ModItems.MY_BLOCK_ITEM.get());
                output.accept(ModItems.MY_CRAFTING_TABLE_ITEM.get());
                output.accept(ModItems.STABLE_WATER_ITEM.get());
                output.accept(ModItems.URANIUM_ITEM.get());
                output.accept(ModItems.TURTLE_SHELL_ITEM.get());
                output.accept(ModItems.NEO_BATTLE_AXE.get());
                output.accept(ModItems.URSA_HEART.get());
                output.accept(ModItems.TEMPERED_STAFF.get());
            })
            .build()
    );
}
