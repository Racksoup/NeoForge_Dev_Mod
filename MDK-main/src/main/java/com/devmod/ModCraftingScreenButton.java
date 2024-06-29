package com.devmod;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;


public class ModCraftingScreenButton extends Button {
    private static final ResourceLocation BUTTON_TEXTURE = ResourceLocation.fromNamespaceAndPath(DevMod.MODID, "textures/buttons/mod_crafting_screen_button.png");

    public ModCraftingScreenButton(int x, int y, int width, int height, Button.OnPress onPress ) {
        super(x, y, width, height, Component.empty(), onPress, Button.DEFAULT_NARRATION);
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        if (isMouseOver(mouseX, mouseY)) {
            guiGraphics.blit(BUTTON_TEXTURE, getX(), getY(),0, 19, getWidth(), getHeight());
        } else {
            guiGraphics.blit(BUTTON_TEXTURE, getX(), getY(),0, 0, getWidth(), getHeight());
        }
    }
}
