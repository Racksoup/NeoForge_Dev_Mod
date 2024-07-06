package com.devmod.utils.spells.warrior;

import com.devmod.DevMod;
import com.devmod.data.ModRoleData;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

import java.util.Timer;
import java.util.TimerTask;

public class Charge {

    public static void onCharge() {
        Player player = Minecraft.getInstance().player;
        AttributeInstance movementSpeedAttribute = player.getAttribute(Attributes.MOVEMENT_SPEED);
        movementSpeedAttribute.setBaseValue(ModRoleData.WarTree.chargeSpeed);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                DevMod.LOGGER.info("Charging " + player.getDisplayName().getString() + "...");
                movementSpeedAttribute.setBaseValue(ModRoleData.WarTree.walkSpeed);
            }
        }, ModRoleData.WarTree.chargeTime);
    }
}
