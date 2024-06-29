package com.devmod;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.CraftingScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.CraftingMenu;


public class ModCraftingScreen extends CraftingScreen {
    private ModCraftingScreenButton modButton;
    private ImageButton imageButton;
    private boolean bookOpen = false;
    private boolean wasBookOpen = false;

    public ModCraftingScreen(CraftingMenu pMenu, Inventory pPlayerInventory) {
        super(pMenu, pPlayerInventory, Component.literal("ModCrafting"));
    }

    @Override
    protected void init() {
        super.init();

        for (var widget : this.renderables) {
            if (widget instanceof ImageButton) {
                imageButton = (ImageButton) widget;
                break;
            }
        }
        this.renderables.remove(imageButton);
        this.children().remove(imageButton);

        modButton = new ModCraftingScreenButton(this.leftPos + 5, this.topPos + 34, 20, 18,
                button -> {
                    if (bookOpen) {
                        leftPos = (this.width - this.imageWidth) /2;
                        bookOpen = false;
                    } else {
                        leftPos = 177 + (this.width - this.imageWidth - 200) / 2;
                        bookOpen = true;
                    }
                });
        this.addRenderableWidget(modButton);
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        if (bookOpen != wasBookOpen) {
            wasBookOpen = bookOpen;
            updateButtonPosition();
        }
    }

    private void updateButtonPosition() {
        int xPos = this.leftPos;
        int yPos = this.topPos;

        this.modButton.setX(xPos + 5);
        this.modButton.setY(yPos + 34);
    }
}
