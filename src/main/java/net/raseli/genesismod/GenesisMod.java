package net.raseli.genesismod;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.raseli.genesismod.block.ModBlocks;
import net.raseli.genesismod.block.entity.ModBlockEntities;
import net.raseli.genesismod.entity.ModEntities;
import net.raseli.genesismod.entity.client.PenPenRenderer;
import net.raseli.genesismod.item.ModCreativeModeTabs;
import net.raseli.genesismod.item.ModItems;
import net.raseli.genesismod.event.ModEvents;
import net.raseli.genesismod.recipe.ModRecipes;
import net.raseli.genesismod.screen.BeerStationScreen;
import net.raseli.genesismod.screen.ModMenuTypes;
import net.raseli.genesismod.sound.ModSounds;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(GenesisMod.MOD_ID)
public class GenesisMod {
    public static final String MOD_ID = "genesis";

    public GenesisMod(FMLJavaModLoadingContext context){
        IEventBus modEventBus = context.getModEventBus();

        ModCreativeModeTabs.register(modEventBus);

        ModItems.register(modEventBus);

        ModBlocks.register(modEventBus);
        ModSounds.register(modEventBus);
        ModEntities.register(modEventBus);

        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModRecipes.register(modEventBus);
        
        // Register capabilities and events
        ModEvents.register(modEventBus, MinecraftForge.EVENT_BUS);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event)  {
        event.enqueueWork(() -> {
            net.raseli.genesismod.worldgen.ModTerrablenderApi.registerBiomes();
        });
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)  {
        if(event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
            event.accept(ModItems.BATATA);
            event.accept(ModItems.BATATA_ENVENENADA);
        }
        if(event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(ModItems.AT_FIELD_AWAKENER);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        net.raseli.genesismod.command.TeleportToMarMortoCommand.register(event.getServer().getCommands().getDispatcher());
        net.raseli.genesismod.commands.ATFieldCommand.register(event.getServer().getCommands().getDispatcher());
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents{
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event){
            EntityRenderers.register(ModEntities.PENPEN.get(), PenPenRenderer::new);

            MenuScreens.register(ModMenuTypes.BEER_STATION_MENU.get(), BeerStationScreen::new);
        }
    }
}
