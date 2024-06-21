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
    public static final Supplier<CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("dev_mod_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable(DevMod.MODID + "_tab"))
            .icon(() -> new ItemStack(ModItems.MY_BLOCK_ITEM.get()))
            .displayItems((params, output) -> {
                output.accept(ModItems.MY_BLOCK_ITEM.get());
            })
            .build()
    );
}
