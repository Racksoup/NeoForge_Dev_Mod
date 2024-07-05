package com.devmod.events;

import com.devmod.registers.ModItems;
import com.devmod.utils.spells.warrior.Charge;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

public class ModPlayerInteractEventHandler {

    public static void onLeftClick(PlayerInteractEvent.LeftClickBlock event) {
        if(event.getEntity() != null && event.getEntity().getMainHandItem().getItem() == ModItems.SPELL_CHARGE.get()) {
            Charge.onCharge();
        }
    }


}
