package com.devmod.events;

import com.devmod.utils.talents.shaman.IncreaseMoveSpeed;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

public class ModPlayerTickEventPost {

    public static void handler(PlayerTickEvent.Post event) {

        // Shaman Talent 1
       IncreaseMoveSpeed.setEffectOnCrouchOrSprint(event);
    }
}
