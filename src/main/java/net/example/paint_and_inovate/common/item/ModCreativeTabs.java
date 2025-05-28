package net.example.paint_and_inovate.common.item;


import net.example.paint_and_inovate.Paint_and_inovate;
import net.example.paint_and_inovate.common.block.ModBlockRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;


import java.util.function.Supplier;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Paint_and_inovate.MODID);

    public static final Supplier<CreativeModeTab> COPPER = CREATIVE_MODE_TAB.register("copper",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.COPPER_WIRE.get()))
                    .title(Component.translatable("creativetab.paint_and_inovate.copper"))
                    .displayItems((itemDisplayParameters, output) -> {

                        output.accept(ModBlockRegistry.COPPER_LATTICE.get());
                        output.accept(ModBlockRegistry.EXPOSED_COPPER_LATTICE.get());
                        output.accept(ModBlockRegistry.WEATHERED_COPPER_LATTICE.get());
                        output.accept(ModBlockRegistry.OXIDIZED_COPPER_LATTICE.get());

                        output.accept(ModBlockRegistry.COPPER_COLUMN.get());
                        output.accept(ModBlockRegistry.EXPOSED_COPPER_COLUMN.get());
                        output.accept(ModBlockRegistry.WEATHERED_COPPER_COLUMN.get());
                        output.accept(ModBlockRegistry.OXIDIZED_COPPER_COLUMN.get());


                        output.accept(ModBlockRegistry.COPPER_STONE.get());
                        output.accept(ModBlockRegistry.COPPER_SAND.get());

                        output.accept(ModBlockRegistry.COPPER_SAND_STONE.get());
                        output.accept(ModBlockRegistry.CHISELED_COPPER_SAND_STONE.get());
                        output.accept(ModBlockRegistry.POLISHED_COPPER_SAND_STONE.get());

                        output.accept(ModBlockRegistry.COPPER_BARREL.get());
                        output.accept(ModBlockRegistry.EXPOSED_COPPER_BARREL.get());
                        output.accept(ModBlockRegistry.WEATHERED_COPPER_BARREL.get());
                        output.accept(ModBlockRegistry.OXIDIZED_COPPER_BARREL.get());

                        output.accept(ModBlockRegistry.LAMP.get());

                        output.accept(ModBlockRegistry.STEEL_LATTICE.get());
                        output.accept(ModBlockRegistry.STEEL_BLOCK.get());
                        output.accept(ModBlockRegistry.CHISELED_STEEL_BLOCK.get());

                        output.accept(ModItems.STEEL_INGOT.get());
                        output.accept(ModItems.STEEL_NUGGET.get());
                        output.accept(ModItems.STEEL_WIRE.get());

                        output.accept(Items.COPPER_INGOT);
                        output.accept(ModItems.COPPER_NUGGET.get());
                        output.accept(ModItems.COPPER_WIRE.get());

                        output.accept(ModItems.COPPER_KEY.get());
                        output.accept(ModItems.DISC_CHIRP.get());
                    }).build());






    public static final Supplier<CreativeModeTab> RAINBOW = CREATIVE_MODE_TAB.register("rainbow",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(Items.RED_DYE))
                    .title(Component.translatable("creativetab.paint_and_inovate.copper"))
                    .displayItems((itemDisplayParameters, output) -> {


                        output.accept(ModItems.DISC_CAT.get());
                    }).build());









    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
