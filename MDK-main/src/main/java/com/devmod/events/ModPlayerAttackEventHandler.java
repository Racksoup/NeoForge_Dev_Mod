package com.devmod.events;

import com.devmod.utils.talents.warrior.AttackRange;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;


public class ModPlayerAttackEventHandler {

    public static void onPlayerAttack(AttackEntityEvent event) {

        // Warrior Attack Range Talent
        AttackRange.increaseRangeOnAttack(event);
    }
}
