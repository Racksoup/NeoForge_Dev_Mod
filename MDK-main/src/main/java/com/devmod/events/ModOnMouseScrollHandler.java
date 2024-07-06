package com.devmod.events;

import com.devmod.data.SpellData;
import net.neoforged.neoforge.client.event.InputEvent;

public class ModOnMouseScrollHandler {

    public static void onMouseScroll(InputEvent.MouseScrollingEvent event) {

        // set last slot active
        SpellData.setSlot();
    }
}
