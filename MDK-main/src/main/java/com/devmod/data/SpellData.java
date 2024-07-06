package com.devmod.data;

import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Function;

public class SpellData {
    private static boolean spellUsable = true;
    private static Integer globalCooldown = 600;

    public static boolean isSpellUsable() {return spellUsable;}

    public static boolean spellUsable() {
        if (spellUsable) {
            spellUsable = false;
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    spellUsable = true;
                }

            }, globalCooldown);
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

    public interface BooleanSetter {
        void set(boolean value);
    }
}
