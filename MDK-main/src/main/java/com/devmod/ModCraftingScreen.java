package com.devmod;

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
    private boolean bookOpen = false;
    private boolean wasBookOpen = false;
    private ModTalents modTalents;
    private RecipeBookComponent recipeBookComponent;
    private static final ResourceLocation BG_TEXTURE = ResourceLocation.fromNamespaceAndPath("minecraft", "textures/gui/container/crafting_table.png");

    public ModCraftingScreen(ModCraftingMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        this.recipeBookComponent = new RecipeBookComponent();
    }

    @Override
    protected void init() {
        super.init();
        this.modTalents = new ModTalents(this.leftPos, this.topPos);

        this.recipeBookComponent.init(this.width, this.height, getMinecraft(), false, this.getMenu());
        recipeButton = new ImageButton(this.leftPos + 5, this.height / 2 - 59, 20, 18, RecipeBookComponent.RECIPE_BUTTON_SPRITES, p_313433_ -> {
            this.recipeBookComponent.toggleVisibility();
            if (bookOpen) {
                leftPos = (this.width - this.imageWidth) /2;
                bookOpen = false;
            } else {
                leftPos = 177 + (this.width - this.imageWidth - 200) / 2;
                bookOpen = true;
            }
        });
        this.addWidget(this.recipeBookComponent);

        modButton = new ModCraftingScreenButton(this.leftPos + 5, this.topPos + 44, 20, 18,
                button -> {
                    if (bookOpen) {
                        leftPos = (this.width - this.imageWidth) /2;
                        bookOpen = false;
                    } else {
                        leftPos = 177 + (this.width - this.imageWidth - 200) / 2;
                        bookOpen = true;
                    }
                });
        this.addRenderableWidget(recipeButton);
        this.addRenderableWidget(modButton);
        this.addWidget(recipeBookComponent);
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
            leftPos = 177 + (this.width - this.imageWidth - 200) / 2;
            bookOpen = true;
        }

        if (bookOpen != wasBookOpen) {
            toggleBook();
            wasBookOpen = bookOpen;
            updatePosition();
        }

        this.modTalents.render(pGuiGraphics, pMouseX, pMouseY);
        this.recipeBookComponent.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        this.recipeBookComponent.renderGhostRecipe(pGuiGraphics, this.leftPos, this.topPos, true, pPartialTick);
        this.renderTooltip(pGuiGraphics, pMouseX, pMouseY);
        this.recipeBookComponent.renderTooltip(pGuiGraphics, this.leftPos, this.topPos, pMouseX, pMouseY);
    }

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        pGuiGraphics.blit(BG_TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
    }

    @Override
    public boolean keyPressed(int pKeyCode, int pScanCode, int pModifiers) {
        return this.recipeBookComponent.keyPressed(pKeyCode, pScanCode, pModifiers) ? true : super.keyPressed(pKeyCode, pScanCode, pModifiers);
    }

    @Override
    public boolean charTyped(char pCodePoint, int pModifiers) {
        return this.recipeBookComponent.charTyped(pCodePoint, pModifiers) ? true : super.charTyped(pCodePoint, pModifiers);
    }

    @Override
    protected boolean isHovering(int pX, int pY, int pWidth, int pHeight, double pMouseX, double pMouseY) {
        return  super.isHovering(pX, pY, pWidth, pHeight, pMouseX, pMouseY);
    }

    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        if (this.recipeBookComponent.mouseClicked(pMouseX, pMouseY, pButton)) {
            this.setFocused(this.recipeBookComponent);
            return true;
        } else {
            return super.mouseClicked(pMouseX, pMouseY, pButton);
        }
    }

    @Override
    protected void slotClicked(Slot pSlot, int pSlotId, int pMouseButton, ClickType pType) {
        super.slotClicked(pSlot, pSlotId, pMouseButton, pType);
        this.recipeBookComponent.slotClicked(pSlot);
    }

    @Override
    public void recipesUpdated() {
        this.recipeBookComponent.recipesUpdated();
    }

    @Override
    public RecipeBookComponent getRecipeBookComponent() {
        return this.recipeBookComponent;
    }

    private void updatePosition() {
        int x = this.leftPos;
        int y = this.topPos;

        this.modButton.setX(x + 5);
        this.modButton.setY(y + 44);
        this.recipeButton.setX(x + 5);
        this.recipeButton.setY(this.height / 2 - 59);


        modTalents.updatePos(x, y);
    }

    private void addModTalentsWidgets(ModTalents modTalents) {
        List<AbstractWidget> widgets = modTalents.getAllWidgets();
        for (AbstractWidget widget : widgets) {
            this.addRenderableWidget(widget);
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
}
