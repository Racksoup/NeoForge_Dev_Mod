package com.devmod.utils;

import com.devmod.DevMod;
import com.devmod.data.RoleData;
import com.devmod.items.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.Arrays;

public class HotbarHasWrongClassItems {
    private static ItemStack[] previousHotbar = new ItemStack[9];

    public static void checkThenSet(PlayerTickEvent.Post event) {
        // get current hotbar
        ItemStack[] currentHotbar = new ItemStack[9];
        for (int i = 0; i < 9; i++) {
           currentHotbar[i] = event.getEntity().getInventory().getItem(i);
        }
        // check against previous
        for (int i = 0; i < 9; i++) {
            if (previousHotbar[i] == null) {
                previousHotbar[i] = currentHotbar[i].copy();
            } else if (!ItemStack.matches(currentHotbar[i], previousHotbar[i])) {
                onHotbarSlotChange(currentHotbar[i], event.getEntity());
                previousHotbar[i] = currentHotbar[i].copy();
            }
        }
    }

    private static void onHotbarSlotChange(ItemStack stack, Player player) {
        Item item = stack.getItem();
        if (item instanceof ModSpell || item instanceof ModSwordItem || item instanceof ModProjectileWeapon || item instanceof ModArmorItem) {
            DevMod.LOGGER.info("mod item!: {}", item);

            boolean isRightClass = false;
            if (RoleData.getCurrentClass().equals("warrior")) {
                for (int i = 0; i < RoleData.Warrior.Items.length; i++) {
                    if (RoleData.Warrior.Items[i].equals(item)) {
                        isRightClass = true;
                    }
                }
            }
            if (RoleData.getCurrentClass().equals("mage")) {
                for (int i = 0; i < RoleData.Mage.Items.length; i++) {
                    if (RoleData.Mage.Items[i].equals(item)) {
                        isRightClass = true;
                    }
                }
            }
            if (RoleData.getCurrentClass().equals("shaman")) {
                for (int i = 0; i < RoleData.Shaman.Items.length; i++) {
                    if (RoleData.Shaman.Items[i].equals(item)) {
                        isRightClass = true;
                    }
                }
            }

            DevMod.LOGGER.info("is right class: {}", isRightClass);
            if (!isRightClass) {
                // Remove item from hotbar
                player.getInventory().removeItem(stack);

                // Add item to inventory
                for (int i = 9; i < 35; i++) {
                    if (player.getInventory().getItem(i) == ItemStack.EMPTY) {
                        player.getInventory().setItem(i, stack);
                        break;
                    }
                }
            }
        }
    }
}
