package com.devmod.utils;

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
        return ModRoleData.ShamTree.moveSpeedDefault;
    }

    public static void resetSpeedOnCrouchSprint(PlayerTickEvent.Post event) {
        if (ModRoleData.getCurrentClass().equals("shaman") && ModRoleData.ShamTree.getT1()) {
            Player player = event.getEntity();

            if (player.isCrouching() && !wasCrouching) {
                wasCrouching = true;
            } else if (!player.isCrouching() && wasCrouching) {
                resetSpeed(player, ModRoleData.ShamTree.moveSpeedLevel1);
                wasCrouching = false;
            }

            if (player.isSprinting() && !wasSprinting) {

                resetSpeed(player, ModRoleData.ShamTree.moveSpeedLevel1 + ModRoleData.ShamTree.runSpeed);
                wasSprinting = true;
            } else if (!player.isSprinting() && wasSprinting) {
                resetSpeed(player, ModRoleData.ShamTree.moveSpeedLevel1);
                wasCrouching = false;
            }
        }
    }

    public static void resetSpeed(Player player, Double speed) {
        ModShamTalentMoveSpeedModifier.setPlayerSpeed(speed);
    }
}
