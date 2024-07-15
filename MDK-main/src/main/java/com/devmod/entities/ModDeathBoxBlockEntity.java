package com.devmod.entities;

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

    // on server start or level load, put saved items from blockData into itemHandler
    @Override
    protected void loadAdditional(CompoundTag blockData, HolderLookup.Provider registries) {
        super.loadAdditional(blockData, registries);
        if (blockData.contains("Items")) {
            itemHandler.deserializeNBT(registries, blockData.getCompound("Items"));
        }
    }

    // on server start or level load, returns tag with client blockData. no data on server start, "Items is empty"
    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        CompoundTag blockData = super.getUpdateTag(registries);
        blockData.put("Items", itemHandler.serializeNBT(registries));
        return blockData;
    }

    // on server start or level load, does the same as loadAdditional, put saved items from blockData into itemHandler
    // but i think it's supposed to be a check for the server. so loadAdditional loads the saved blockData.Items for the
    // client, and handleUpdateTag does the same for the server.
    @Override
    public void handleUpdateTag(CompoundTag blockData, HolderLookup.Provider registries) {
        super.handleUpdateTag(blockData, registries);
        if (blockData.contains("Items")) {
            itemHandler.deserializeNBT(registries, blockData.getCompound("Items"));
        }
    }

    public IItemHandler getItemHandler() {
        return this.itemHandler;
    }
}


