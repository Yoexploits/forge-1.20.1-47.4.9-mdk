package net.raseli.genesismod.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.raseli.genesismod.GenesisMod;
import net.raseli.genesismod.block.ModBlocks;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, GenesisMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> GENESIS_TAB = CREATIVE_MODE_TABS.register("genesis_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.BATATA.get()))
                    .title(Component.translatable("creativetab.genesis_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.BATATA.get());
                        output.accept(ModItems.BATATA_ENVENENADA.get());
                        output.accept(ModItems.BATATA_STAFF.get());
                        output.accept(ModItems.LONGINUS_SPEAR.get());
                        output.accept(ModItems.CRUEL_ANGEL_THESIS_MUSIC_DISC.get());
                        output.accept(ModItems.CRUEL_ANGEL_THESIS_MUSIC_DISC_MONO.get());

                        output.accept(ModItems.BATATA_SWORD.get());
                        output.accept(ModItems.BATATA_PICKAXE.get());
                        output.accept(ModItems.BATATA_SHOVEL.get());
                        output.accept(ModItems.BATATA_HOE.get());
                        output.accept(ModItems.BATATA_AXE.get());

                        output.accept(ModItems.BATATA_HELMET.get());
                        output.accept(ModItems.BATATA_CHESTPLATE.get());
                        output.accept(ModItems.BATATA_LEGGINGS.get());
                        output.accept(ModItems.BATATA_BOOTS.get());
                        output.accept(ModItems.BEER.get());
                        output.accept(ModItems.FUMO.get());

                        output.accept(Items.DIAMOND);

                        output.accept(ModBlocks.BATATA_BLOCK.get());
                        output.accept(ModBlocks.BATATA_ENVENENADA_BLOCK.get());
                        output.accept(ModBlocks.BATATA_ORE.get());
                        output.accept(ModBlocks.ADAM_BLOCK.get());
                        output.accept(ModItems.DETECTOR_DE_BATATA.get());
                        output.accept(ModItems.PENPEN_SPAWN_EGG.get());
                        output.accept(ModBlocks.SOUND_BLOCK.get());
                        output.accept(ModBlocks.BEER_STATION.get());


                        output.accept(ModBlocks.BATATA_DOOR.get());
                        output.accept(ModBlocks.BATATA_FENCE.get());
                        output.accept(ModBlocks.BATATA_FENCE_GATE.get());
                        output.accept(ModBlocks.BATATA_PRESSURE_PLATE.get());
                        output.accept(ModBlocks.BATATA_SLAB.get());
                        output.accept(ModBlocks.BATATA_TRAPDOOR.get());
                        output.accept(ModBlocks.BATATA_BUTTON.get());
                        output.accept(ModBlocks.BATATA_STAIRS.get());
                        output.accept(ModBlocks.BATATA_WALL.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
