package net.raseli.genesismod.worldgen.biome;

import net.raseli.genesismod.GenesisMod;
import net.minecraft.resources.ResourceLocation;
import terrablender.api.Regions;

public class ModTerrablender {
    public static void registerBiomes() {
        Regions.register(new ModOverworldRegion(ResourceLocation.tryBuild(GenesisMod.MOD_ID, "overworld"), 5));
    }
}