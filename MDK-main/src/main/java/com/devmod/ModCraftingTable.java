package com.devmod;

import com.mojang.serialization.MapCodec;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.inventory.MenuConstructor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;


public class ModCraftingTable extends CraftingTableBlock {

    public ModCraftingTable(BlockBehaviour.Properties pProperties) {
        super(pProperties);
    }

//    @Override
//    public InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, BlockHitResult pHitResult) {
//        if (pLevel.isClientSide) {
//            return InteractionResult.SUCCESS;
//        } else {
//            pPlayer.awardStat(Stats.INTERACT_WITH_CRAFTING_TABLE);
//
//            Minecraft minecraft = Minecraft.getInstance();
//            minecraft.execute(() -> {
//                ModCraftingMenu modCraftingMenu = new ModCraftingMenu(
//                        pPlayer.containerMenu.containerId,
//                        pPlayer.getInventory(),
//                        ContainerLevelAccess.create(pLevel, pPos)
//                );
//                minecraft.setScreen(new ModCraftingScreen(modCraftingMenu, pPlayer.getInventory()));
//            });
//
//            return InteractionResult.CONSUME;
//        }
//    }

//    @Nullable
//    @Override
//    protected MenuProvider getMenuProvider(BlockState pState, Level pLevel, BlockPos pPos) {
//        return super.getMenuProvider(pState, pLevel, pPos);
//    }
//    @Override
//    public MenuProvider getMenuProvider(BlockState pState, Level pLevel, BlockPos pPos) {
//        return new SimpleMenuProvider(
//                (pContainerId, pPlayerInventory, pPlayer) -> new CraftingMenu(pContainerId, pPlayerInventory),
//                CONTAINER_TITLE
//        );
//    }

    @Override
    protected InteractionResult useWithoutItem(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, BlockHitResult pHitResult) {
        if (pLevel.isClientSide) {
            return InteractionResult.SUCCESS;
        } else {
            pPlayer.openMenu(pState.getMenuProvider(pLevel, pPos));
//            pPlayer.awardStat(Stats.INTERACT_WITH_CRAFTING_TABLE);
            return InteractionResult.CONSUME;
        }
    }

    @Override
    protected MenuProvider getMenuProvider(BlockState pState, Level pLevel, BlockPos pPos) {
        return new SimpleMenuProvider(
                (p_52229_, p_52230_, p_52231_) -> new ModCraftingMenu(p_52229_, p_52230_, ContainerLevelAccess.create(pLevel, pPos)), Component.translatable("container.modcraftingtable")
        );
    }
}
