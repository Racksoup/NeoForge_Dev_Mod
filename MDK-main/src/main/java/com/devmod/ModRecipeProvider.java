package com.devmod;

import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void buildRecipes(RecipeOutput output) {
        DevMod.LOGGER.info("Registering recipes for ModCraftingTable.");
        DevMod.LOGGER.info("Registering recipes for ModCraftingTable.");
        DevMod.LOGGER.info("Registering recipes for ModCraftingTable.");
        DevMod.LOGGER.info("Registering recipes for ModCraftingTable.");
        DevMod.LOGGER.info("Registering recipes for ModCraftingTable.");
        DevMod.LOGGER.info("Registering recipes for ModCraftingTable.");
        DevMod.LOGGER.info("Registering recipes for ModCraftingTable.");
        DevMod.LOGGER.info("Registering recipes for ModCraftingTable.");
        DevMod.LOGGER.info("Registering recipes for ModCraftingTable.");
        DevMod.LOGGER.info("Registering recipes for ModCraftingTable.");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.MY_CRAFTING_TABLE_BLOCK.get())
                .pattern("a  ")
                .pattern("a  ")
                .pattern("a  ")
                .define('a', ModItems.URANIUM_ITEM.get())
                .unlockedBy("has_uranium", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.URANIUM_ITEM.get()))
                .save(output);
    }
}

