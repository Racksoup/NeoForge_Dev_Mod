package com.devmod.utils.spells;


import com.devmod.data.SpellData;
import com.devmod.items.ModProjectileWeapon;
import com.devmod.items.ModSpell;
import com.devmod.items.ModSwordItem;
import com.devmod.utils.spells.mage.Blink;
import com.devmod.utils.spells.shaman.Leash;
import com.devmod.utils.spells.warrior.Charge;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.event.InputEvent;


public class KeyBindings {

    public static boolean checkIfSpellKeybindClicked(InputEvent.Key event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return false;
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
                            // switch to last item held
                            mc.player.getInventory().selected = SpellData.slot;

                            // perform action
                            if (!SpellData.isGCD()) {
                                performSpell(spellStack, mc);
                            }
                            return true;
                        }
                    }
                    // weapon not equip
                    return false;
                }
                // not our spell
                return false;
            }
        }
        return false;
    }

    public static boolean isSpell(ItemStack itemStack) {
        return itemStack.getItem() instanceof ModSpell;
    }

    public static boolean isWeapon(ItemStack itemStack) {
        return itemStack.getItem() instanceof ModProjectileWeapon || itemStack.getItem() instanceof ModSwordItem;
    }

    public static void performSpell(ItemStack spellStack, Minecraft mc) {
        switch (spellStack.getItem().getName(spellStack).getString()) {
            case "item.dev_mod.spell_charge":
                Charge.onCharge();
                break;
            case "item.dev_mod.spell_leash":
                Leash.onLeash(mc.player);
                break;
            case "item.dev_mod.spell_blink":
                Blink.onBlink();
                break;
            // add cases for other spells
            default:
                // handle default case
                break;
        }

    }
}
