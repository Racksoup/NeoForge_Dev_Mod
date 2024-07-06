package com.devmod.utils.talents.warrior;

import com.devmod.data.RoleData;
import com.devmod.registers.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;

public class AttackRange {

    public static void increaseRangeOnAttack(AttackEntityEvent event) {
        Player player = event.getEntity();
        ItemStack heldItem = player.getMainHandItem();

        if (heldItem.getItem() == ModItems.NEO_BATTLE_AXE.get() && RoleData.getCurrentClass().equals("warrior") && RoleData.War.getT1()) {
            player.getAttribute(Attributes.ENTITY_INTERACTION_RANGE).setBaseValue(RoleData.War.attackRangeLevel1);
        } else {
            player.getAttribute(Attributes.ENTITY_INTERACTION_RANGE).setBaseValue(RoleData.War.attackRangeDefault);
        }
    }

    public static void setRange(Double val) {
        Minecraft.getInstance().player.getAttribute(Attributes.ENTITY_INTERACTION_RANGE).setBaseValue(val);
    }
}
