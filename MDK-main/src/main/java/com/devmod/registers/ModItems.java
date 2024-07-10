package com.devmod.registers;

import com.devmod.DevMod;
import com.devmod.items.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModItems {
    public static final ModTierShaman tier = new ModTierShaman();

    // Items
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(DevMod.MODID);
    public static final Supplier<Item> MY_BLOCK_ITEM = ITEMS.register(
            "my_block",
            () -> new BlockItem(ModBlocks.MY_BLOCK.get(), new Item.Properties())
    );
    public static final Supplier<Item> MY_CRAFTING_TABLE_ITEM = ITEMS.register(
            "my_crafting_table",
            () -> new BlockItem(ModBlocks.MY_CRAFTING_TABLE_BLOCK.get(), new Item.Properties())
    );

    // Crafting Reagents
    public static final Supplier<Item> REAGENT_STABLE_WATER_ITEM = ITEMS.registerItem(
           "reagent_stable_water",
            Item::new,
            new Item.Properties()
    );
    public static final Supplier<Item> REAGENT_URANIUM_ITEM = ITEMS.registerItem(
            "reagent_uranium",
            Item::new,
            new Item.Properties()
    );
    public static final Supplier<Item> REAGENT_TURTLE_SHELL_ITEM = ITEMS.registerItem(
            "reagent_turtle_shell",
            Item::new,
            new Item.Properties()
    );

    // Weapons
    public static final Supplier<ModProjectileWeapon> WEAPON_TEMPERED_STAFF = ITEMS.register(
            "weapon_tempered_staff",
            () -> new ModProjectileWeapon(
                    new Item.Properties()
            )

    );
    public static final Supplier<ModSwordItem> WEAPON_NEO_BATTLE_AXE = ITEMS.register("weapon_neo_battle_axe", () -> new ModSwordItem(
            ModTier.COPPER_TIER,
            new Item.Properties().attributes(
                    SwordItem.createAttributes(
                            ModTier.COPPER_TIER,
                            22,
                            -3.2f
                            )
            )
    ));
    public static final Supplier<ModSwordItem> WEAPON_URSA_HEART = ITEMS.register("weapon_ursa_heart", () -> new ModSwordItem(
            tier,
            new Item.Properties().attributes(
                    SwordItem.createAttributes(
                            tier,
                            5,
                            0.0f
                    )
            )
    ));

    // Spells
    public static final Supplier<ModSpell> SPELL_CHARGE = ITEMS.registerItem(
            "spell_charge",
            ModSpell::new,
            new ModSpell.Properties()
    );
    public static final Supplier<ModSpell> SPELL_LEASH = ITEMS.registerItem(
            "spell_leash",
            ModSpell::new,
            new ModSpell.Properties()
    );
    public static final Supplier<ModSpell> SPELL_BLINK = ITEMS.registerItem(
            "spell_blink",
            ModSpell::new,
            new ModSpell.Properties()
    );

    // Armor
    public static final Supplier<ArmorItem> SHAMAN_HELMET = ITEMS.register("armor_shaman_helmet", () -> new ArmorItem(
            ArmorShamanTier.SHAMAN_ARMOR,
            ArmorItem.Type.HELMET,
            new Item.Properties().durability((ArmorItem.Type.HELMET.getDurability(15))
    )));
    public static final Supplier<ArmorItem> SHAMAN_CHESTPLATE = ITEMS.register("armor_shaman_chestplate", () -> new ArmorItem(
            ArmorShamanTier.SHAMAN_ARMOR,
            ArmorItem.Type.CHESTPLATE,
            new Item.Properties().durability((ArmorItem.Type.CHESTPLATE.getDurability(15))
            )));
    public static final Supplier<ArmorItem> SHAMAN_LEGGINGS = ITEMS.register("armor_shaman_leggings", () -> new ArmorItem(
            ArmorShamanTier.SHAMAN_ARMOR,
            ArmorItem.Type.LEGGINGS,
            new Item.Properties().durability((ArmorItem.Type.LEGGINGS.getDurability(15))
            )));
    public static final Supplier<ArmorItem> SHAMAN_BOOTS = ITEMS.register("armor_shaman_boots", () -> new ArmorItem(
            ArmorShamanTier.SHAMAN_ARMOR,
            ArmorItem.Type.BOOTS,
            new Item.Properties().durability((ArmorItem.Type.BOOTS.getDurability(15))
            )));

    public static final Supplier<ArmorItem> MAGE_HELMET = ITEMS.register("armor_mage_helmet", () -> new ArmorItem(
            ArmorMageTier.MAGE_ARMOR,
            ArmorItem.Type.HELMET,
            new Item.Properties().durability((ArmorItem.Type.HELMET.getDurability(15))
            )));
    public static final Supplier<ArmorItem> MAGE_CHESTPLATE = ITEMS.register("armor_mage_chestplate", () -> new ArmorItem(
            ArmorMageTier.MAGE_ARMOR,
            ArmorItem.Type.CHESTPLATE,
            new Item.Properties().durability((ArmorItem.Type.CHESTPLATE.getDurability(15))
            )));
    public static final Supplier<ArmorItem> MAGE_LEGGINGS = ITEMS.register("armor_mage_leggings", () -> new ArmorItem(
            ArmorMageTier.MAGE_ARMOR,
            ArmorItem.Type.LEGGINGS,
            new Item.Properties().durability((ArmorItem.Type.LEGGINGS.getDurability(15))
            )));
    public static final Supplier<ArmorItem> MAGE_BOOTS = ITEMS.register("armor_mage_boots", () -> new ArmorItem(
            ArmorMageTier.MAGE_ARMOR,
            ArmorItem.Type.BOOTS,
            new Item.Properties().durability((ArmorItem.Type.BOOTS.getDurability(15))
            )));
    public static final Supplier<ArmorItem> WARRIOR_HELMET = ITEMS.register("armor_warrior_helmet", () -> new ArmorItem(
            ArmorWarriorTier.WARRIOR_ARMOR,
            ArmorItem.Type.HELMET,
            new Item.Properties().durability((ArmorItem.Type.HELMET.getDurability(15))
            )));
    public static final Supplier<ArmorItem> WARRIOR_CHESTPLATE = ITEMS.register("armor_warrior_chestplate", () -> new ArmorItem(
            ArmorWarriorTier.WARRIOR_ARMOR,
            ArmorItem.Type.CHESTPLATE,
            new Item.Properties().durability((ArmorItem.Type.CHESTPLATE.getDurability(15))
            )));
    public static final Supplier<ArmorItem> WARRIOR_LEGGINGS = ITEMS.register("armor_warrior_leggings", () -> new ArmorItem(
            ArmorWarriorTier.WARRIOR_ARMOR,
            ArmorItem.Type.LEGGINGS,
            new Item.Properties().durability((ArmorItem.Type.LEGGINGS.getDurability(15))
            )));
    public static final Supplier<ArmorItem> WARRIOR_BOOTS = ITEMS.register("armor_warrior_boots", () -> new ArmorItem(
            ArmorWarriorTier.WARRIOR_ARMOR,
            ArmorItem.Type.BOOTS,
            new Item.Properties().durability((ArmorItem.Type.BOOTS.getDurability(15))
            )));
}
