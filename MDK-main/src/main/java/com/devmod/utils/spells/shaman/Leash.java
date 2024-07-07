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
            HitResult target = player.pick(20.0d, 0.0f, false);
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

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                playerPos[0] = player.position();
                if (playerPos[0].distanceTo(targetPos) <= 1.0) {
                    this.cancel();
                    return;
                }
                player.setDeltaMovement(direction.scale(0.5));
            }
        }, 0, 50);
    }
}
