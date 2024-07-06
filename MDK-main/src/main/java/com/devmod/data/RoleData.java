package com.devmod.data;

import com.devmod.utils.talents.shaman.MoveSpeed;
import com.devmod.utils.talents.warrior.AttackRange;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

public class RoleData {
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
//        setTalents();
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


    public static void setTalents() {
        WarTree.resetTalents();
        ShamTree.resetTalents();
        MageTree.resetTalents();

        if (RoleData.getCurrentClass().equals("warrior")) {
            WarTree.setTalents();
        } else if (RoleData.getCurrentClass().equals("shaman")) {
            ShamTree.setTalents();
        } else if (RoleData.getCurrentClass().equals("mage")) {
            MageTree.setTalents();
        }
    }

    public static class WarTree {
        private static final CompoundTag WAR_TREE_DATA = new CompoundTag();

        public static Double attackRangeLevel1 = 40.0d;
        public static Double attackRangeDefault = 3.0d;
        public static Double chargeSpeed = 1.05d;
        public static Double walkSpeed = 0.1d;
        public static Integer chargeTime = 140;
        public static boolean chargeCD = true;
        public static SpellData.BooleanSetter setChargeCD() {return val -> chargeCD = val;}
        public static Integer chargeCDLength = 2 *1000;

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

        public static void resetTalents() {
            AttackRange.setRange(attackRangeLevel1);
        }

        public static void setTalents() {
            AttackRange.setRange(attackRangeDefault);
        }
    }

    public static class ShamTree {
        private static final CompoundTag SHAM_TREE_DATA = new CompoundTag();

        public static Double moveSpeedDefault = .1D;
        public static Double moveSpeedLevel1 = .1D * 2.0D;
        public static Double runSpeed = .1D;

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

        public static void resetTalents() {
            MoveSpeed.setPlayerSpeed(moveSpeedDefault);
        }

        public static void setTalents() {
            MoveSpeed.setPlayerSpeed(moveSpeedLevel1);
        }
    }

    public static class MageTree {
        private static final CompoundTag MAGE_TREE_DATA = new CompoundTag();
        public static Double stunChanceLevel1 = 1.0D;
        public static Double stunChanceDefault = 0D;

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

        public static void resetTalents() {
        }

        public static void setTalents() {

        }
    }
}
