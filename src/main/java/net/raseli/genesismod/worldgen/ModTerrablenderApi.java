package net.raseli.genesismod.worldgen;

import net.minecraft.resources.ResourceLocation;
import net.raseli.genesismod.GenesisMod;
import net.raseli.genesismod.worldgen.biome.ModOverworldRegion;
import terrablender.api.Regions;

public class ModTerrablenderApi {
    public static void registerBiomes() {
        Regions.register(new ModOverworldRegion(ResourceLocation.tryBuild(GenesisMod.MOD_ID, "overworld"), 5));
    }
}
