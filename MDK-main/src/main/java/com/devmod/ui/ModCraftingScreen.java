package com.devmod.ui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeUpdateListener;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;

import java.util.List;

public class ModCraftingScreen extends AbstractContainerScreen<ModCraftingMenu> implements RecipeUpdateListener {
    private ModCraftingScreenButton modButton;
    private ImageButton recipeButton;
    private static boolean bookOpen = false;
    private boolean wasBookOpen = false;
    private ModTalents modTalents;
    private RecipeBookComponent recipeBookComponent;
    private static final ResourceLocation BG_TEXTURE = ResourceLocation.fromNamespaceAndPath("minecraft", "textures/gui/container/crafting_table.png");
    private static String bookType = "";

    public ModCraftingScreen(ModCraftingMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        recipeBookComponent = new RecipeBookComponent();
    }

    @Override
    protected void init() {
        super.init();
        modTalents = new ModTalents(leftPos, topPos, getMinecraft());

        recipeBookComponent.init(width, height, getMinecraft(), false, getMenu());
        recipeButton = new ImageButton(leftPos + 5, height / 2 - 59, 20, 18, RecipeBookComponent.RECIPE_BUTTON_SPRITES, p_313433_ -> {
            recipeBookComponent.toggleVisibility();
            if (bookOpen) {
                leftPos = (width - imageWidth) /2;
                bookOpen = false;
                bookType = "";
            } else {
                leftPos = 177 + (width - imageWidth - 200) / 2;
                bookOpen = true;
                bookType = "recipe";
            }
        });

        modButton = new ModCraftingScreenButton(leftPos + 5, topPos + 44, 20, 18,
                button -> {
                    if (bookOpen) {
                        leftPos = (width - imageWidth) /2;
                        bookOpen = false;
                        bookType = "";
                    } else {
                        leftPos = 177 + (width - imageWidth - 200) / 2;
                        bookOpen = true;
                        bookType = "talents";
                    }
                });
        addRenderableWidget(recipeButton);
        addRenderableWidget(modButton);
        addWidget(recipeBookComponent);
        addModTalentsWidgets(modTalents);

        toggleBook();
    }

    @Override
    public void containerTick() {
        super.containerTick();
        this.recipeBookComponent.tick();
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        if (recipeBookComponent.isVisible()) {
            leftPos = 177 + (width - imageWidth - 200) / 2;
            bookOpen = true;
        }

        if (bookOpen != wasBookOpen) {
            toggleBook();
            wasBookOpen = bookOpen;
            updatePosition();
        }

        modTalents.render(pGuiGraphics, pMouseX, pMouseY);
        recipeBookComponent.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        recipeBookComponent.renderGhostRecipe(pGuiGraphics, leftPos, topPos, true, pPartialTick);
        renderTooltip(pGuiGraphics, pMouseX, pMouseY);
        recipeBookComponent.renderTooltip(pGuiGraphics, leftPos, topPos, pMouseX, pMouseY);
    }

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        pGuiGraphics.blit(BG_TEXTURE, leftPos, topPos, 0, 0, imageWidth, imageHeight);
    }

    @Override
    public boolean keyPressed(int pKeyCode, int pScanCode, int pModifiers) {
        return recipeBookComponent.keyPressed(pKeyCode, pScanCode, pModifiers) ? true : super.keyPressed(pKeyCode, pScanCode, pModifiers);
    }

    @Override
    public boolean charTyped(char pCodePoint, int pModifiers) {
        return recipeBookComponent.charTyped(pCodePoint, pModifiers) ? true : super.charTyped(pCodePoint, pModifiers);
    }

    @Override
    protected boolean isHovering(int pX, int pY, int pWidth, int pHeight, double pMouseX, double pMouseY) {
        return  super.isHovering(pX, pY, pWidth, pHeight, pMouseX, pMouseY);
    }

    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        if (recipeBookComponent.mouseClicked(pMouseX, pMouseY, pButton)) {
            setFocused(recipeBookComponent);
            return true;
        } else {
            return super.mouseClicked(pMouseX, pMouseY, pButton);
        }
    }

    @Override
    protected void slotClicked(Slot pSlot, int pSlotId, int pMouseButton, ClickType pType) {
        super.slotClicked(pSlot, pSlotId, pMouseButton, pType);
        recipeBookComponent.slotClicked(pSlot);
    }

    @Override
    public void recipesUpdated() {
        recipeBookComponent.recipesUpdated();
    }

    @Override
    public RecipeBookComponent getRecipeBookComponent() {
        return recipeBookComponent;
    }

    private void updatePosition() {
        int x = leftPos;
        int y = topPos;

        modButton.setX(x + 5);
        modButton.setY(y + 44);
        recipeButton.setX(x + 5);
        recipeButton.setY(height / 2 - 59);


        modTalents.updatePos(x, y);
    }

    private void addModTalentsWidgets(ModTalents modTalents) {
        List<AbstractWidget> widgets = modTalents.getAllWidgets();
        for (AbstractWidget widget : widgets) {
            addRenderableWidget(widget);
        }
    }

    private void toggleBook() {
        List<AbstractWidget> widgets = modTalents.getAllWidgets();
        for (AbstractWidget widget : widgets) {
            widget.visible = bookOpen;
        }
        if (bookOpen) {
            modTalents.tabSelect();
        }
    }

    public static String getBookType() {
        return bookType;
    }
}
