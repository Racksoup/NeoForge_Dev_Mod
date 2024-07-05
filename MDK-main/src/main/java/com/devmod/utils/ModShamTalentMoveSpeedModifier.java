package com.devmod.utils;

import com.devmod.DevMod;
import com.devmod.data.ModRoleData;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

public class ModShamTalentMoveSpeedModifier {

    private static boolean wasCrouching = false;
    private static boolean wasSprinting = false;

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
        return 0.1; // Default player speed
    }

    public static void resetSpeedOnCrouchSprint(PlayerTickEvent.Post event) {
        if (ModRoleData.getCurrentClass().equals("shaman") && ModRoleData.ShamTree.getT1()) {
            Player player = event.getEntity();

            if (player.isCrouching() && !wasCrouching) {
                DevMod.LOGGER.info("was crouching");
                wasCrouching = true;
            } else if (!player.isCrouching() && wasCrouching) {
                DevMod.LOGGER.info("was not crouching");
                onPlayerStopCrouching(player);
                wasCrouching = false;
            }

            if (player.isSprinting() && !wasSprinting) {
                DevMod.LOGGER.info("was sprinting");
                wasSprinting = true;
            } else if (!player.isSprinting() && wasSprinting) {
                DevMod.LOGGER.info("was not sprinting");
                onPlayerStopSprinting(player);
                wasCrouching = false;
            }
        }
    }

    public static void onPlayerStopCrouching(Player player) {
        ModShamTalentMoveSpeedModifier.setPlayerSpeed(.1d * 2d);
    }

    public static void onPlayerStopSprinting(Player player) {
        ModShamTalentMoveSpeedModifier.setPlayerSpeed(.1d * 2d);
    }
}
