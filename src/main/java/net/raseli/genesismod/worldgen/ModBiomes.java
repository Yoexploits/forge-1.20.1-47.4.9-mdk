package net.raseli.genesismod.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.raseli.genesismod.GenesisMod;
import net.raseli.genesismod.entity.ModEntities;

public class ModBiomes {
    public static final ResourceKey<Biome> MAR_MORTO = ResourceKey.create(Registries.BIOME,
            ResourceLocation.fromNamespaceAndPath(GenesisMod.MOD_ID, "mar_morto"));

    public static void bootstrap(BootstapContext<Biome> context) {
        context.register(MAR_MORTO, marMortoBiome(context));
    }

    public static Biome marMortoBiome(BootstapContext<Biome> context) {
        // Create biome with ocean characteristics
        BiomeSpecialEffects.Builder effects = new BiomeSpecialEffects.Builder()
                .waterColor(0x385757) // Dark teal water color as specified
                .waterFogColor(0x2a3f3f) // Darker water fog
                .fogColor(0xc0d8ff) // Light fog
                .skyColor(0x78a7ff) // Ocean sky color
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                .backgroundMusic(null);

        // Create mob spawn settings with rare penpen spawning
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder()
                .addSpawn(MobCategory.WATER_CREATURE,
                        new MobSpawnSettings.SpawnerData(ModEntities.PENPEN.get(), 1, 1, 2)) // Very rare penpen spawns
                .addSpawn(MobCategory.WATER_AMBIENT,
                        new MobSpawnSettings.SpawnerData(EntityType.COD, 8, 0, 0))
                .addSpawn(MobCategory.WATER_AMBIENT,
                        new MobSpawnSettings.SpawnerData(EntityType.SALMON, 5, 0, 0))
                .addSpawn(MobCategory.WATER_CREATURE,
                        new MobSpawnSettings.SpawnerData(EntityType.SQUID, 1, 0, 0))
                .addSpawn(MobCategory.WATER_CREATURE,
                        new MobSpawnSettings.SpawnerData(EntityType.DOLPHIN, 1, 0, 0));

        // Create generation settings for ocean biome
        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), 
                context.lookup(Registries.CONFIGURED_CARVER)
        );
        
        // Add ocean features
        BiomeDefaultFeatures.addDefaultCarversAndLakes(generationSettings);
        BiomeDefaultFeatures.addDefaultCrystalFormations(generationSettings);
        BiomeDefaultFeatures.addDefaultMonsterRoom(generationSettings);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(generationSettings);
        BiomeDefaultFeatures.addDefaultSprings(generationSettings);
        BiomeDefaultFeatures.addSurfaceFreezing(generationSettings);
        
        // Add ocean vegetation
        BiomeDefaultFeatures.addDefaultOres(generationSettings);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(0.5f) // Cool temperature
                .downfall(0.3f) // Low rainfall
                .specialEffects(effects.build())
                .mobSpawnSettings(spawnSettings.build())
                .generationSettings(generationSettings.build())
                .build();
    }
}
