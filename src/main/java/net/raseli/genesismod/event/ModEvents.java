package net.raseli.genesismod.event;

import net.minecraftforge.eventbus.api.IEventBus;
import net.raseli.genesismod.capabilities.ModCapabilities;

public class ModEvents {
    
    public static void register(IEventBus modEventBus, IEventBus forgeEventBus) {
        // Register AT Field capabilities
        ModCapabilities.register(modEventBus, forgeEventBus);
    }
}
