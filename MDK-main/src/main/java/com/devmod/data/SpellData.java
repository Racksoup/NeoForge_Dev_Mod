package com.devmod.data;

import com.devmod.DevMod;
import net.minecraft.client.Minecraft;
import net.neoforged.neoforge.client.event.InputEvent;

import java.util.Timer;
import java.util.TimerTask;

public class SpellData {
    private static boolean GCD = false;
    private static int GCDLength = 600;
    public static int slot = 1;


    public static boolean isGCD() {return GCD;}

    public static boolean activateGCD() {
        if (GCD) {
            GCD = true;
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    GCD = false;
                }

            }, GCDLength);
            return true;
        }
        return false;
    }

    public static void activateCD(BooleanSetter setter, Integer cdLength) {
        setter.set(false);
        Timer cdTimer = new Timer();
        cdTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                setter.set(true);
            }
        }, cdLength);
    }

    public static void setSlot() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player != null) {
            slot = mc.player.getInventory().selected;
        }
    }

    public interface BooleanSetter {
        void set(boolean value);
    }
}