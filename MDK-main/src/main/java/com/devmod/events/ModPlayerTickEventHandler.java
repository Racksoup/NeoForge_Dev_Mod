package com.devmod.events;

import com.devmod.utils.ModShamTalentMoveSpeedModifier;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

public class ModPlayerTickEventHandler {

    public static void onPlayerTick(PlayerTickEvent.Post event) {

        // Shaman Talent 1
       ModShamTalentMoveSpeedModifier.resetSpeedOnCrouchSprint(event);
    }
}
