package com.devmod.registers;

import com.devmod.DevMod;
import com.devmod.blocks.ModCraftingTable;
import com.devmod.blocks.ModDeathBox;
import com.devmod.blocks.MyBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    // Blocks
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(DevMod.MODID);
    public static final DeferredBlock<Block> MY_BLOCK = BLOCKS.register("my_block", () -> new MyBlock(BlockBehaviour.Properties.of()
            .destroyTime(2.0f)
            .explosionResistance(10.0f)
            .sound(SoundType.ANVIL)
            .lightLevel(state -> 7)));
    public static final Supplier<Block> MOD_DEATH_BOX_BLOCK = BLOCKS.register("mod_death_box_block", () -> new ModDeathBox(BlockBehaviour.Properties.of()));
    public static final DeferredBlock<CraftingTableBlock> MY_CRAFTING_TABLE_BLOCK = BLOCKS.register("my_crafting_table", () -> new ModCraftingTable(BlockBehaviour.Properties.of()
            .destroyTime(2.0f)
            .explosionResistance(10.0f)
            .sound(SoundType.ANVIL)
            .lightLevel(state -> 7)));
}
