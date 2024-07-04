package com.devmod;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

public class ModRoleData {
    private static final CompoundTag MOD_DATA = new CompoundTag();

    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        CompoundTag playerData = player.getPersistentData();
        if (playerData.contains("devMod")) {
            MOD_DATA.merge(playerData.getCompound("devMod"));
            WarTree.WAR_TREE_DATA.merge(MOD_DATA.getCompound("warTree"));
            ShamTree.SHAM_TREE_DATA.merge(MOD_DATA.getCompound("shamTree"));
            MageTree.MAGE_TREE_DATA.merge(MOD_DATA.getCompound("mageTree"));
        }
    }
    public static void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        Player player = event.getEntity();
        CompoundTag playerData = player.getPersistentData();
        MOD_DATA.put("warTree", WarTree.WAR_TREE_DATA);
        MOD_DATA.put("shamTree", ShamTree.SHAM_TREE_DATA);
        MOD_DATA.put("mageTree", MageTree.MAGE_TREE_DATA);
        playerData.put("devMod", MOD_DATA);
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

    public static String getCurrentClass() {
        return MOD_DATA.getString("currentClass");
    }
    public static void setCurrentClass(String currentClass) {
        MOD_DATA.putString("currentClass", currentClass);

        Player player = Minecraft.getInstance().player;
        if (player != null) {
            CompoundTag playerData = player.getPersistentData();
            playerData.put("devMod", MOD_DATA);
        }
    }

    public static class WarTree {
        private static final CompoundTag WAR_TREE_DATA = new CompoundTag();

        public static Boolean getT1() {
            return WAR_TREE_DATA.getByte("t1") != (byte) 0;
        }
        public static void setT1(Boolean currentClass) {
            WAR_TREE_DATA.putByte("t1", (byte) (currentClass ? 1 : 0));
            Player player = Minecraft.getInstance().player;
            if (player != null) {
                MOD_DATA.put("warTree", WAR_TREE_DATA);
                CompoundTag playerData = player.getPersistentData();
                playerData.put("devMod", MOD_DATA);
            }
        }
    }

    public static class ShamTree {
        private static final CompoundTag SHAM_TREE_DATA = new CompoundTag();

        public static Boolean getT1() {
            return SHAM_TREE_DATA.getByte("t1") != (byte) 0;
        }
        public static void setT1(Boolean currentClass) {
            SHAM_TREE_DATA.putByte("t1", (byte) (currentClass ? 1 : 0));
            Player player = Minecraft.getInstance().player;
            if (player != null) {
                MOD_DATA.put("shamTree", SHAM_TREE_DATA);
                CompoundTag playerData = player.getPersistentData();
                playerData.put("devMod", MOD_DATA);
            }
        }
    }

    public static class MageTree {
        private static final CompoundTag MAGE_TREE_DATA = new CompoundTag();

        public static Boolean getT1() {
            return MAGE_TREE_DATA.getByte("t1") != (byte) 0;
        }
        public static void setT1(Boolean currentClass) {
            MAGE_TREE_DATA.putByte("t1", (byte) (currentClass ? 1 : 0));
            Player player = Minecraft.getInstance().player;
            if (player != null) {
                MOD_DATA.put("mageTree", MAGE_TREE_DATA);
                CompoundTag playerData = player.getPersistentData();
                playerData.put("devMod", MOD_DATA);
            }
        }
    }
}
