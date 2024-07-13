package com.devmod.blocks;

import com.devmod.menus.ModDeathBoxMenu;
import com.devmod.registers.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;


public class ModDeathBox extends ChestBlock {
    public ModDeathBox(Properties pProperties) {
        super(pProperties, ModBlockEntities.MOD_DEATH_BOX_BLOCK_ENTITY::get);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return super.newBlockEntity(pPos, pState);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, BlockHitResult pHitResult) {
        if (pLevel.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            pPlayer.openMenu(pState.getMenuProvider(pLevel, pPos));
            pPlayer.awardStat(Stats.OPEN_CHEST);
            return InteractionResult.CONSUME;
        }

    }

    @Override
    public MenuProvider getMenuProvider(BlockState pState, Level pLevel, BlockPos pPos) {
        return new SimpleMenuProvider(
                (id, playerInventory, playerEntity) -> new ModDeathBoxMenu(id, playerInventory, ContainerLevelAccess.create(pLevel, pPos)), Component.translatable("Mod Death Box")
        );
    }
}
