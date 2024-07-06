package com.devmod.utils.spells.warrior;

import com.devmod.DevMod;
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

        // set charge speed
        if (RoleData.WarTree.chargeCD) {
            AttributeInstance movementSpeedAttribute = player.getAttribute(Attributes.MOVEMENT_SPEED);
            movementSpeedAttribute.setBaseValue(RoleData.WarTree.chargeSpeed);

            // reset to walk speed
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    DevMod.LOGGER.info("Charging " + player.getDisplayName().getString() + "...");
                    movementSpeedAttribute.setBaseValue(RoleData.WarTree.walkSpeed);
                }
            }, RoleData.WarTree.chargeTime);

            SpellData.activateCD(RoleData.WarTree.setChargeCD(), RoleData.WarTree.chargeCDLength);
        }
    }
}
