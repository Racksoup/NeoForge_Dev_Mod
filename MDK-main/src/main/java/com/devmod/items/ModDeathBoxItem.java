package com.devmod.items;
import java.util.UUID;

import com.devmod.DevMod;
import com.devmod.data.PlayerData;
import com.devmod.entities.ModDeathBoxBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;

public class ModDeathBoxItem extends BlockItem {
    public ModDeathBoxItem(Block blockIn, Properties builder) {
        super(blockIn, builder);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if (!pContext.getLevel().isClientSide()) {
            CompoundTag data = PlayerData.PLAYER_DATA;
            if (!data.contains("deathBoxPos", Tag.TAG_COMPOUND)) {
                return super.useOn(pContext);
            }

            CompoundTag dataPos = data.getCompound("deathBoxPos");
            BlockPos entPos = new BlockPos(dataPos.getInt("x"), dataPos.getInt("y"), dataPos.getInt("z"));
            BlockEntity ent = pContext.getLevel().getBlockEntity(entPos);

            if (ent instanceof ModDeathBoxBlockEntity) {
                ModDeathBoxBlockEntity ent1 = (ModDeathBoxBlockEntity) ent;
                UUID id = ent1.getId(); 
								if (!id.equals(data.getUUID("deathBoxUUID"))) {
										return super.useOn(pContext);
								}
						}

            return InteractionResult.FAIL;
        } else {
            return InteractionResult.PASS;
        }
    }
}
