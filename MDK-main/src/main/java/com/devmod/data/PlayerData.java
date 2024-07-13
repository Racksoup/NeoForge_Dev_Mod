package com.devmod.data;

import com.devmod.DevMod;
import net.minecraft.world.entity.player.Inventory;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

public class PlayerData {
    private static Inventory inventory;
    private static Inventory previousInventory;
    public static Inventory getPreviousInventory() {return previousInventory;}
    public static Inventory getInventory() {return inventory;}

    public static void setInventoryOnTick(PlayerTickEvent.Post event) {
        if (event.getEntity().isAlive()) {
            previousInventory = inventory;
            inventory = event.getEntity().getInventory();
            for (int i = 0; i < inventory.getContainerSize(); i++) {
//                DevMod.LOGGER.info("slot: " + i + ", item: " + inventory.getItem(i));
            }
        }
    }
}
