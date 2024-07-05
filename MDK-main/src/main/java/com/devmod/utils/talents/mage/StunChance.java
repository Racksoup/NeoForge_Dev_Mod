package com.devmod.utils.talents.mage;

import com.devmod.DevMod;
import com.devmod.data.ModRoleData;
import com.devmod.registers.ModItems;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.entity.living.LivingHurtEvent;

public class StunChance {

    public static void stunOnAttack(LivingHurtEvent event) {
        DamageSource source = event.getSource();
        ItemStack heldItem = null;

        if (source.getEntity() instanceof Player) {
            heldItem = ((Player) source.getEntity()).getMainHandItem();
        }

        if (heldItem != null) {
            if (heldItem.getItem() == ModItems.TEMPERED_STAFF.get() && ModRoleData.getCurrentClass().equals("mage") && ModRoleData.MageTree.getT1() && Math.random() < ModRoleData.MageTree.stunChanceLevel1) {
                event.getEntity().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 4));
            }
        }
    }
}
