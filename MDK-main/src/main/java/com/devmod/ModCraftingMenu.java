package com.devmod;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

public class ModCraftingMenu extends AbstractContainerMenu {
    private ContainerLevelAccess access;

    // Client menu constructor
    public ModCraftingMenu(int containerId, Inventory playerInventory) {
        this(containerId, playerInventory, ContainerLevelAccess.NULL);
    }

    // Server menu constructor
    public ModCraftingMenu(int containerId, Inventory playerInventory, ContainerLevelAccess access) {
        super(ModMenus.MOD_CRAFTING_MENU.get(), containerId);
        this.access = access;
    }

//    public ModCraftingMenu(int pContainerId, Inventory pPlayerInventory) {
//        super(ModMenus.MOD_CRAFTING_MENU.get(), pContainerId);
//    }

//    public ModCraftingMenu(int pContainerId, Inventory pPlayerInventory, ContainerLevelAccess containerLevelAccess) {
//        super(pContainerId, pPlayerInventory, containerLevelAccess);
//    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        return null;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return AbstractContainerMenu.stillValid(this.access, pPlayer, ModBlocks.MY_CRAFTING_TABLE_BLOCK.get());
    }
}
