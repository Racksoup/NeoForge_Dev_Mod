package com.devmod.events;

import com.devmod.utils.talents.warrior.MeleeRangeIncrease;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;


public class ModPlayerAttackEventHandler {

    public static void handler(AttackEntityEvent event) {

        // Warrior Attack Range Talent
        MeleeRangeIncrease.increaseRangeOnAttack(event);
    }
}
