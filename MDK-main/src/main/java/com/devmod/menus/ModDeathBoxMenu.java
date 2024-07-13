package com.devmod.menus;

import com.devmod.registers.ModBlocks;
import com.devmod.registers.ModMenus;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.SlotItemHandler;

public class ModDeathBoxMenu extends AbstractContainerMenu {
    private ContainerLevelAccess access;
    private int slotXOffset = 8;

    public ModDeathBoxMenu(int containerId, Inventory playerInventory) {
        this(containerId, playerInventory, new ItemStackHandler(6 * 9), ContainerLevelAccess.NULL);
    }

    public ModDeathBoxMenu(int containerId, Inventory playerInventory, IItemHandler dataInventory,  ContainerLevelAccess access) {
        super(ModMenus.MOD_DEATH_BOX_MENU.get(), containerId);
        this.access = access;

        // add death box slots
        for (int i = 0; i < dataInventory.getSlots(); ++i) {
            int posX = i % 9 * 18 + slotXOffset;
            int posY = i / 9 * 18 + (1 * 18);
            this.addSlot(new SlotItemHandler(dataInventory, i, posX, posY));
        }

        // add inventory slots
        for (int i = 9; i < playerInventory.getContainerSize() -5; ++i) {
            int posX = i % 9 * 18 + slotXOffset;
            int posY = i / 9 * 18 + (7 * 18) -5;
            this.addSlot(new Slot(playerInventory, i, posX, posY));
        }
        // add hotbar slots
        for (int i = 0; i < 9; ++i) {
            int posX = i % 9 * 18 + slotXOffset;
            int posY = i / 9 * 18 + (11 * 18) -1;
            this.addSlot(new Slot(playerInventory, i, posX, posY));
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return AbstractContainerMenu.stillValid(this.access, player, ModBlocks.MOD_DEATH_BOX_BLOCK.get());
    }

    // Assume we have a data inventory of size 5
    // The inventory has 4 inputs (index 1 - 4) which outputs to a result slot (index 0)
    // We also have the 27 player inventory slots and the 9 hotbar slots
    // As such, the actual slots are indexed like so:
    //   - Data Inventory: Result (0), Inputs (1 - 4)
    //   - Player Inventory (5 - 31)
    //   - Player Hotbar (32 - 40)
    @Override
    public ItemStack quickMoveStack(Player player, int quickMovedSlotIndex) {
        // The quick moved slot stack
        ItemStack quickMovedStack = ItemStack.EMPTY;
        // The quick moved slot
        Slot quickMovedSlot = this.slots.get(quickMovedSlotIndex);

        // If the slot is in the valid range and the slot is not empty
        if (quickMovedSlot != null && quickMovedSlot.hasItem()) {
            // Get the raw stack to move
            ItemStack rawStack = quickMovedSlot.getItem();
            // Set the slot stack to a copy of the raw stack
            quickMovedStack = rawStack.copy();

        /*
        The following quick move logic can be simplified to if in data inventory,
        try to move to player inventory/hotbar and vice versa for containers
        that cannot transform data (e.g. chests).
        */

        // If the quick move was performed on the data inventory result slot
        if (quickMovedSlotIndex == 0) {
            // Try to move the result slot into the player inventory/hotbar
            if (!this.moveItemStackTo(rawStack, 5, 41, true)) {
                // If cannot move, no longer quick move
                return ItemStack.EMPTY;
            }

            // Perform logic on result slot quick move
            quickMovedSlot.onQuickCraft(rawStack, quickMovedStack);
        }
        // Else if the quick move was performed on the player inventory or hotbar slot
        else if (quickMovedSlotIndex >= 5 && quickMovedSlotIndex < 41) {
            // Try to move the inventory/hotbar slot into the data inventory input slots
            if (!this.moveItemStackTo(rawStack, 1, 5, false)) {
                // If cannot move and in player inventory slot, try to move to hotbar
                if (quickMovedSlotIndex < 32) {
                    if (!this.moveItemStackTo(rawStack, 32, 41, false)) {
                        // If cannot move, no longer quick move
                        return ItemStack.EMPTY;
                    }
                }
                // Else try to move hotbar into player inventory slot
                else if (!this.moveItemStackTo(rawStack, 5, 32, false)) {
                    // If cannot move, no longer quick move
                    return ItemStack.EMPTY;
                }
            }
        }
        // Else if the quick move was performed on the data inventory input slots, try to move to player inventory/hotbar
        else if (!this.moveItemStackTo(rawStack, 5, 41, false)) {
            // If cannot move, no longer quick move
            return ItemStack.EMPTY;
        }

        if (rawStack.isEmpty()) {
            // If the raw stack has completely moved out of the slot, set the slot to the empty stack
            quickMovedSlot.set(ItemStack.EMPTY);
        } else {
            // Otherwise, notify the slot that that the stack count has changed
            quickMovedSlot.setChanged();
        }

        /*
        The following if statement and Slot#onTake call can be removed if the
        menu does not represent a container that can transform stacks (e.g.
        chests).
        */
        if (rawStack.getCount() == quickMovedStack.getCount()) {
            // If the raw stack was not able to be moved to another slot, no longer quick move
            return ItemStack.EMPTY;
        }
        // Execute logic on what to do post move with the remaining stack
        quickMovedSlot.onTake(player, rawStack);
        }

        return quickMovedStack; // Return the slot stack
    }
}
