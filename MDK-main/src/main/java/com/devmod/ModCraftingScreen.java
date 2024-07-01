package com.devmod;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.CraftingScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.inventory.Slot;

import java.util.List;


public class ModCraftingScreen extends AbstractContainerScreen<ModCraftingMenu> {
    private ModCraftingScreenButton modButton;
    private ImageButton recipeBookButton;
    private boolean bookOpen = false;
    private boolean wasBookOpen = false;
    private ModTalents modTalents;

    public ModCraftingScreen(ModCraftingMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
        this.modTalents = new ModTalents(this.leftPos, this.topPos);

//        for (var widget : this.renderables) {
//            if (widget instanceof ImageButton) {
//                recipeBookButton = (ImageButton) widget;
//                break;
//            }
//        }
//        this.renderables.remove(recipeBookButton);
//        this.children().remove(recipeBookButton);

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

        toggleBook();
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        if (bookOpen != wasBookOpen) {
            toggleBook();
            wasBookOpen = bookOpen;
            updatePosition();
        }

        this.modTalents.render(pGuiGraphics, pMouseX, pMouseY);
    }

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {

    }

    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        return super.mouseClicked(pMouseX, pMouseY, pButton);
    }

    @Override
    protected void slotClicked(Slot pSlot, int pSlotId, int pMouseButton, ClickType pType) {
        DevMod.LOGGER.info("Slot clicked: " + pSlot + " " + pSlotId + " " + pType);
        if (pSlot != null && pSlot.hasItem()) {
            DevMod.LOGGER.info("Item in slot: " + pSlot.getItem());
        }
        super.slotClicked(pSlot, pSlotId, pMouseButton, pType);
    }

    private void updatePosition() {
        int x = this.leftPos;
        int y = this.topPos;

        this.modButton.setX(x + 5);
        this.modButton.setY(y + 34);

        modTalents.updatePos(x, y);
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
        if (bookOpen) {
            modTalents.tabSelect();
        }
    }
}
