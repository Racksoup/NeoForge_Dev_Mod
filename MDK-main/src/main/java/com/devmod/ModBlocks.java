package com.devmod;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.RegistryBuilder;

public class ModBlocks {
    // Blocks
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(DevMod.MODID);
    public static final DeferredBlock<Block> MY_BLOCK = BLOCKS.register("my_block", () -> new MyBlock(BlockBehaviour.Properties.of()
            .destroyTime(2.0f)
            .explosionResistance(10.0f)
            .sound(SoundType.ANVIL)
            .lightLevel(state -> 7)));
    public static final ResourceKey<Registry<Block>> BLOCK_REGISTRY_KEY = ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath(DevMod.MODID, "dev_blocks"));
    public static final Registry<Block> BLOCK_REGISTRY = new RegistryBuilder<>(BLOCK_REGISTRY_KEY).create();
}
