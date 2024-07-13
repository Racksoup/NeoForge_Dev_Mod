package com.devmod.entities;

import com.devmod.registers.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.wrapper.InvWrapper;
import org.jetbrains.annotations.NotNull;

public class ModDeathBoxBlockEntity extends BlockEntity {
    private final SimpleContainer inventory;
    private final IItemHandler itemHandler;

    public ModDeathBoxBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.MOD_DEATH_BOX_BLOCK_ENTITY.get(), pPos, pBlockState);
        this.inventory = new SimpleContainer(6*9) {
            @Override
            public void setChanged() {
                super.setChanged();
                ModDeathBoxBlockEntity.this.setChanged();
                ModDeathBoxBlockEntity.this.sync();
            }
        };
        this.itemHandler = new InvWrapper(this.inventory);
    }

    public IItemHandler getItemHandler() {
        return this.itemHandler;
    }

    @Override
    protected void loadAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.loadAdditional(pTag, pRegistries);
        pTag.put("DeathBoxInventory", ContainerHelper.saveAllItems(new CompoundTag(), this.inventory.getItems(), pRegistries));
    }

    @Override
    protected void saveAdditional(CompoundTag pTag, HolderLookup.Provider pRegistries) {
        super.saveAdditional(pTag, pRegistries);
        ContainerHelper.loadAllItems(pTag.getCompound("DeathBoxInventory"), this.inventory.getItems(), pRegistries);
    }

    @Override
    public @NotNull CompoundTag getUpdateTag(HolderLookup.Provider pRegistries) {
        CompoundTag nbt = super.getUpdateTag(pRegistries);
        saveAdditional(nbt, pRegistries);
        return nbt;
    }

    @Override
    public void handleUpdateTag(CompoundTag tag, HolderLookup.Provider lookupProvider) {
        super.handleUpdateTag(tag, lookupProvider);
        loadAdditional(tag, lookupProvider);
    }

    // Custom method to sync inventory changes to the client
    private void sync() {
        if (level != null && !level.isClientSide) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        }
    }

    public SimpleContainer getInventory() {
        return inventory;
    }


//    @Override
//    public boolean canPlaceItem(int pSlot, ItemStack pStack) {
//        return super.canPlaceItem(pSlot, pStack);
//    }
}
