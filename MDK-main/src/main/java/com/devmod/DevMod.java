package com.devmod;

import com.devmod.data.ModRoleData;
import com.devmod.events.ModLivingHurtEventHandler;
import com.devmod.events.ModPlayerAttackEventHandler;
import com.devmod.events.ModPlayerTickEventHandler;
import com.devmod.registers.ModBlocks;
import com.devmod.registers.ModItems;
import com.devmod.registers.ModMenus;
import com.devmod.registers.ModTab;
import com.devmod.ui.ModCraftingScreen;
import com.devmod.ui.ModCraftingMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
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


@Mod(DevMod.MODID)
public class DevMod
{
    public static final String MODID = "dev_mod";
    public static final Logger LOGGER = LogUtils.getLogger();

    public DevMod(IEventBus modEventBus, ModContainer modContainer)
    {
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::registerScreens);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModTab.CREATIVE_MODE_TABS.register(modEventBus);
        ModMenus.MENUS.register(modEventBus);
    }

    private void registerScreens(RegisterMenuScreensEvent event) {
        event.register(ModMenus.MOD_CRAFTING_MENU.get(), ModCraftingScreen::new);
    }

    private static void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
        Player player = event.getEntity();
        Entity entity = event.getTarget();
        Level level = (Level) event.getLevel();

        if (event.getHand() == net.minecraft.world.InteractionHand.MAIN_HAND) {
            if (entity instanceof Turtle) {
                ItemStack turtleShellStack = new ItemStack(ModItems.TURTLE_SHELL_ITEM.get());
                ItemEntity turtleShellEntity = new ItemEntity(level, entity.xo, entity.yo, entity.zo, turtleShellStack);
                level.addFreshEntity(turtleShellEntity);
            }
        }
    }

    private static void blockBreak(BlockEvent.BreakEvent event) {
        Level level = (Level) event.getLevel();
        BlockPos pos = event.getPos();

        if (event.getState().getBlock() == Blocks.COAL_BLOCK) {
            ItemStack uraniumStack = new ItemStack(ModItems.URANIUM_ITEM.get());
            ItemEntity uraniumEntity = new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), uraniumStack);
            level.addFreshEntity(uraniumEntity);
        }

        if (event.getState().getBlock() == Blocks.SNOW) {
            ItemStack stableWaterStack = new ItemStack(ModItems.STABLE_WATER_ITEM.get());
            ItemEntity stableWaterEntity = new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), stableWaterStack);
            level.addFreshEntity(stableWaterEntity);
        }
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        LOGGER.info("HELLO FROM COMMON SETUP");
        if (Config.logDirtBlock)
            LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));
        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);
        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));

        NeoForge.EVENT_BUS.addListener(ModRoleData::onPlayerLogin);
        NeoForge.EVENT_BUS.addListener(ModRoleData::onPlayerClone);
        NeoForge.EVENT_BUS.addListener(ModRoleData::onPlayerLogout);
        NeoForge.EVENT_BUS.addListener(DevMod::blockBreak);
        NeoForge.EVENT_BUS.addListener(DevMod::onEntityInteract);
        NeoForge.EVENT_BUS.addListener(ModPlayerAttackEventHandler::onPlayerAttack);
        NeoForge.EVENT_BUS.addListener(ModPlayerTickEventHandler::onPlayerTick);
        NeoForge.EVENT_BUS.addListener(ModLivingHurtEventHandler::onLivingHurt);
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
        }
    }
}


