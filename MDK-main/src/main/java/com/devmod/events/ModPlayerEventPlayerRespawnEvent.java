package com.devmod.events;

import com.devmod.DevMod;
import com.devmod.data.PlayerData;
import com.devmod.entities.ModDeathBoxBlockEntity;
import com.devmod.registers.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.items.IItemHandler;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ModPlayerEventPlayerRespawnEvent {

    public static void handler(PlayerEvent.PlayerRespawnEvent event) {
        Inventory inv = PlayerData.getInventory();
        List<ItemStack> devItems = new ArrayList<ItemStack>();
        for (int i = 0; i < inv.getContainerSize(); i++) {
            if (ModItems.isDevModItem(inv.getItem(i).getItem())) {
                devItems.add(inv.getItem(i));
            }
        }

        // add devItems to players deathbox
				CompoundTag pos = PlayerData.PLAYER_DATA.getCompound("position");
        BlockEntity myEntity = Minecraft.getInstance().level.getBlockEntity(new BlockPos(pos.getInt("x"), pos.getInt("y"), pos.getInt("z")));
        
				DevMod.LOGGER.info("x: {}, y: {}, z: {}", pos.getInt("x"), pos.getInt("y"), pos.getInt("z"));

				if (myEntity instanceof ModDeathBoxBlockEntity) {
					DevMod.LOGGER.info("ff");
					ModDeathBoxBlockEntity e = (ModDeathBoxBlockEntity) myEntity;
					IItemHandler items = e.getItemHandler();

					for (int i = 0; i < items.getSlots(); i++) {
						if (items.getStackInSlot(i) == ItemStack.EMPTY) {
							DevMod.LOGGER.info("EMPTY");
							items.insertItem(i, new ItemStack(ModItems.REAGENT_TURTLE_SHELL_ITEM.get(), 22), false);
						};
					};
  			};
		};
}
