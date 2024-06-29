package com.devmod;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.CraftingScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.CraftingMenu;

import java.util.List;


public class ModCraftingScreen extends CraftingScreen {
    private ModCraftingScreenButton modButton;
    private ImageButton imageButton;
    private boolean bookOpen = false;
    private boolean wasBookOpen = false;
    private ModTalents modTalents;

    public ModCraftingScreen(CraftingMenu pMenu, Inventory pPlayerInventory) {
        super(pMenu, pPlayerInventory, Component.literal("ModCrafting"));
    }

    @Override
    protected void init() {
        super.init();

        this.modTalents = new ModTalents(this, this.leftPos, this.topPos);

        for (var widget : this.renderables) {
            if (widget instanceof ImageButton) {
                imageButton = (ImageButton) widget;
                break;
            }
        }
        this.renderables.remove(imageButton);
        this.children().remove(imageButton);
        toggleBook();

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
        addModTalentsWidgets(modTalents);
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        if (bookOpen != wasBookOpen) {
            toggleBook();
            wasBookOpen = bookOpen;
            updatePosition();
        }
    }

    private void updatePosition() {
        int x = this.leftPos;
        int y = this.topPos;

        this.modButton.setX(x + 5);
        this.modButton.setY(y + 34);

        modTalents.warButton.setPosition(x - modTalents.texture.getWidth() / 2 - 10, y -20);
        modTalents.shamButton.setPosition((int) (x - modTalents.texture.getWidth() / 2.0f * (2.0f/3.0f) - 10), y -1);
        modTalents.mageButton.setPosition((int) (x - modTalents.texture.getWidth() / 2.0f * (1.0f/3.0f) - 10), y -1);
        modTalents.texture.setPosition(x - modTalents.texture.getWidth() / 2 - 22, y -1);
    }

    private void addModTalentsWidgets(ModTalents modTalents) {
        List<AbstractWidget> widgets = modTalents.getAllWidgets();
        for (AbstractWidget widget : widgets) {
            this.addRenderableWidget(widget);
        }
    }

    private void toggleBook() {
        List<AbstractWidget> widgets = modTalents.getAllWidgets();
        for (AbstractWidget widget : widgets) {
            widget.visible = bookOpen;
        }
    }
}
