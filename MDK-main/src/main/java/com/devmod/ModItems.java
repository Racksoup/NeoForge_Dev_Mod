package com.devmod;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModItems {
    // Items
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(DevMod.MODID);
    public static final Supplier<Item> MY_BLOCK_ITEM = ITEMS.register(
            "my_block",
            () -> new BlockItem(ModBlocks.MY_BLOCK.get(), new Item.Properties())
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
}
