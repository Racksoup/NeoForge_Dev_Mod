package com.devmod.registers;

import com.devmod.DevMod;
import com.devmod.items.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModItems {
    public static final ModShamanTier tier = new ModShamanTier();

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
    public static final Supplier<Item> STABLE_WATER_ITEM = ITEMS.registerItem(
           "stable_water",
            Item::new,
            new Item.Properties()
    );
    public static final Supplier<Item> URANIUM_ITEM = ITEMS.registerItem(
            "uranium",
            Item::new,
            new Item.Properties()
    );
    public static final Supplier<Item> TURTLE_SHELL_ITEM = ITEMS.registerItem(
            "turtle_shell",
            Item::new,
            new Item.Properties()
    );

    // Weapons
    public static final Supplier<ModProjectileWeapon> TEMPERED_STAFF = ITEMS.register(
            "tempered_staff",
            () -> new ModProjectileWeapon(
                    new Item.Properties()
            )

    );
    public static final Supplier<ModSwordItem> NEO_BATTLE_AXE = ITEMS.register("neo_battle_axe", () -> new ModSwordItem(
            ModTier.COPPER_TIER,
            new Item.Properties().attributes(
                    SwordItem.createAttributes(
                            ModTier.COPPER_TIER,
                            22,
                            -3.2f
                            )
            )
    ));
    public static final Supplier<ModSwordItem> URSA_HEART = ITEMS.register("ursa_heart", () -> new ModSwordItem(
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
