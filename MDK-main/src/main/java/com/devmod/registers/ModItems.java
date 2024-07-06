package com.devmod.registers;

import com.devmod.DevMod;
import com.devmod.items.ModProjectileWeapon;
import com.devmod.ModShamanTier;
import com.devmod.ModTier;
import com.devmod.items.ModSpell;
import com.devmod.items.ModSwordItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
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
}
