package com.devmod.events;

import com.devmod.data.SpellData;
import com.devmod.utils.spells.KeyBindings;
import net.neoforged.neoforge.client.event.InputEvent;

public class ModKeyBindEventHandler {

    public static void onKeyInput(InputEvent.Key event) {

        // get spell keybinds
        boolean isOurSpell = KeyBindings.checkIfSpellKeybindClicked(event);

        // set last slot active
        if (!isOurSpell) {SpellData.setSlot();}
    }
}
