package com.devmod.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

public class ModPlayerSpeedModifier {

    public static void setPlayerSpeed(double speed) {
        Player player = Minecraft.getInstance().player;
        if (player != null) {
            AttributeInstance movementSpeedAttribute = player.getAttribute(Attributes.MOVEMENT_SPEED);
            if (movementSpeedAttribute != null) {
                movementSpeedAttribute.setBaseValue(speed);
            }
        }
    }

    public static double getPlayerSpeed() {
        Player player = Minecraft.getInstance().player;
        if (player != null) {
            AttributeInstance movementSpeedAttribute = player.getAttribute(Attributes.MOVEMENT_SPEED);
            if (movementSpeedAttribute != null) {
                return movementSpeedAttribute.getBaseValue();
            }
        }
        return 0.7; // Default player speed
    }
}
