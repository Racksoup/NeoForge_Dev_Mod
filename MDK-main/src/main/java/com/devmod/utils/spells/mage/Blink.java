package com.devmod.utils.spells.mage;

import com.devmod.data.RoleData;
import com.devmod.data.SpellData;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

import java.util.Timer;
import java.util.TimerTask;

public class Blink {

    public static void cast() {
        Player player = Minecraft.getInstance().player;

        if (!RoleData.Mage.blinkCD && RoleData.getCurrentClass().equals("mage")) {
            // set blink speed
            AttributeInstance movementSpeedAttribute = player.getAttribute(Attributes.MOVEMENT_SPEED);
            movementSpeedAttribute.setBaseValue(RoleData.Mage.blinkSpeed);

            // reset to walk speed
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    movementSpeedAttribute.setBaseValue(RoleData.Mage.walkSpeed);
                }
            }, RoleData.Mage.blinkTime);

            // activate CD
            SpellData.activateGCD();
            SpellData.activateCD(RoleData.Mage.setBlinkCD(), RoleData.Mage.blinkCDLength);
        }
    }
}
