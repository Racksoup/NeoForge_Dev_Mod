package com.devmod;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookPage;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;

import java.util.List;


public class ModCraftingScreen extends AbstractContainerScreen<ModCraftingMenu> {
    private ModCraftingScreenButton modButton;
    private boolean bookOpen = false;
    private boolean wasBookOpen = false;
    private ModTalents modTalents;
    private RecipeBookPage recipeBookPage;
    private static final ResourceLocation BG_TEXTURE = ResourceLocation.fromNamespaceAndPath("minecraft", "textures/gui/container/crafting_table.png");

    public ModCraftingScreen(ModCraftingMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
        this.modTalents = new ModTalents(this.leftPos, this.topPos);

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
        pGuiGraphics.blit(BG_TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
    }

    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        return super.mouseClicked(pMouseX, pMouseY, pButton);
    }

    @Override
    protected void slotClicked(Slot pSlot, int pSlotId, int pMouseButton, ClickType pType) {
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
