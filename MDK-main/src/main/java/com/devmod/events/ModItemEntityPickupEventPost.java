package com.devmod.events;

import com.devmod.DevMod;
import net.neoforged.neoforge.event.entity.player.ItemEntityPickupEvent;

public class ModItemEntityPickupEventPost {

    public static void handler(ItemEntityPickupEvent.Post event) {
        DevMod.LOGGER.info("ItemEntityPickupEvent, {}", event);
    }
}
