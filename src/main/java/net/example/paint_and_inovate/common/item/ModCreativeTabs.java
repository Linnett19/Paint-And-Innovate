package net.example.paint_and_inovate.common.item;


import net.example.paint_and_inovate.Paint_and_inovate;
import net.example.paint_and_inovate.common.block.ModBlockRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;


import java.util.function.Supplier;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Paint_and_inovate.MODID);

    public static final Supplier<CreativeModeTab> COPPER = CREATIVE_MODE_TAB.register("copper",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlockRegistry.COPPER_LATTICE.get()))
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
                        output.accept(ModBlockRegistry.COPPER_BARREL.get());
                        output.accept(ModBlockRegistry.LAMP.get());
                        output.accept(ModItems.ELIXIR_DOUBLING.get());
                        output.accept(ModItems.PAINT_ROLLER.get());

                        output.accept(ModBlockRegistry.COPPER_SAND.get());
                        output.accept(ModBlockRegistry.COPPER_SAND_STONE.get());
                        output.accept(ModBlockRegistry.COPPER_STONE.get());
                    }).build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
