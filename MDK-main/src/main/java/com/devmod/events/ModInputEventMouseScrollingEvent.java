package com.devmod.events;

import com.devmod.data.SpellData;
import net.neoforged.neoforge.client.event.InputEvent;

public class ModInputEventMouseScrollingEvent {

    public static void handler(InputEvent.MouseScrollingEvent event) {

        // set last slot active
        SpellData.setSlot();
    }
}
