package com.devmod.events;

import com.devmod.utils.resourceGen.SpawnStableWater;
import com.devmod.utils.resourceGen.SpawnUranium;
import net.neoforged.neoforge.event.level.BlockEvent;

public class ModBlockEventBreakEvent {

    public static void handler(BlockEvent.BreakEvent event) {
        // spawn uranium
        SpawnUranium.spawn(event);

        // spawn stable water
        SpawnStableWater.spawn(event);
    }
}
