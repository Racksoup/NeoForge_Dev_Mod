package com.devmod.utils.resourceGen;

import com.devmod.registers.ModItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

public class SpawnTurtleShell {

    public static void spawn(PlayerInteractEvent.EntityInteract event) {
        Entity entity = event.getTarget();
        Level level = (Level) event.getLevel();
        if (event.getHand() == net.minecraft.world.InteractionHand.MAIN_HAND) {
            if (entity instanceof Turtle) {
                ItemStack turtleShellStack = new ItemStack(ModItems.REAGENT_TURTLE_SHELL_ITEM.get());
                ItemEntity turtleShellEntity = new ItemEntity(level, entity.xo, entity.yo, entity.zo, turtleShellStack);
                level.addFreshEntity(turtleShellEntity);
            }
        }
    }
}
