package com.devmod.items.tiers;

import com.devmod.DevMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModTierShaman implements Tier {
    private final int uses;
    private final float speed;
    private final float attackDamageBonus;
    private final int enchantmentValue;
    private final Ingredient repairIngredient;

    public ModTierShaman() {
        this.uses = 1000;
        this.speed = 1.0f;
        this.attackDamageBonus = 0;
        this.enchantmentValue = 100;
        this.repairIngredient = Ingredient.of(ItemTags.create(ResourceLocation.fromNamespaceAndPath(DevMod.MODID, "turtle_shell")));
    }

    @Override
    public int getUses() {
        return uses;
    }

    @Override
    public float getSpeed() {
        return speed;
    }

    @Override
    public float getAttackDamageBonus() {
        return attackDamageBonus;
    }

    @Override
    public TagKey<Block> getIncorrectBlocksForDrops() {
        return null;
    }

    @Override
    public int getEnchantmentValue() {
        return enchantmentValue;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairIngredient;
    }
}
