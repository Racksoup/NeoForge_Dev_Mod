package com.devmod.items;

import net.minecraft.world.level.block.Block;

public class MyBlock extends Block {
    public MyBlock(Properties pProperties) {
        super(pProperties);
        registerDefaultState(stateDefinition.any());
    }
}
