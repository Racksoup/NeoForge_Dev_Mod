package com.devmod.ui;

import com.devmod.DevMod;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class ModTalentButton extends Button {
    private static final ResourceLocation BUTTON_TEXTURE = ResourceLocation.fromNamespaceAndPath(DevMod.MODID, "textures/buttons/mod_buttons.png");
    private int uOff;
    private int vOff;

    public ModTalentButton(int width, int height, OnPress onPress, int uOff, int vOff ) {
        super(0, 0, width, height, Component.empty(), onPress, Button.DEFAULT_NARRATION);
        this.uOff = uOff;
        this.vOff = vOff;
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        if (isMouseOver(mouseX, mouseY)) {
            guiGraphics.blit(BUTTON_TEXTURE, getX(), getY(),uOff, vOff +17, getWidth(), getHeight());
        } else {
            guiGraphics.blit(BUTTON_TEXTURE, getX(), getY(),uOff, vOff, getWidth(), getHeight());
        }
    }
}
