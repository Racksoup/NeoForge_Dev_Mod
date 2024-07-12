package com.devmod.ui;

import com.devmod.DevMod;
import com.devmod.data.RoleData;
import com.devmod.utils.talents.shaman.IncreaseMoveSpeed;
import com.devmod.utils.talents.warrior.MeleeRangeIncrease;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageWidget;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class ModTalents {
    private static final ResourceLocation CONTAINER_BACKGROUND = ResourceLocation.withDefaultNamespace("textures/gui/recipe_book.png");
    public ImageWidget texture;
    public Button warButton;
    public Button shamButton;
    public Button mageButton;
    public Font font;
    private Minecraft minecraft;

    public WarTalents warTalents;
    public ShamTalents shamTalents;
    public MageTalents mageTalents;

    public ModTalents(int leftPos, int topPos, Minecraft mc) {
        this.minecraft = mc;
        this.font = mc.font;

        warTalents = new WarTalents();
        shamTalents = new ShamTalents();
        mageTalents = new MageTalents();

        texture = ImageWidget.texture(256, 256, CONTAINER_BACKGROUND, 256, 256);

        warButton = Button.builder(Component.literal("Warrior"), button -> {
            DevMod.LOGGER.info("Warrior Button clicked");
            RoleData.setCurrentClass("warrior");
            tabSelect();
            RoleData.setTalents();
        }).bounds(leftPos, topPos, 41, 14).build();

        shamButton = Button.builder(Component.literal("Shaman"), button -> {
            DevMod.LOGGER.info("Shaman Button clicked");
            RoleData.setCurrentClass("shaman");
            tabSelect();
            RoleData.setTalents();
        }).bounds(leftPos, topPos, 41, 14).build();

        mageButton = Button.builder(Component.literal("Mage"), button -> {
            DevMod.LOGGER.info("Mage Button clicked");
            RoleData.setCurrentClass("mage");
            tabSelect();
            RoleData.setTalents();
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

        // Render tooltips
        if (ModCraftingScreen.getBookType().equals("talents")) {
            if (warButton.isHovered()) {
                renderTooltip(pGuiGraphics, pMouseX, pMouseY, Arrays.asList("Warrior talents"));
            }
            if (shamButton.isHovered()) {
                renderTooltip(pGuiGraphics, pMouseX, pMouseY, Arrays.asList("Shaman talents"));
            }
            if (mageButton.isHovered()) {
                renderTooltip(pGuiGraphics, pMouseX, pMouseY, Arrays.asList("Mage talents"));
            }
            if (warTalents.t1.isHovered()) {
                renderTooltip(pGuiGraphics, pMouseX, pMouseY, Arrays.asList("+1 Axe Attack Range"));
            }
            if (shamTalents.t1.isHovered()) {
                renderTooltip(pGuiGraphics, pMouseX, pMouseY, Arrays.asList("%10 Move Speed"));
            }
            if (mageTalents.t1.isHovered()) {
                renderTooltip(pGuiGraphics, pMouseX, pMouseY, Arrays.asList("%5 Chance To Stun On Hit"));
            }
        }
    }

    private void renderTooltip(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, List<String> tooltips) {
        List<Component> tooltipComponents = tooltips.stream().map(Component::literal).collect(Collectors.toList());
        pGuiGraphics.renderComponentTooltip(font, tooltipComponents, pMouseX, pMouseY);
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

        if (RoleData.getCurrentClass() != null) {
            if (RoleData.getCurrentClass().equals("warrior")) {
                warTalents.show();
            }
            if (RoleData.getCurrentClass().equals("shaman")) {
                shamTalents.show();
            }
            if (RoleData.getCurrentClass().equals("mage")) {
                mageTalents.show();
            }
        }
    }

    public static class WarTalents {
        public ModTalentButton t1;

        public WarTalents() {
            t1 = new ModTalentButton(16, 16,
                    button -> {
                        RoleData.Warrior.setT1(!RoleData.Warrior.getT1());
                        if (RoleData.Warrior.getT1()) {
                            MeleeRangeIncrease.setEffect(RoleData.Warrior.attackRangeLevel1);
                        } else {
                            MeleeRangeIncrease.setEffect(RoleData.Warrior.attackRangeDefault);
                        }
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
                        RoleData.Shaman.setT1(!RoleData.Shaman.getT1());
                        if (RoleData.Shaman.getT1()) {
                            IncreaseMoveSpeed.setEffect(RoleData.Shaman.moveSpeedLevel1);
                        } else {
                            IncreaseMoveSpeed.setEffect(RoleData.Shaman.moveSpeedDefault);
                        }
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
                        RoleData.Mage.setT1(!RoleData.Mage.getT1());
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
