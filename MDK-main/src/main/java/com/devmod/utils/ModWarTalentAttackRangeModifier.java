package com.devmod.utils;

import com.devmod.data.ModRoleData;
import com.devmod.registers.ModItems;
import com.devmod.ui.ModTalents;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;

public class ModWarTalentAttackRangeModifier {

    public static void increaseRangeTalent(AttackEntityEvent event) {
        Player player = event.getEntity();
        ItemStack heldItem = player.getMainHandItem();

        if (heldItem.getItem() == ModItems.NEO_BATTLE_AXE.get() && ModRoleData.getCurrentClass().equals("warrior") && ModRoleData.WarTree.getT1()) {
            player.getAttribute(Attributes.ENTITY_INTERACTION_RANGE).setBaseValue(ModRoleData.WarTree.attackRangeLevel1);
        } else {
            player.getAttribute(Attributes.ENTITY_INTERACTION_RANGE).setBaseValue(ModRoleData.WarTree.attackRangeDefault);
        }
    }

    public static void setRangeTalent(Double val) {
        Minecraft.getInstance().player.getAttribute(Attributes.ENTITY_INTERACTION_RANGE).setBaseValue(val);
    }
}
