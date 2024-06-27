package com.devmod;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.CraftingScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.CraftingMenu;

public class ModCraftingScreen extends CraftingScreen {
    private Button customButton;

    public ModCraftingScreen(CraftingMenu pMenu, Inventory pPlayerInventory) {
        super(pMenu, pPlayerInventory, Component.literal("ModCrafting"));
        DevMod.LOGGER.info("ModCraftingScreen Constructor");
    }

    @Override
    protected void init() {
        DevMod.LOGGER.info("ModCraftingScreen init");
        super.init();
        int x = this.leftPos + 10;
        int y = this.topPos + 10;
        customButton = Button.builder(Component.literal("Ride The Lightning!"), button -> {
            DevMod.LOGGER.info("Button Clicked!");
        })
            .bounds(x, y, 100, 20)
            .build();
        this.addRenderableWidget(customButton);
    }

}
