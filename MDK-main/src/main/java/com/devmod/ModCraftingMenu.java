package com.devmod;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;

public class ModCraftingMenu extends CraftingMenu {

    public ModCraftingMenu(int pContainerId, Inventory pPlayerInventory) {
        super(pContainerId, pPlayerInventory);
    }

    public ModCraftingMenu(int pContainerId, Inventory pPlayerInventory, ContainerLevelAccess containerLevelAccess) {
        super(pContainerId, pPlayerInventory, containerLevelAccess);
    }

    @Override
    public void slotsChanged(Container pInventory) {
        super.slotsChanged(pInventory);
    }

    @Override
    public void beginPlacingRecipe() {
        super.beginPlacingRecipe();
    }

    @Override
    public void finishPlacingRecipe(RecipeHolder<CraftingRecipe> p_345915_) {
        super.finishPlacingRecipe(p_345915_);
    }

    @Override
    public void fillCraftSlotsStackedContents(StackedContents pItemHelper) {
        super.fillCraftSlotsStackedContents(pItemHelper);
    }

    @Override
    public void clearCraftingContent() {
        super.clearCraftingContent();
    }

    @Override
    public boolean recipeMatches(RecipeHolder<CraftingRecipe> pRecipe) {
        return super.recipeMatches(pRecipe);
    }

    @Override
    public void removed(Player pPlayer) {
        super.removed(pPlayer);
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return super.stillValid(pPlayer);
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        return super.quickMoveStack(pPlayer, pIndex);
    }

    @Override
    public boolean canTakeItemForPickAll(ItemStack pStack, Slot pSlot) {
        return super.canTakeItemForPickAll(pStack, pSlot);
    }

    @Override
    public int getResultSlotIndex() {
        return super.getResultSlotIndex();
    }

    @Override
    public int getGridWidth() {
        return super.getGridWidth();
    }

    @Override
    public int getGridHeight() {
        return super.getGridHeight();
    }

    @Override
    public int getSize() {
        return super.getSize();
    }

    @Override
    public RecipeBookType getRecipeBookType() {
        return super.getRecipeBookType();
    }

    @Override
    public boolean shouldMoveToInventory(int pSlotIndex) {
        return super.shouldMoveToInventory(pSlotIndex);
    }

    @Override
    public Slot addSlot(Slot pSlot) {
        return super.addSlot(pSlot);
    }
}
