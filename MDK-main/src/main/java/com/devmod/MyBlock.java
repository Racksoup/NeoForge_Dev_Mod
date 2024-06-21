package com.devmod;


import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class MyBlock extends Block {
    public MyBlock(BlockBehaviour.Properties pProperties) {
        super(pProperties);
        registerDefaultState(stateDefinition.any());
    }
}
