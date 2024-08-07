package com.devmod.utils.talents.warrior;

import com.devmod.data.RoleData;
import com.devmod.registers.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;

public class MeleeRangeIncrease {

    public static void effectOnAttack(AttackEntityEvent event) {
        Player player = event.getEntity();
        ItemStack heldItem = player.getMainHandItem();

        if (heldItem.getItem() == ModItems.WEAPON_NEO_BATTLE_AXE.get() && RoleData.getCurrentClass().equals("warrior") && RoleData.Warrior.getT1()) {
            player.getAttribute(Attributes.ENTITY_INTERACTION_RANGE).setBaseValue(RoleData.Warrior.attackRangeLevel1);
        } else {
            player.getAttribute(Attributes.ENTITY_INTERACTION_RANGE).setBaseValue(RoleData.Warrior.attackRangeDefault);
        }
    }

    public static void setEffect(Double val) {
        Minecraft.getInstance().player.getAttribute(Attributes.ENTITY_INTERACTION_RANGE).setBaseValue(val);
    }
}
