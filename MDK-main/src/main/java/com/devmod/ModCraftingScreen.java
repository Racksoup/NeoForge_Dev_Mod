package com.devmod;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class ModCraftingScreen extends InventoryScreen {
    private Button customButton;

    public ModCraftingScreen(Player player) {
        super(player);
    }

    @Override
    protected void init() {
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
