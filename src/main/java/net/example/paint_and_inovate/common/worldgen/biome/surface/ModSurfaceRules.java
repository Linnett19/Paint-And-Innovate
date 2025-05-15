package net.example.paint_and_inovate.common.worldgen.biome.surface;

import net.example.paint_and_inovate.common.worldgen.biome.ModBiomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;

public class ModSurfaceRules {
    private static final SurfaceRules.RuleSource SAND = makeStateRule(Blocks.NETHERITE_BLOCK);
    private static final SurfaceRules.RuleSource SANDSTONE = makeStateRule(Blocks.GOLD_BLOCK);
    private static final SurfaceRules.RuleSource STONE = makeStateRule(Blocks.CALCITE);



    public static SurfaceRules.RuleSource makeRules() {
        SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0);

        SurfaceRules.RuleSource grassSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(isAtOrAboveWaterLevel, SAND), SANDSTONE);

        return SurfaceRules.sequence(
                SurfaceRules.sequence(SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.COPPER_WASTELAND),
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, STONE)),
                        SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, STONE)),


                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, grassSurface)
        );
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }
}
