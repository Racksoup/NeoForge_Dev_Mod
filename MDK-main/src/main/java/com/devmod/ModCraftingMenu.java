package com.devmod;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.inventory.Slot;

public class ModCraftingMenu extends CraftingMenu {

    public ModCraftingMenu(int pContainerId, Inventory pPlayerInventory, ContainerLevelAccess containerLevelAccess) {
        super(pContainerId, pPlayerInventory);
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return super.stillValid(pPlayer);
    }

    @Override
    public Slot addSlot(Slot pSlot) {
        return super.addSlot(pSlot);
    }
}
