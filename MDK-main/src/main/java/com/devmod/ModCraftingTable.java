package com.devmod;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;


public class ModCraftingTable extends Block {
    private static final Component CONTAINER_TITLE = Component.translatable("container.crafting");

    public ModCraftingTable(BlockBehaviour.Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, BlockHitResult pHitResult) {
        if (pLevel.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            MenuProvider menuProvider = pState.getMenuProvider(pLevel, pPos);
            if (menuProvider != null) {
                pPlayer.openMenu(menuProvider);
                pPlayer.awardStat(Stats.INTERACT_WITH_CRAFTING_TABLE);

                ModCraftingMenu modCraftingMenu = new ModCraftingMenu(0, pPlayer.getInventory(), ContainerLevelAccess.create(pLevel, pPos));
                Minecraft minecraft = Minecraft.getInstance();
                minecraft.execute(() -> minecraft.setScreen(new ModCraftingScreen(modCraftingMenu, pPlayer.getInventory())));
            }

            return InteractionResult.CONSUME;
        }
    }

    @Override
    public MenuProvider getMenuProvider(BlockState pState, Level pLevel, BlockPos pPos) {
        return new SimpleMenuProvider(
                (p_52229_, p_52230_, p_52231_) -> {
                    return new ModCraftingMenu(p_52229_, p_52230_, ContainerLevelAccess.create(pLevel, pPos));
                }, CONTAINER_TITLE
        );
    }
}
