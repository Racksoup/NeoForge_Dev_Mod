package com.devmod.ui;

import com.devmod.menus.ModDeathBoxMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ModDeathBoxScreen extends AbstractContainerScreen<ModDeathBoxMenu> {
    private static final ResourceLocation BG_TEXTURE = ResourceLocation.fromNamespaceAndPath("minecraft", "textures/gui/container/generic_54.png");

    public ModDeathBoxScreen(ModDeathBoxMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component);

        this.titleLabelX = 10;
        this.inventoryLabelX = 10;
    }

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        pGuiGraphics.blit(BG_TEXTURE, leftPos, topPos, 0, 0, imageWidth, imageHeight);
    }
}
