package com.devmod.events;

import com.devmod.utils.talents.mage.StunOnAttack;
import net.neoforged.neoforge.event.entity.living.LivingHurtEvent;

public class ModLivingHurtEvent {

    public static void handler(LivingHurtEvent event) {

        // Mage Stun Talent
        StunOnAttack.effect(event);
    }
}
