package com.devmod;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CraftingTableBlock;
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
        DevMod.LOGGER.info("use called on server side: {}", !pLevel.isClientSide);
        if (pLevel.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            DevMod.LOGGER.info("Opening crafting menu for player at position: {}", pPos);
            MenuProvider menuProvider = pState.getMenuProvider(pLevel, pPos);
            if (menuProvider != null) {
                pPlayer.openMenu(menuProvider);
                pPlayer.awardStat(Stats.INTERACT_WITH_CRAFTING_TABLE);
            } else {
                DevMod.LOGGER.error("MenuProvider is null at position: {}", pPos);
            }
            return InteractionResult.CONSUME;
        }
    }

    @Override
    public MenuProvider getMenuProvider(BlockState pState, Level pLevel, BlockPos pPos) {
        DevMod.LOGGER.info("getMenuProvider called at position: {}", pPos);
        return new SimpleMenuProvider(
                (p_52229_, p_52230_, p_52231_) -> {
                    DevMod.LOGGER.info("Creating new ModCraftingMenu at position: {}", pPos);
                    return new ModCraftingMenu(p_52229_, p_52230_, ContainerLevelAccess.create(pLevel, pPos));
                }, CONTAINER_TITLE
        );
    }
}
