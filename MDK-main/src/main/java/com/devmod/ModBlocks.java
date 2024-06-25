package com.devmod;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {
    // Blocks
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(DevMod.MODID);
    public static final DeferredBlock<Block> MY_BLOCK = BLOCKS.register("my_block", () -> new MyBlock(BlockBehaviour.Properties.of()
            .destroyTime(2.0f)
            .explosionResistance(10.0f)
            .sound(SoundType.ANVIL)
            .lightLevel(state -> 7)));
    public static final DeferredBlock<Block> MY_CRAFTING_TABLE_BLOCK = BLOCKS.register("my_crafting_table", () -> new ModCraftingTable(BlockBehaviour.Properties.of()
            .destroyTime(2.0f)
            .explosionResistance(10.0f)
            .sound(SoundType.ANVIL)
            .lightLevel(state -> 7)));
}
