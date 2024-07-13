package com.devmod.events;

import com.devmod.data.PlayerData;
import com.devmod.utils.HotbarHasWrongClassItems;
import com.devmod.utils.talents.shaman.IncreaseMoveSpeed;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

public class ModPlayerTickEventPost {

    public static void handler(PlayerTickEvent.Post event) {

        // shaman talent 1
        IncreaseMoveSpeed.setEffectOnCrouchOrSprint(event);

        // check inventory hotbar for wrong class items
        HotbarHasWrongClassItems.checkThenSet(event);

        // set inventory at end so that other stuff can use old inventory from last tick
        PlayerData.setInventoryOnTick(event);
    }
}
