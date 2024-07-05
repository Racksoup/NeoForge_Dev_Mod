package com.devmod.events;

import com.devmod.utils.ModWarTalentAttackRangeModifier;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;


public class ModAttackRangeEventHandler {

    public static void onPlayerAttack(AttackEntityEvent event) {

        // Warrior Attack Range Talent
        ModWarTalentAttackRangeModifier.increaseRangeTalent(event);
    }
}
