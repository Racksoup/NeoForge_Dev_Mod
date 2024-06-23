package com.devmod;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class ModInventoryScreen extends InventoryScreen {

    private Button myButton;

    public ModInventoryScreen(Player player) {
        super(player);
    }

    @Override
    protected void init() {
        super.init();

        this.myButton = Button.builder(Component.literal("ff"), button -> {
            DevMod.LOGGER.info("ffffff");
        }).build();

        this.addRenderableWidget(this.myButton);
    }
}
