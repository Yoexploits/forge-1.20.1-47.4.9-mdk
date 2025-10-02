package net.raseli.genesismod.capabilities;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.raseli.genesismod.GenesisMod;

public class ModCapabilities {
    
    public static void register(IEventBus modEventBus, IEventBus forgeEventBus) {
        modEventBus.addListener(ModCapabilities::registerCapabilities);
        forgeEventBus.addGenericListener(net.minecraft.world.entity.player.Player.class, ModCapabilities::attachCapabilityToPlayer);
    }

    @SuppressWarnings("unused")
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.register(ATFieldCapability.class);
    }

    @SuppressWarnings("unused")
    public static void attachCapabilityToPlayer(AttachCapabilitiesEvent<Player> event) {
        event.addCapability(net.minecraft.resources.ResourceLocation.fromNamespaceAndPath(GenesisMod.MOD_ID, "at_field"), new ATFieldCapabilityProvider());
    }
}
