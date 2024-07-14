package com.devmod.entities;

import com.devmod.registers.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.util.INBTSerializable;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;

public class ModDeathBoxBlockEntity extends ChestBlockEntity {
    private CompoundTag items = new CompoundTag();
    private final ItemStackHandler itemHandler = new ItemStackHandler(6*9) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            saveItemsToTag(level.registryAccess());
        }
    };

    public ModDeathBoxBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.MOD_DEATH_BOX_BLOCK_ENTITY.get(), pPos, pBlockState);
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);
        this.items = pTag.getCompound("ModDeathBox");
        loadItemsFromTag(pRegistries);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.saveAdditional(pTag, pRegistries);
        pTag.put("ModDeathBox", this.items);
    }

    @Override
    public void setChanged() {
        super.setChanged();
        level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 2);
    }
//
    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return this.items;
    }
//
    @Override
    public void handleUpdateTag(CompoundTag tag, HolderLookup.Provider lookupProvider) {
        super.handleUpdateTag(tag, lookupProvider);
        this.items = tag;
        loadItemsFromTag(lookupProvider);
    }

    public IItemHandler getItemHandler() {
        return this.itemHandler;
    }

    private void saveItemsToTag(HolderLookup.Provider provider) {
        items = new CompoundTag();
        CompoundTag itemHandlerTag = ((INBTSerializable<CompoundTag>) itemHandler).serializeNBT(provider);
        items.put("ItemHandler", itemHandlerTag);
    }

    private void loadItemsFromTag(HolderLookup.Provider provider) {
        if (items.contains("ItemHandler")) {
            CompoundTag itemHandlerTag = items.getCompound("ItemHandler");
            ((INBTSerializable<CompoundTag>) itemHandler).deserializeNBT(provider, itemHandlerTag);
        }
    }
}
