package com.devmod;

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
    public static final Supplier<Item> TEMPERED_STAFF = ITEMS.register(
            "tempered_staff",
            () -> new ModProjectileWeapon(
                    new Item.Properties()
            )

    );
    public static final Supplier<SwordItem> NEO_BATTLE_AXE = ITEMS.register("neo_battle_axe", () -> new SwordItem(
            ModTier.COPPER_TIER,
            new Item.Properties().attributes(
                    SwordItem.createAttributes(
                            ModTier.COPPER_TIER,
                            22,
                            -3.2f
                            )
            )
    ));
    public static final Supplier<SwordItem> URSA_HEART = ITEMS.register("ursa_heart", () -> new SwordItem(
            tier,
            new Item.Properties().attributes(
                    SwordItem.createAttributes(
                            tier,
                            5,
                            0.0f
                    )
            )
    ));
}
