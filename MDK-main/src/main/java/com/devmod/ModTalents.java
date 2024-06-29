package com.devmod;

import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageWidget;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

public class ModTalents {
    private static final ResourceLocation CONTAINER_BACKGROUND = ResourceLocation.withDefaultNamespace("textures/gui/recipe_book.png");
    public ImageWidget texture;
    public Button warButton;
    public Button shamButton;
    public Button mageButton;

    public ModTalents(ModCraftingScreen parent, int leftPos, int topPos) {
        texture = ImageWidget.texture(256, 256, CONTAINER_BACKGROUND, 256, 256);

        Button.CreateNarration createNarration = messageSupplier -> {
            return Component.literal("Button clicked: ").append(messageSupplier.get());
        };

        warButton = Button.builder(Component.literal("Warrior"), button -> {
            DevMod.LOGGER.info("Warrior Button clicked");
            DevMod.roleData.currentClass = "warrior";
        }).bounds(leftPos, topPos, 38, 12).build();

        shamButton = Button.builder(Component.literal("Shaman"), button -> {
            DevMod.LOGGER.info("Shaman Button clicked");
            DevMod.roleData.currentClass = "shaman";
        }).bounds(leftPos, topPos, 38, 12).build();

        mageButton = Button.builder(Component.literal("Mage"), button -> {
            DevMod.LOGGER.info("Mage Button clicked");
            DevMod.roleData.currentClass = "mage";
        }).bounds(leftPos, topPos, 38, 12).build();
    }

    public List<AbstractWidget> getAllWidgets() {
        List<AbstractWidget> widgets = new ArrayList<>();

        widgets.add(warButton);
        widgets.add(shamButton);
        widgets.add(mageButton);
        widgets.add(texture);

        return widgets;
    }
}
