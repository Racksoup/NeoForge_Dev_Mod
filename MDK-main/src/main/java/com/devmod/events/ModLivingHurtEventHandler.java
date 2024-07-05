package com.devmod.events;

import com.devmod.utils.talents.mage.StunChance;
import net.neoforged.neoforge.event.entity.living.LivingHurtEvent;

public class ModLivingHurtEventHandler {

    public static void onLivingHurt(LivingHurtEvent event) {

        // Mage Stun Talent
        StunChance.stunOnAttack(event);
    }
}
