package net.raseli.genesismod.worldgen.biome;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Musics;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.raseli.genesismod.GenesisMod;
import net.raseli.genesismod.entity.ModEntities;
import net.raseli.genesismod.sound.ModSounds;

public class ModBiomes {
    public static final ResourceKey<Biome> MAR_MORTO = ResourceKey.create(Registries.BIOME,
            ResourceLocation.tryBuild(GenesisMod.MOD_ID, "mar_morto"));

    public static void boostrap(BootstapContext<Biome> context) {
        context.register(MAR_MORTO, marMorto(context));
    }

    public static void globalOverworldGeneration(BiomeGenerationSettings.Builder builder) {
        BiomeDefaultFeatures.addDefaultCarversAndLakes(builder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        BiomeDefaultFeatures.addDefaultSprings(builder);
        BiomeDefaultFeatures.addSurfaceFreezing(builder);
    }

    public static Biome marMorto(BootstapContext<Biome> context) {
        // Ocean biome mob spawn settings
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        

        
        // Add hostile mobs
        BiomeDefaultFeatures.oceanSpawns(spawnBuilder, 1, 1, 1);

        // Ocean biome generation settings
        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        // Add ocean-specific features
        BiomeDefaultFeatures.addDefaultCarversAndLakes(biomeBuilder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(biomeBuilder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSprings(biomeBuilder);
        BiomeDefaultFeatures.addSurfaceFreezing(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .downfall(0.3f) // Low rainfall for dead sea effect
                .temperature(0.5f) // Cool temperature
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0x385757) // Dark teal water color as specified
                        .waterFogColor(0x2a3f3f) // Darker water fog
                        .skyColor(0x78a7ff) // Ocean sky color
                        .fogColor(0xc0d8ff) // Light fog
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(Musics.createGameMusic(ModSounds.CRUEL_ANGEL_THESIS_MONO.getHolder().get())).build())
                .build();
    }
}