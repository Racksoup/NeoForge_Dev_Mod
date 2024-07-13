package com.devmod.events;

import com.devmod.data.PlayerData;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

public class ModPlayerEventPlayerRespawnEvent {

    public static void handler(PlayerEvent.PlayerRespawnEvent event) {
        Inventory inv = PlayerData.getInventory();

    }
}
