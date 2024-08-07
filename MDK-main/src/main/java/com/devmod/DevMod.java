package com.devmod;

import com.devmod.data.PlayerData;
import com.devmod.events.*;
import com.devmod.items.tiers.ArmorMageTier;
import com.devmod.items.tiers.ArmorShamanTier;
import com.devmod.items.tiers.ArmorWarriorTier;
import com.devmod.registers.*;
import com.devmod.ui.ModCraftingScreen;
import com.devmod.ui.ModDeathBoxScreen;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;


@Mod(value = DevMod.MODID)
public class DevMod
{
    public static final String MODID = "dev_mod";
    public static final Logger LOGGER = LogUtils.getLogger();

    public DevMod(IEventBus modEventBus, ModContainer modContainer)
    {
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::registerScreens);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModTab.CREATIVE_MODE_TABS.register(modEventBus);
        ModMenus.MENUS.register(modEventBus);
        ArmorShamanTier.SHAMAN_ARMOR_MATERIALS.register(modEventBus);
        ArmorWarriorTier.WARRIOR_ARMOR_MATERIALS.register(modEventBus);
        ArmorMageTier.MAGE_ARMOR_MATERIALS.register(modEventBus);
    }

    private void registerScreens(RegisterMenuScreensEvent event) {
        event.register(ModMenus.MOD_CRAFTING_MENU.get(), ModCraftingScreen::new);
        event.register(ModMenus.MOD_DEATH_BOX_MENU.get(), ModDeathBoxScreen::new);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        LOGGER.info("HELLO FROM COMMON SETUP");
        if (Config.logDirtBlock)
            LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));
        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);
        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        LOGGER.info("HELLO from server starting");
    }

    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());

            NeoForge.EVENT_BUS.addListener(PlayerData::onPlayerLogin);
            NeoForge.EVENT_BUS.addListener(PlayerData::onPlayerClone);
            NeoForge.EVENT_BUS.addListener(PlayerData::onPlayerLogout);
            NeoForge.EVENT_BUS.addListener(ModBlockEventBreakEvent::handler);
            NeoForge.EVENT_BUS.addListener(ModPlayerInteractEventEntityInteract::handler);
            NeoForge.EVENT_BUS.addListener(ModAttackEntityEvent::handler);
            NeoForge.EVENT_BUS.addListener(ModPlayerTickEventPost::handler);
            NeoForge.EVENT_BUS.addListener(ModLivingHurtEvent::handler);
            NeoForge.EVENT_BUS.addListener(ModInputEventKey::handler);
            NeoForge.EVENT_BUS.addListener(ModInputEventMouseScrollingEvent::handler);
            NeoForge.EVENT_BUS.addListener(ModPlayerEventPlayerRespawnEvent::handler);
            NeoForge.EVENT_BUS.addListener(ModItemEntityPickupEventPost::handler);
        }
    }
		
}


