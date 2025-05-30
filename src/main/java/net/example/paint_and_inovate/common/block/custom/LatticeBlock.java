package net.example.paint_and_inovate.common.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class LatticeBlock extends Block {
    public LatticeBlock() {
        super(Properties.of()
                .mapColor(MapColor.TERRACOTTA_ORANGE)
                .noOcclusion()
                .isSuffocating((state, world, pos) -> false)
                .isViewBlocking((state, world, pos) -> false)
                .isRedstoneConductor((state, world, pos) -> false)
                .pushReaction(PushReaction.NORMAL)
                .strength(0.3F)
                .sound(SoundType.COPPER));
    }

    @Override
    public boolean skipRendering(BlockState state, BlockState adjacentBlockState, Direction side) {
        return state.getBlock() == adjacentBlockState.getBlock();
    }
    @Override
    public int getLightBlock(BlockState state, BlockGetter level, BlockPos pos) {
        return 0;
    }
    @Override
    public float getShadeBrightness(BlockState state, BlockGetter level, BlockPos pos) {
        return 1.0F;
    }
}