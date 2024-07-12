package com.devmod.data;

import com.devmod.registers.ModItems;
import com.devmod.utils.talents.shaman.IncreaseMoveSpeed;
import com.devmod.utils.talents.warrior.MeleeRangeIncrease;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

public class RoleData {
    private static final CompoundTag MOD_DATA = new CompoundTag();

    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        CompoundTag playerData = player.getPersistentData();
        if (playerData.contains("devMod")) {
            MOD_DATA.merge(playerData.getCompound("devMod"));
            Warrior.WAR_TREE_DATA.merge(MOD_DATA.getCompound("warTree"));
            Shaman.SHAM_TREE_DATA.merge(MOD_DATA.getCompound("shamTree"));
            Mage.MAGE_TREE_DATA.merge(MOD_DATA.getCompound("mageTree"));
        }
//        setTalents();
    }
    public static void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        Player player = event.getEntity();
        CompoundTag playerData = player.getPersistentData();
        MOD_DATA.put("warTree", Warrior.WAR_TREE_DATA);
        MOD_DATA.put("shamTree", Shaman.SHAM_TREE_DATA);
        MOD_DATA.put("mageTree", Mage.MAGE_TREE_DATA);
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
        Warrior.resetTalents();
        Shaman.resetTalents();
        Mage.resetTalents();

        if (RoleData.getCurrentClass().equals("warrior")) {
            Warrior.setTalents();
        } else if (RoleData.getCurrentClass().equals("shaman")) {
            Shaman.setTalents();
        } else if (RoleData.getCurrentClass().equals("mage")) {
            Mage.setTalents();
        }
    }

    public static class Warrior {
        private static final CompoundTag WAR_TREE_DATA = new CompoundTag();

        public static Double attackRangeLevel1 = 40.0d;
        public static Double attackRangeDefault = 3.0d;
        public static Double chargeSpeed = .7d;
        public static Double walkSpeed = 0.1d;
        public static Integer chargeTime = 210;
        public static boolean chargeCD = false;
        public static SpellData.BooleanSetter setChargeCD() {return val -> chargeCD = val;}
        public static Integer chargeCDLength = 2 *1000;
        public static Item[] Items = {
                ModItems.WARRIOR_BOOTS.get(),
                ModItems.WARRIOR_HELMET.get(),
                ModItems.WARRIOR_CHESTPLATE.get(),
                ModItems.WARRIOR_LEGGINGS.get(),
                ModItems.WEAPON_NEO_BATTLE_AXE.get(),
                ModItems.SPELL_CHARGE.get(),
        };

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
            MeleeRangeIncrease.setEffect(attackRangeLevel1);
        }

        public static void setTalents() {
            MeleeRangeIncrease.setEffect(attackRangeDefault);
        }


    }

    public static class Shaman {
        private static final CompoundTag SHAM_TREE_DATA = new CompoundTag();

        public static double moveSpeedDefault = .1d;
        public static double moveSpeedLevel1 = .1d * 2.0d;
        public static double runSpeed = .1d;
        public static boolean leashCD = false;
        public static int leashCDLength = 2 *1000;
        public static double leashSpeed = .7d;
        public static int leashTimeToMaxSpeed = (int) Math.floor(1.5d * 1000);
        public static int leashPeriod = 50;
        public static double leashStartSpeedPercentage = .3d;
        public static SpellData.BooleanSetter setLeashCD() {return val -> leashCD = val;}
        public static Item[] Items = {
                ModItems.SHAMAN_BOOTS.get(),
                ModItems.SHAMAN_HELMET.get(),
                ModItems.SHAMAN_CHESTPLATE.get(),
                ModItems.SHAMAN_LEGGINGS.get(),
                ModItems.WEAPON_URSA_HEART.get(),
                ModItems.SPELL_LEASH.get(),
        };

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
            IncreaseMoveSpeed.setEffect(moveSpeedDefault);
        }

        public static void setTalents() {
            IncreaseMoveSpeed.setEffect(moveSpeedLevel1);
        }
    }

    public static class Mage {
        private static final CompoundTag MAGE_TREE_DATA = new CompoundTag();
        public static double stunChanceLevel1 = 0.2D;
        public static double stunChanceDefault = 0D;
        public static boolean blinkCD = false;
        public static int blinkCDLength = 5 *1000;
        public static double blinkSpeed = 5d;
        public static int blinkTime = (int) Math.floor(.1 *1000);
        public static double walkSpeed = .1;
        public static SpellData.BooleanSetter setBlinkCD() {return val -> blinkCD = val;}
        public static Item[] Items = {
                ModItems.MAGE_BOOTS.get(),
                ModItems.MAGE_HELMET.get(),
                ModItems.MAGE_CHESTPLATE.get(),
                ModItems.MAGE_LEGGINGS.get(),
                ModItems.WEAPON_TEMPERED_STAFF.get(),
                ModItems.SPELL_BLINK.get(),
        };

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