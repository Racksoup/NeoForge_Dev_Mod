package com.devmod.ui;

import com.devmod.menus.ModDeathBoxMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ModDeathBoxScreen extends AbstractContainerScreen<ModDeathBoxMenu> {
    private static final ResourceLocation CONTAINER_BACKGROUND = ResourceLocation.fromNamespaceAndPath("minecraft", "textures/gui/container/generic_54.png");
    private int containerRows = 6;

    public ModDeathBoxScreen(ModDeathBoxMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component);
    }

    @Override
    protected void init() {
        super.init();
        this.titleLabelX = 8;
        this.inventoryLabelX = 8;
        this.inventoryLabelY = 18 * (this.containerRows + 1) + 2;
        this.topPos = (this.height - this.imageHeight) / 4 - 6;
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        this.renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        int i = (this.width - this.imageWidth) / 2;
        pGuiGraphics.blit(CONTAINER_BACKGROUND, i, topPos, 0, 0, this.imageWidth, this.containerRows * 18 + 17);
        pGuiGraphics.blit(CONTAINER_BACKGROUND, i, topPos + this.containerRows * 18 + 17, 0, 126, this.imageWidth, 96);
    }
}
