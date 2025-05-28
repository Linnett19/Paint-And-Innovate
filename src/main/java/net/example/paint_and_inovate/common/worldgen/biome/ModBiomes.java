package net.example.paint_and_inovate.common.worldgen.biome;

import net.example.paint_and_inovate.Paint_and_inovate;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.biome.*;

public class ModBiomes {
    public static final ResourceKey<Biome> COPPER_WASTELAND = ResourceKey.create(Registries.BIOME,
             ResourceLocation.fromNamespaceAndPath(Paint_and_inovate.MODID, "copper_wasteland"));

    public static void boostrap(BootstapContext<Biome> context) {
        context.register(COPPER_WASTELAND, copperwastlandBiome(context));
    }

    public static void globalOverworldGeneration(BiomeGenerationSettings.Builder builder) {
        BiomeDefaultFeatures.addDesertVegetation(builder);
    }







    public static Biome copperwastlandBiome(BootstapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();

        BiomeGenerationSettings.Builder biomeBuilder =
                new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));
        globalOverworldGeneration(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(0.0f)
                .temperature(2.0f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects((new BiomeSpecialEffects.Builder())
                        .waterColor(0xe0ffae)
                        .waterFogColor(0xc0d8ff)
                        .skyColor(0xb06734)
                        .fogColor(0x733820)
                        .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                        .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_DESERT)).build())
                .build();
    }
}