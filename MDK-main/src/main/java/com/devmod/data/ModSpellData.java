package com.devmod.data;

import java.util.Timer;
import java.util.TimerTask;

public class ModSpellData {
    private static boolean spellUsable = true;
    private static Integer globalCooldown = 400;

    public boolean isSpellUsable() {return spellUsable;}

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
}
