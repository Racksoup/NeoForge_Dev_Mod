package com.devmod;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.inventory.Slot;

public class ModCraftingMenu extends CraftingMenu {

    public ModCraftingMenu(int pContainerId, Inventory pPlayerInventory, ContainerLevelAccess containerLevelAccess) {
        super(pContainerId, pPlayerInventory);
        DevMod.LOGGER.info("ModCraftingMenu created with containerId: {}", pContainerId);
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        DevMod.LOGGER.info("Checking if the menu is still valid for player: {}", pPlayer.getName().getString());
        return super.stillValid(pPlayer);
    }

    @Override
    public Slot addSlot(Slot pSlot) {
        DevMod.LOGGER.info("Adding slot to ModCraftingMenu: {}", pSlot);
        return super.addSlot(pSlot);
    }
}
