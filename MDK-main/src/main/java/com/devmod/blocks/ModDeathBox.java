package com.devmod.blocks;

import com.devmod.DevMod;
import com.devmod.data.PlayerData;
import com.devmod.entities.ModDeathBoxBlockEntity;
import com.devmod.menus.ModDeathBoxMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;


public class ModDeathBox extends Block implements EntityBlock {
    public ModDeathBox(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack) {
        if (pLevel.getBlockEntity(pPos) instanceof ModDeathBoxBlockEntity blockEntity) {
            PlayerData.PLAYER_DATA.putUUID("deathBoxUUID", blockEntity.getId());
            if (pPlacer instanceof Player) {
                UUID playerUUID = pPlacer.getUUID();
            }
        }
        super.setPlacedBy(pLevel, pPos, pState, pPlacer, pStack);
    }

    @Override
    public BlockState playerWillDestroy(Level pLevel, BlockPos pPos, BlockState pState, Player pPlayer) {
        if (pLevel.getBlockEntity(pPos) instanceof ModDeathBoxBlockEntity blockEntity) {
            UUID blockUUID = blockEntity.getId();
            UUID savedBlockUUID = PlayerData.PLAYER_DATA.getUUID("deathBoxUUID");
            if (pLevel.isClientSide) {DevMod.LOGGER.info("client"); } else {DevMod.LOGGER.info("server");}

            if (blockUUID.equals(savedBlockUUID)) {
                PlayerData.PLAYER_DATA.putBoolean("hasDeathBox", false);
                PlayerData.PLAYER_DATA.remove("deathBoxUUID");
            }
        }
        return super.playerWillDestroy(pLevel, pPos, pState, pPlayer);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new ModDeathBoxBlockEntity(pPos, pState);
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
        ModDeathBoxBlockEntity blockEntity = (ModDeathBoxBlockEntity) pLevel.getBlockEntity(pPos);
        return new SimpleMenuProvider(
                (id, playerInventory, playerEntity) -> new ModDeathBoxMenu(id, playerInventory, blockEntity.getItemHandler(), ContainerLevelAccess.create(pLevel, pPos)), Component.translatable("Mod Death Box")
        );
    }
}
