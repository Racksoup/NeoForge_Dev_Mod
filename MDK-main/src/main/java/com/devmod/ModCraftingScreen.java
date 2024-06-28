package com.devmod;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.CraftingScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.CraftingMenu;


public class ModCraftingScreen extends CraftingScreen {
    private Button customButton;
    private boolean wasRecipeBookVisible;
    private ModCraftingScreenButton modButton;

    public ModCraftingScreen(CraftingMenu pMenu, Inventory pPlayerInventory) {
        super(pMenu, pPlayerInventory, Component.literal("ModCrafting"));
        DevMod.LOGGER.info("ModCraftingScreen Constructor");
    }

    @Override
    protected void init() {
        DevMod.LOGGER.info("ModCraftingScreen init");
        super.init();
        int x = this.leftPos;
        int y = this.topPos;
        customButton = Button.builder(Component.literal("Ride The Lightning!"), button -> {
            DevMod.LOGGER.info("Button Clicked!");
        })
            .bounds(x, y, 100, 20)
            .build();
        this.addRenderableWidget(customButton);

        modButton = new ModCraftingScreenButton(x + 10, y + 10, 20, 18);
        this.addRenderableWidget(modButton);
    }

    @Override
    public void containerTick() {
        super.containerTick();

        boolean isRecipeBookVisible = this.getRecipeBookComponent().isVisible();
        if (isRecipeBookVisible != this.wasRecipeBookVisible) {
            this.updateButtonPosition();
            this.wasRecipeBookVisible = isRecipeBookVisible;
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
        boolean result = super.mouseClicked(mouseX, mouseY, mouseButton);

        if (this.getRecipeBookComponent().mouseClicked(mouseX, mouseY, mouseButton)) {
            DevMod.LOGGER.info("Recipe book interaction detected!");
            return true;
        }

        return result;
    }

    private void updateButtonPosition() {
        int xPos = this.leftPos;
        int yPos = this.topPos;

        this.customButton.setX(xPos);
        this.customButton.setY(yPos);
    }
}
