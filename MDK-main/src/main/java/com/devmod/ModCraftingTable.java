package com.devmod;

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
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;


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
            pPlayer.awardStat(Stats.INTERACT_WITH_CRAFTING_TABLE);

            Minecraft minecraft = Minecraft.getInstance();
            minecraft.execute(() -> {
                CraftingMenu modCraftingMenu = new CraftingMenu(
                        pPlayer.containerMenu.containerId,
                        pPlayer.getInventory(),
                        ContainerLevelAccess.create(pLevel, pPos)
                );
                minecraft.setScreen(new ModCraftingScreen(modCraftingMenu, pPlayer.getInventory()));
            });

            return InteractionResult.CONSUME;
        }
    }

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
}
