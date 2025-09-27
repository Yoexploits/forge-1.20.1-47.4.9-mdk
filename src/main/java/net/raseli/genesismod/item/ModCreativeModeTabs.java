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
import net.raseli.genesismod.block.modblocks;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, GenesisMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> GENESIS_TAB = CREATIVE_MODE_TABS.register("genesis_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(moditems.BATATA.get()))
                    .title(Component.translatable("creativetab.genesis_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(moditems.BATATA.get());
                        output.accept(moditems.BATATA_ENVENENADA.get());
                        output.accept(moditems.BATATA_STAFF.get());
                        output.accept(moditems.CRUEL_ANGEL_THESIS_MUSIC_DISC.get());

                        output.accept(moditems.BATATA_SWORD.get());
                        output.accept(moditems.BATATA_PICKAXE.get());
                        output.accept(moditems.BATATA_SHOVEL.get());
                        output.accept(moditems.BATATA_HOE.get());
                        output.accept(moditems.BATATA_AXE.get());

                        output.accept(moditems.BATATA_HELMET.get());
                        output.accept(moditems.BATATA_CHESTPLATE.get());
                        output.accept(moditems.BATATA_LEGGINGS.get());
                        output.accept(moditems.BATATA_BOOTS.get());

                        output.accept(Items.DIAMOND);

                        output.accept(modblocks.BATATA_BLOCK.get());
                        output.accept(modblocks.BATATA_ENVENENADA_BLOCK.get());
                        output.accept(modblocks.BATATA_ORE.get());
                        output.accept(moditems.DETECTOR_DE_BATATA.get());
                        output.accept(modblocks.SOUND_BLOCK.get());

                        output.accept(modblocks.BATATA_DOOR.get());
                        output.accept(modblocks.BATATA_FENCE.get());
                        output.accept(modblocks.BATATA_FENCE_GATE.get());
                        output.accept(modblocks.BATATA_PRESSURE_PLATE.get());
                        output.accept(modblocks.BATATA_SLAB.get());
                        output.accept(modblocks.BATATA_TRAPDOOR.get());
                        output.accept(modblocks.BATATA_BUTTON.get());
                        output.accept(modblocks.BATATA_STAIRS.get());
                        output.accept(modblocks.BATATA_WALL.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
