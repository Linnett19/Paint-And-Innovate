package net.example.paint_and_inovate.common.worldgen.biome;

import net.example.paint_and_inovate.Paint_and_inovate;
import net.minecraft.resources.ResourceLocation;
import terrablender.api.Regions;


public class ModTerrablender {
    public static void registerBiomes() {
        Regions.register(new ModOverworldRegion(ResourceLocation.fromNamespaceAndPath(Paint_and_inovate.MODID, "overworld"), 5));
    }
}
