package com.devmod.utils.spells;


import com.devmod.DevMod;
import com.devmod.data.SpellData;
import com.devmod.items.ModProjectileWeapon;
import com.devmod.items.ModSpell;
import com.devmod.items.ModSwordItem;
import com.devmod.utils.spells.warrior.Charge;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.event.InputEvent;


public class KeyBindings {

    public static void checkForSpellKeybindings(InputEvent.Key event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;
        // look through hotbar keybinds
        for (int i = 0; i < 9; i++) {
            // if this is the hotbarkey the player pressed
            if (mc.options.keyHotbarSlots[i].getKey().getValue() == event.getKey()) {
                ItemStack spellStack = mc.player.getInventory().getItem(i);
                // if it's a modspell
                if (isSpell(spellStack)) {
                    // find our weapon on hotbar
                    for (int j = 0; j < 9; j++) {
                        ItemStack weaponStack = mc.player.getInventory().getItem(j);
                        if (isWeapon(weaponStack)) {
                            // switch active hotbar slot to weapon
                            mc.player.getInventory().selected = j;
                            // perform action
                            performSpell(spellStack);
                            return;
                        }
                    }
                    return;
                }
            }
        }
    }

    public static boolean isSpell(ItemStack itemStack) {
        return itemStack.getItem() instanceof ModSpell;
    }

    public static boolean isWeapon(ItemStack itemStack) {
        return itemStack.getItem() instanceof ModProjectileWeapon || itemStack.getItem() instanceof ModSwordItem;
    }

    public static void performSpell(ItemStack spellStack) {
        if (SpellData.spellUsable()) {
            switch (spellStack.getItem().getName(spellStack).getString()) {
                case "item.dev_mod.spell_charge":
                    Charge.onCharge();
                    break;
                case "item.dev_mod.spell_leash":
                    // handle spell_leash
                    break;
                case "item.dev_mod.spell_blink":
                    // handle spell_blink
                    break;
                // add cases for other spells
                default:
                    // handle default case
                    break;
            }
        }
    }
}
