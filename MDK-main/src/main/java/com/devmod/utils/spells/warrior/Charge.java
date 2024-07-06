package com.devmod.utils.spells.warrior;

import com.devmod.data.RoleData;
import com.devmod.data.SpellData;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

import java.util.Timer;
import java.util.TimerTask;

public class Charge {

    public static void onCharge() {
        Player player = Minecraft.getInstance().player;

        if (!RoleData.War.chargeCD && RoleData.getCurrentClass().equals("warrior")) {
            // set charge speed
            AttributeInstance movementSpeedAttribute = player.getAttribute(Attributes.MOVEMENT_SPEED);
            movementSpeedAttribute.setBaseValue(RoleData.War.chargeSpeed);

            // reset to walk speed
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    movementSpeedAttribute.setBaseValue(RoleData.War.walkSpeed);
                }
            }, RoleData.War.chargeTime);

            // activate CD
            SpellData.activateGCD();
            SpellData.activateCD(RoleData.War.setChargeCD(), RoleData.War.chargeCDLength);
        }
    }
}
