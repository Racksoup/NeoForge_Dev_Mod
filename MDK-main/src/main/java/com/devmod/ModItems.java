package com.devmod;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.RegistryBuilder;

import java.util.function.Supplier;

public class ModItems {
    // Items
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(DevMod.MODID);
    public static final Supplier<Item> MY_BLOCK_ITEM = ITEMS.register(
            "my_block",
            () -> new BlockItem(ModBlocks.MY_BLOCK.get(), new Item.Properties())
    );
    public static final ResourceKey<Registry<Item>> ITEM_REGISTRY_KEY = ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(DevMod.MODID, "dev_items"));
    public static final Registry<Item> ITEM_REGISTRY = new RegistryBuilder<>(ITEM_REGISTRY_KEY)
            .sync(true)
            .defaultKey(ResourceLocation.fromNamespaceAndPath(DevMod.MODID, "mod_items"))
            .maxId(256)
            .create();
}
