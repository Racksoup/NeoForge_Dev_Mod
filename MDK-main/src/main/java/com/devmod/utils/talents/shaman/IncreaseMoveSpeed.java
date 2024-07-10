package com.devmod.utils.talents.shaman;

import com.devmod.data.RoleData;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

public class IncreaseMoveSpeed {

    private static boolean wasCrouching = false;
    private static boolean wasSprinting = false;

    public static void setEffect(double speed) {
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
        return RoleData.Shaman.moveSpeedDefault;
    }

    public static void setEffectOnCrouchOrSprint(PlayerTickEvent.Post event) {
        if (RoleData.getCurrentClass().equals("shaman") && RoleData.Shaman.getT1()) {
            Player player = event.getEntity();

            if (player.isCrouching() && !wasCrouching) {
                wasCrouching = true;
            } else if (!player.isCrouching() && wasCrouching) {
                resetSpeed(player, RoleData.Shaman.moveSpeedLevel1);
                wasCrouching = false;
            }

            if (player.isSprinting() && !wasSprinting) {

                resetSpeed(player, RoleData.Shaman.moveSpeedLevel1 + RoleData.Shaman.runSpeed);
                wasSprinting = true;
            } else if (!player.isSprinting() && wasSprinting) {
                resetSpeed(player, RoleData.Shaman.moveSpeedLevel1);
                wasCrouching = false;
            }
        }
    }

    public static void resetSpeed(Player player, Double speed) {
        player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(speed);
    }
}
