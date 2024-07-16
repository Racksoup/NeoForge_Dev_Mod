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
            if (!PlayerData.PLAYER_DATA.contains("hasDeathBox", Tag.TAG_BYTE)) {
                DevMod.LOGGER.info("No hasDeathBox");
                PlayerData.PLAYER_DATA.putBoolean("hasDeathBox", true);
                return super.useOn(pContext);
            }
            if (!PlayerData.PLAYER_DATA.getBoolean("hasDeathBox")) {
                DevMod.LOGGER.info("hasDeathBox false");
                PlayerData.PLAYER_DATA.putBoolean("hasDeathBox", true);
                return super.useOn(pContext);
            }
            DevMod.LOGGER.info("HasDeathBox true");
            return InteractionResult.FAIL;
        } else {
            return InteractionResult.PASS;
        }
    }
}
