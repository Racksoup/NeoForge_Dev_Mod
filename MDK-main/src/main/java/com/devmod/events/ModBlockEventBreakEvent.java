package com.devmod.events;

import com.devmod.utils.resourceGen.SpawnUranium;
import net.neoforged.neoforge.event.level.BlockEvent;

public class ModBlockEventBreakEvent {

    public static void handler(BlockEvent.BreakEvent event) {
        // Spawn Uranium
        SpawnUranium.spawn(event);
    }
}
