package com.devmod;

import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.event.ScreenEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import org.lwjgl.glfw.GLFW;
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

import java.util.function.Supplier;

@Mod(DevMod.MODID)
public class DevMod
{
    public static final String MODID = "dev_mod";
    public static final Logger LOGGER = LogUtils.getLogger();



    public DevMod(IEventBus modEventBus, ModContainer modContainer)
    {
        modEventBus.addListener(this::commonSetup);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModTab.CREATIVE_MODE_TABS.register(modEventBus);
        NeoForge.EVENT_BUS.addListener(DevMod::blockBreak);
        NeoForge.EVENT_BUS.addListener(DevMod::onEntityInteract);
//        NeoForge.EVENT_BUS.addListener(DevMod::onPlayerInteract);
//        NeoForge.EVENT_BUS.addListener(DevMod::onScreenInit);
//        NeoForge.EVENT_BUS.addListener(DevMod::onScreenRender);
    }

//    public static void onPlayerInteract(InputEvent.Key event) {
//        if (event.getKey() == GLFW.GLFW_KEY_E && event.getAction() == GLFW.GLFW_PRESS) {
//            Minecraft minecraft = Minecraft.getInstance();
//            Player player = minecraft.player;
//            minecraft.setScreen(new ModInventoryScreen(player));
//
//
//        }
//    }

//    public static void onScreenInit(ScreenEvent.Init.Post event) {
//        Screen screen = event.getScreen();
//
//        if (screen instanceof InventoryScreen inv) {
//            event.addListener(new DevButton(inv.getGuiLeft() + 126, inv.height / 2 - 22, 20, 18));
//            event.addListener(new StringWidget(Component.literal("Hello World!!"), Minecraft.getInstance().font));
//        }
//    }
//
//    public static void onScreenRender(ScreenEvent.Render.Post event) {
//        Screen screen = event.getScreen();
//        if (screen instanceof InventoryScreen inv) {
//            inv.renderables.forEach(widget -> {
//                if (widget instanceof DevButton button) {
//                    button.setPosition(20, 20);
//                }
//            });
//        }
//    }

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


    public static class DevButton extends Button
    {
        public DevButton(int pX, int pY, int pWidth, int pHeight) {
            super(pX, pY, pWidth, pHeight, Component.literal("faer"), button -> {
                DevMod.LOGGER.info("new BUtton");
            }, pMessageSupplier -> {
                // Custom narration message logic
                MutableComponent originalMessage = pMessageSupplier.get();
                // Modify the original message or create a new one
                MutableComponent customMessage = originalMessage.copy().append(" - Custom Narration");
                return customMessage;
            });
        }
//
//        public static Button btn = Button.builder(Component.literal("ff"), button -> {
//            DevMod.LOGGER.info("ffffff");
//        }).build();

    }
}


