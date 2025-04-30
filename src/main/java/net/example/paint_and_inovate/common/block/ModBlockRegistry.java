package net.example.paint_and_inovate.common.block;


import net.example.paint_and_inovate.Paint_and_inovate;
import net.example.paint_and_inovate.common.block.custom.LampBlock;
import net.example.paint_and_inovate.common.block.custom.LatticeBlock;
import net.example.paint_and_inovate.common.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.BarrelBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


import java.util.function.Supplier;

public class ModBlockRegistry {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Paint_and_inovate.MODID);

    public static final RegistryObject<Block> COPPER_LATTICE = registerBlock("copper_lattice",
            () -> new LatticeBlock());
    public static final RegistryObject<Block> EXPOSED_COPPER_LATTICE = registerBlock("exposed_copper_lattice",
            () -> new LatticeBlock());
    public static final RegistryObject<Block> WEATHERED_COPPER_LATTICE = registerBlock("weathered_copper_lattice",
            () -> new LatticeBlock());
    public static final RegistryObject<Block> OXIDIZED_COPPER_LATTICE = registerBlock("oxidized_copper_lattice",
            () -> new LatticeBlock());


    public static final RegistryObject<Block> COPPER_COLUMN = registerBlock("copper_column",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops()
                    .sound(SoundType.COPPER)
                    .mapColor(MapColor.TERRACOTTA_ORANGE)
            ));

    public static final RegistryObject<Block> EXPOSED_COPPER_COLUMN = registerBlock("exposed_copper_column",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops()
                    .sound(SoundType.COPPER)
                    .mapColor(MapColor.TERRACOTTA_ORANGE)
            ));

    public static final RegistryObject<Block> WEATHERED_COPPER_COLUMN = registerBlock("weathered_copper_column",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops()
                    .sound(SoundType.COPPER)
                    .mapColor(MapColor.TERRACOTTA_ORANGE)
            ));

    public static final RegistryObject<Block> OXIDIZED_COPPER_COLUMN = registerBlock("oxidized_copper_column",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops()
                    .sound(SoundType.COPPER)
                    .mapColor(MapColor.TERRACOTTA_ORANGE)
            ));

    public static final RegistryObject<Block> COPPER_BARREL = registerBlock("copper_barrel",
            () -> new BarrelBlock(BlockBehaviour.Properties.of()
                    .strength(4f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.COPPER)
                    .mapColor(MapColor.TERRACOTTA_ORANGE)
                    .isRedstoneConductor((state, world, pos) -> false)
            ));


    public static final RegistryObject<Block> LAMP = registerBlock("lamp",
            () -> new LampBlock());


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }


    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}