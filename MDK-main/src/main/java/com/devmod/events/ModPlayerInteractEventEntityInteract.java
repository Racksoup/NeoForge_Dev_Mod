package com.devmod.events;

import com.devmod.utils.resourceGen.SpawnTurtleShell;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

public class ModPlayerInteractEventEntityInteract {


    public static void handler(PlayerInteractEvent.EntityInteract event) {

        // turtle shells on turtle click
        SpawnTurtleShell.spawn(event);

    }
}
