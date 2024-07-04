package com.devmod;

import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;


public class ModAttackRangeEventHandler {

    public static void onPlayerAttack(AttackEntityEvent event) {
        Player player = event.getEntity();
        ItemStack heldItem = player.getMainHandItem();

        if (heldItem.getItem() == ModItems.NEO_BATTLE_AXE.get() && ModRoleData.currentClass.equals("warrior") && ModRoleData.WarTree.t1) {
            player.getAttribute(Attributes.ENTITY_INTERACTION_RANGE).setBaseValue(4.0D);
        } else {
            player.getAttribute(Attributes.ENTITY_INTERACTION_RANGE).setBaseValue(3.0D);
        }
    }
}
