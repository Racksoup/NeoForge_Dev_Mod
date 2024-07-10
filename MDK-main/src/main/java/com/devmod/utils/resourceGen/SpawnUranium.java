package com.devmod.utils.resourceGen;

import com.devmod.registers.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.event.level.BlockEvent;

public class SpawnUranium {

    public static void spawn(BlockEvent.BreakEvent event) {
        Level level = (Level) event.getLevel();
        BlockPos pos = event.getPos();

        if (event.getState().getBlock() == Blocks.COAL_BLOCK) {
            ItemStack uraniumStack = new ItemStack(ModItems.URANIUM_ITEM.get());
            ItemEntity uraniumEntity = new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), uraniumStack);
            level.addFreshEntity(uraniumEntity);
        }

        if (event.getState().getBlock() == Blocks.SNOW) {
            ItemStack stableWaterStack = new ItemStack(ModItems.STABLE_WATER_ITEM.get());
            ItemEntity stableWaterEntity = new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), stableWaterStack);
            level.addFreshEntity(stableWaterEntity);
        }
    }
}
