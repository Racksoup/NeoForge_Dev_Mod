package com.devmod.registers;

import com.devmod.DevMod;
import com.devmod.entities.ModDeathBoxBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, DevMod.MODID);

    public static final Supplier<BlockEntityType<ModDeathBoxBlockEntity>> MOD_DEATH_BOX_BLOCK_ENTITY = BLOCK_ENTITIES.register(
            "mod_death_box_block_entity",
            () -> BlockEntityType.Builder.of(ModDeathBoxBlockEntity::new, ModBlocks.MOD_DEATH_BOX_BLOCK.get()).build(null)
    );
}
