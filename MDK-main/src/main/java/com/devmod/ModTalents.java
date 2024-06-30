package com.devmod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
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

    public WarTalents warTalents;
    public ShamTalents shamTalents;
    public MageTalents mageTalents;

    public ModTalents(int leftPos, int topPos) {
        this.warTalents = new WarTalents();
        this.shamTalents = new ShamTalents();
        this.mageTalents = new MageTalents();

        Minecraft.getInstance().getTextureManager().bindForSetup(CONTAINER_BACKGROUND);

        texture = ImageWidget.texture(256, 256, CONTAINER_BACKGROUND, 256, 256);

        warButton = Button.builder(Component.literal("Warrior"), button -> {
            DevMod.LOGGER.info("Warrior Button clicked");
            DevMod.roleData.currentClass = "warrior";
            tabSelect();
        }).bounds(leftPos, topPos, 41, 14).build();

        shamButton = Button.builder(Component.literal("Shaman"), button -> {
            DevMod.LOGGER.info("Shaman Button clicked");
            DevMod.roleData.currentClass = "shaman";
            tabSelect();
        }).bounds(leftPos, topPos, 41, 14).build();

        mageButton = Button.builder(Component.literal("Mage"), button -> {
            DevMod.LOGGER.info("Mage Button clicked");
            DevMod.roleData.currentClass = "mage";
            tabSelect();
        }).bounds(leftPos, topPos, 40, 14).build();
    }

    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY) {
        texture.render(pGuiGraphics, pMouseX, pMouseY, 0);
        warButton.render(pGuiGraphics, pMouseX, pMouseY, 0);
        shamButton.render(pGuiGraphics, pMouseX, pMouseY, 0);
        mageButton.render(pGuiGraphics, pMouseX, pMouseY, 0);
        warTalents.t1.render(pGuiGraphics, pMouseX, pMouseY, 0);
        shamTalents.t1.render(pGuiGraphics, pMouseX, pMouseY, 0);
        mageTalents.t1.render(pGuiGraphics, pMouseX, pMouseY, 0);
    }

    public List<AbstractWidget> getAllWidgets() {
        List<AbstractWidget> widgets = new ArrayList<>();

        widgets.add(warTalents.t1);
        widgets.add(shamTalents.t1);
        widgets.add(mageTalents.t1);
        widgets.add(warButton);
        widgets.add(shamButton);
        widgets.add(mageButton);
        widgets.add(texture);

        return widgets;
    }

    public void updatePos(int x, int y) {
        int bottom = y + texture.getHeight() / 2 + 28;
        int top = y + 7;
        int left = x - texture.getWidth() / 2 - 14;
        int right = x - 11;
        int centerX = left + (right - left) / 2;
        int centerY = top + (bottom - top) / 2;

        warTalents.t1.setPosition(centerX - warTalents.t1.getWidth() / 2, bottom - warTalents.t1.getHeight() - 2);
        shamTalents.t1.setPosition(centerX - shamTalents.t1.getWidth() / 2, bottom - shamTalents.t1.getHeight() - 2);
        mageTalents.t1.setPosition(centerX - mageTalents.t1.getWidth() / 2, bottom - mageTalents.t1.getHeight() - 2);
        warButton.setPosition(x - texture.getWidth() / 2 - 12, y +11);
        shamButton.setPosition((int) (x - texture.getWidth() / 2.0f * (2.0f/3.0f) - 10), y +11);
        mageButton.setPosition((int) (x - texture.getWidth() / 2.0f * (1.0f/3.0f) - 9), y +11);
        texture.setPosition(x - texture.getWidth() / 2 - 22, y -1);
    }

    public void tabSelect() {
        warTalents.hide();
        shamTalents.hide();
        mageTalents.hide();

        if (DevMod.roleData.currentClass.equals("warrior")) {
            warTalents.show();
        }
        if (DevMod.roleData.currentClass.equals("shaman")) {
            shamTalents.show();
        }
        if (DevMod.roleData.currentClass.equals("mage")) {
            mageTalents.show();
        }
    }

    public static class WarTalents {
        public ModTalentButton t1;

        public WarTalents() {
            t1 = new ModTalentButton(16, 16,
                    button -> {
                        DevMod.LOGGER.info("WarTalents Button clicked");
                    }, 38, 0);
        }

        public List<AbstractWidget> getAllWidgets() {
            List<AbstractWidget> widgets = new ArrayList<>();

            widgets.add(t1);

            return widgets;
        }

        public void show() {
            List<AbstractWidget> widgets = getAllWidgets();
            for (AbstractWidget widget : widgets) {
                widget.visible = true;
            }
        }

        public void hide() {
            List<AbstractWidget> widgets = getAllWidgets();
            for (AbstractWidget widget : widgets) {
                widget.visible = false;
            }
        }
    }

    public static class ShamTalents {
        public ModTalentButton t1;

        public ShamTalents() {
            t1 = new ModTalentButton(16, 16,
                    button -> {
                        DevMod.LOGGER.info("Sham Talents Button clicked");
                    }, 55, 0);
        }

        public List<AbstractWidget> getAllWidgets() {
            List<AbstractWidget> widgets = new ArrayList<>();

            widgets.add(t1);

            return widgets;
        }

        public void show() {
            List<AbstractWidget> widgets = getAllWidgets();
            for (AbstractWidget widget : widgets) {
                widget.visible = true;
            }
        }

        public void hide() {
            List<AbstractWidget> widgets = getAllWidgets();
            for (AbstractWidget widget : widgets) {
                widget.visible = false;
            }
        }
    }

    public static class MageTalents {
        public ModTalentButton t1;

        public MageTalents() {
            t1 = new ModTalentButton(16, 16,
                    button -> {
                        DevMod.LOGGER.info("Mage Talents Button clicked");
                    }, 72, 0);
        }

        public List<AbstractWidget> getAllWidgets() {
            List<AbstractWidget> widgets = new ArrayList<>();

            widgets.add(t1);

            return widgets;
        }

        public void show() {
            List<AbstractWidget> widgets = getAllWidgets();
            for (AbstractWidget widget : widgets) {
                widget.visible = true;
            }
        }

        public void hide() {
            List<AbstractWidget> widgets = getAllWidgets();
            for (AbstractWidget widget : widgets) {
                widget.visible = false;
            }
        }
    }
}
