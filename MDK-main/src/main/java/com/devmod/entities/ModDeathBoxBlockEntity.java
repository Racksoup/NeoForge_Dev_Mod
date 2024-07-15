package com.devmod.entities;

import com.devmod.DevMod;
import com.devmod.registers.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;


public class ModDeathBoxBlockEntity extends BlockEntity {
    private final ItemStackHandler itemHandler = new ItemStackHandler(6*9) {
        @Override
        protected void onContentsChanged(int slot) {
            // on slot change, call setChanged() so server calls saveAdditional, saves data to server
            setChanged();
        }
    };

    public ModDeathBoxBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.MOD_DEATH_BOX_BLOCK_ENTITY.get(), pPos, pBlockState);
    }

    // saves itemHandler into blockData.Items when setChanged() called
    @Override
    protected void saveAdditional(CompoundTag blockData, HolderLookup.Provider registries) {
        super.saveAdditional(blockData, registries);
        blockData.put("Items", itemHandler.serializeNBT(registries));
    }

    // on server start or level load, put saved items from blockData into itemHandler for client
    @Override
    protected void loadAdditional(CompoundTag blockData, HolderLookup.Provider registries) {
        super.loadAdditional(blockData, registries);
        if (blockData.contains("Items")) {
            itemHandler.deserializeNBT(registries, blockData.getCompound("Items"));
        }
    }

    public IItemHandler getItemHandler() {
        return this.itemHandler;
    }
}


