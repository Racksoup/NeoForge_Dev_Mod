package com.devmod.events;

import com.devmod.utils.talents.shaman.MoveSpeed;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

public class ModPlayerTickEventHandler {

    public static void onPlayerTick(PlayerTickEvent.Post event) {

        // Shaman Talent 1
       MoveSpeed.resetSpeedOnCrouchSprint(event);
    }
}
