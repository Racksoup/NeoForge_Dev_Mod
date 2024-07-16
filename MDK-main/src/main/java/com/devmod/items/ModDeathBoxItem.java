package com.devmod.items;

import com.devmod.DevMod;
import com.devmod.data.PlayerData;
import net.minecraft.nbt.Tag;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;

public class ModDeathBoxItem extends BlockItem {
    public ModDeathBoxItem(Block blockIn, Properties builder) {
        super(blockIn, builder);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if (!pContext.getLevel().isClientSide()) {
//            PlayerData.PLAYER_DATA.putBoolean("hasDeathBox", false);
            if (!PlayerData.PLAYER_DATA.contains("hasDeathBox", Tag.TAG_BYTE)) {
                PlayerData.PLAYER_DATA.putBoolean("hasDeathBox", true);
                return super.useOn(pContext);
            }
            if (!PlayerData.PLAYER_DATA.getBoolean("hasDeathBox")) {
                PlayerData.PLAYER_DATA.putBoolean("hasDeathBox", true);
                return super.useOn(pContext);
            }
            return InteractionResult.FAIL;
        } else {
            return InteractionResult.PASS;
        }
    }
}
