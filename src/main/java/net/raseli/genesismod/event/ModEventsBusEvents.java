package net.raseli.genesismod.event;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.raseli.genesismod.GenesisMod;
import net.raseli.genesismod.entity.ModEntities;
import net.raseli.genesismod.entity.client.ModModelLayers;
import net.raseli.genesismod.entity.client.PenPenModel;
import net.raseli.genesismod.entity.custom.PenPenEntity;

@Mod.EventBusSubscriber(modid = GenesisMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventsBusEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.PENPEN.get(), PenPenEntity.createAttributes().build());
    }
}
