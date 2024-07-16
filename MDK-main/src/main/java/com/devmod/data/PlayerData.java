package com.devmod.data;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

public class PlayerData {
    public static final CompoundTag PLAYER_DATA = new CompoundTag();
    private static Inventory inventory;
    private static Inventory previousInventory;
    public static Inventory getPreviousInventory() {return previousInventory;}
    public static Inventory getInventory() {return inventory;}

    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        CompoundTag playerData = player.getPersistentData();
        if (playerData.contains("devMod")) {
            PLAYER_DATA.merge(playerData.getCompound("devMod"));
            RoleData.Warrior.WAR_TREE_DATA.merge(PLAYER_DATA.getCompound("warTree"));
            RoleData.Shaman.SHAM_TREE_DATA.merge(PLAYER_DATA.getCompound("shamTree"));
            RoleData.Mage.MAGE_TREE_DATA.merge(PLAYER_DATA.getCompound("mageTree"));
        }
        RoleData.setTalents();
    }
    public static void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        Player player = event.getEntity();
        CompoundTag playerData = player.getPersistentData();
        PLAYER_DATA.put("warTree", RoleData.Warrior.WAR_TREE_DATA);
        PLAYER_DATA.put("shamTree", RoleData.Shaman.SHAM_TREE_DATA);
        PLAYER_DATA.put("mageTree", RoleData.Mage.MAGE_TREE_DATA);
        playerData.put("devMod", PLAYER_DATA);
    }
    public static void onPlayerClone(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            CompoundTag originalData = event.getOriginal().getPersistentData();
            CompoundTag newData = event.getEntity().getPersistentData();
            if (originalData.contains("devMod")) {
                newData.put("devMod", originalData.getCompound("devMod"));
            }
        }
    }

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
