package net.example.paint_and_inovate.common.item;


import net.example.paint_and_inovate.Paint_and_inovate;
import net.example.paint_and_inovate.common.item.custom.ElixirOfDoubling;
import net.example.paint_and_inovate.common.item.custom.PaintBrushItem;
import net.example.paint_and_inovate.common.item.custom.PointerItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Paint_and_inovate.MODID);


    public static final RegistryObject<Item> POINTER = ITEMS.register("pointer",
            () -> new PointerItem());

    public static final RegistryObject<Item> ELIXIR_DOUBLING = ITEMS.register("elixir_doubling",
            () -> new ElixirOfDoubling());

    public static final RegistryObject<Item> PAINT_ROLLER = ITEMS.register("paint_roller",
            () -> new PaintBrushItem());


    public static final RegistryObject<Item> HEAT_HAZE_AMULET = ITEMS.register("heat_haze_amulet",
            () -> new PointerItem());




    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}