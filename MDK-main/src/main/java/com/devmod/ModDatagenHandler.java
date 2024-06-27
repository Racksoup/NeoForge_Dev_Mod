package com.devmod;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = DevMod.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModDatagenHandler {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DevMod.LOGGER.info("Gathering data for ModDatagenHandler.");
        DevMod.LOGGER.info("Gathering data for ModDatagenHandler.");
        DevMod.LOGGER.info("Gathering data for ModDatagenHandler.");
        DevMod.LOGGER.info("Gathering data for ModDatagenHandler.");
        DevMod.LOGGER.info("Gathering data for ModDatagenHandler.");
        DevMod.LOGGER.info("Gathering data for ModDatagenHandler.");
        DevMod.LOGGER.info("Gathering data for ModDatagenHandler.");
        DevMod.LOGGER.info("Gathering data for ModDatagenHandler.");
        DevMod.LOGGER.info("Gathering data for ModDatagenHandler.");
        DevMod.LOGGER.info("Gathering data for ModDatagenHandler.");
        DevMod.LOGGER.info("Gathering data for ModDatagenHandler.");
        DevMod.LOGGER.info("Gathering data for ModDatagenHandler.");
        DevMod.LOGGER.info("Gathering data for ModDatagenHandler.");
        DevMod.LOGGER.info("Gathering data for ModDatagenHandler.");
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(
                event.includeServer(),
                new ModRecipeProvider(output, lookupProvider)
        );
    }
}
