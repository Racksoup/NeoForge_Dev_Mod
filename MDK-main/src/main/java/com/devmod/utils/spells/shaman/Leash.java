package com.devmod.utils.spells.shaman;

import com.devmod.data.RoleData;
import com.devmod.data.SpellData;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.Timer;
import java.util.TimerTask;

public class Leash {

    public static void onLeash(Player player) {
        if (!RoleData.Shaman.leashCD && RoleData.getCurrentClass().equals("shaman")) {
            HitResult target = player.pick(60.0d, 0.0f, false);
            if (target.getType() == HitResult.Type.BLOCK) {
                BlockHitResult block = (BlockHitResult) target;
                Vec3 targetPos = block.getLocation();
                pullPlayerToTarget(player, targetPos);
            } else if (target.getType() == HitResult.Type.ENTITY) {
                EntityHitResult entity = (EntityHitResult) target;
                Vec3 targetPos = entity.getLocation();
                pullPlayerToTarget(player, targetPos);
            }
        }

        // activate CD
        SpellData.activateGCD();
        SpellData.activateCD(RoleData.Shaman.setLeashCD(), RoleData.Shaman.leashCDLength);
    }

    private static void pullPlayerToTarget(Player player, Vec3 targetPos) {
        final Vec3[] playerPos = {player.position()};
        Vec3 direction = targetPos.subtract(playerPos[0]).normalize();
        final double[] lastDistanceToTargetPos = {playerPos[0].distanceTo(targetPos)};
        double maxSpeed = RoleData.Shaman.leashSpeed;
        int timeToMaxSpeed = RoleData.Shaman.leashTimeToMaxSpeed;
        int period = RoleData.Shaman.leashPeriod;
        double startSpeedPercentage = RoleData.Shaman.leashStartSpeedPercentage;
        final int[] timePast = {0};

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                timePast[0] += period;
                playerPos[0] = player.position();
                double distanceToTarget = playerPos[0].distanceTo(targetPos);
                if (distanceToTarget <= 1.0 || distanceToTarget > lastDistanceToTargetPos[0]) { // check if too close or past target
                    this.cancel();
                    return;
                }
                lastDistanceToTargetPos[0] = distanceToTarget; // set after checking math
                double startSpeed = 1.0d - startSpeedPercentage; // start speed at 30% rather than 0%
                double interval = startSpeed * ((double) timePast[0] / timeToMaxSpeed); // gives smaller increase intervals from 30% -> 100%, rather than larger intervals of 0% -> 100%
                double acceleratedFromBaseSpeed = interval * maxSpeed; // gives the actual speed increase
                double speed = acceleratedFromBaseSpeed + (startSpeedPercentage * maxSpeed); // add the speed increase to base 30% speed
                player.setDeltaMovement(direction.scale(speed));
            }
        }, 0, period);
    }
}
