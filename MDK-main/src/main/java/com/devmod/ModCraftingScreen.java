package com.devmod;

import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.CraftingScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.CraftingMenu;


public class ModCraftingScreen extends CraftingScreen {
    private ModCraftingScreenButton modButton;
    private ImageButton imageButton;

    public ModCraftingScreen(CraftingMenu pMenu, Inventory pPlayerInventory) {
        super(pMenu, pPlayerInventory, Component.literal("ModCrafting"));
        DevMod.LOGGER.info("ModCraftingScreen Constructor");
    }

    @Override
    protected void init() {
        DevMod.LOGGER.info("ModCraftingScreen init");
        super.init();

        for (var widget : this.renderables) {
            if (widget instanceof ImageButton) {
                imageButton = (ImageButton) widget;
                break;
            }
        }
        this.renderables.remove(imageButton);
        this.children().remove(imageButton);

        int x = this.leftPos;
        int y = this.topPos;
        modButton = new ModCraftingScreenButton(x + 5, y + 34, 20, 18);
        this.addRenderableWidget(modButton);
    }

    @Override
    public void containerTick() {
        super.containerTick();
    }

//    @Override
//    public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
//        boolean result = super.mouseClicked(mouseX, mouseY, mouseButton);
//
//        if (this.getRecipeBookComponent().mouseClicked(mouseX, mouseY, mouseButton)) {
//            DevMod.LOGGER.info("Recipe book interaction detected!");
//            return true;
//        }
//
//        return result;
//    }

    private void updateButtonPosition() {
        int xPos = this.leftPos;
        int yPos = this.topPos;

        this.modButton.setX(xPos + 10);
        this.modButton.setY(yPos + 10);
    }
}
