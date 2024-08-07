package com.devmod.utils.talents.mage;

import com.devmod.data.RoleData;
import com.devmod.registers.ModItems;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.entity.living.LivingHurtEvent;

public class StunOnAttack {

    public static void effect(LivingHurtEvent event) {
        DamageSource source = event.getSource();
        ItemStack heldItem = null;

        if (source.getEntity() instanceof Player) {
            heldItem = ((Player) source.getEntity()).getMainHandItem();
        }

        if (heldItem != null) {
            if (heldItem.getItem() == ModItems.WEAPON_TEMPERED_STAFF.get() && RoleData.getCurrentClass().equals("mage") && RoleData.Mage.getT1() && Math.random() < RoleData.Mage.stunChanceLevel1) {
                event.getEntity().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 4));
            }
        }
    }
}
