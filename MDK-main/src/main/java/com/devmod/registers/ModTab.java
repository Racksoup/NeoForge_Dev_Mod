package com.devmod.registers;

import com.devmod.DevMod;
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

                // Crafting Reagents
                output.accept(ModItems.STABLE_WATER_ITEM.get());
                output.accept(ModItems.URANIUM_ITEM.get());
                output.accept(ModItems.TURTLE_SHELL_ITEM.get());

                // Weapons
                output.accept(ModItems.NEO_BATTLE_AXE.get());
                output.accept(ModItems.URSA_HEART.get());
                output.accept(ModItems.TEMPERED_STAFF.get());

                // Spells
                output.accept(ModItems.SPELL_CHARGE.get());
                output.accept(ModItems.SPELL_LEASH.get());
                output.accept(ModItems.SPELL_BLINK.get());

                // Armor
                output.accept(ModItems.SHAMAN_HELMET.get());
                output.accept(ModItems.SHAMAN_CHESTPLATE.get());
                output.accept(ModItems.SHAMAN_LEGGINGS.get());
                output.accept(ModItems.SHAMAN_BOOTS.get());
                output.accept(ModItems.WARRIOR_HELMET.get());
                output.accept(ModItems.WARRIOR_CHESTPLATE.get());
                output.accept(ModItems.WARRIOR_LEGGINGS.get());
                output.accept(ModItems.WARRIOR_BOOTS.get());
                output.accept(ModItems.MAGE_HELMET.get());
                output.accept(ModItems.MAGE_CHESTPLATE.get());
                output.accept(ModItems.MAGE_LEGGINGS.get());
                output.accept(ModItems.MAGE_BOOTS.get());
            })
            .build()
    );
}
