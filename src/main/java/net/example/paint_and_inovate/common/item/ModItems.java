package net.example.paint_and_inovate.common.item;


import net.example.paint_and_inovate.Paint_and_inovate;
import net.example.paint_and_inovate.common.item.custom.ElixirOfDoubling;
import net.example.paint_and_inovate.common.item.custom.PaintBrushItem;
import net.example.paint_and_inovate.common.item.custom.PointerItem;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.property.Properties;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.awt.*;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Paint_and_inovate.MODID);

    public static final Rarity RARITY_RAINBOW = Rarity.create("paint_and_inovate:rainbow",
            style -> style.withColor(Color.HSBtoRGB((System.currentTimeMillis() % 10000) / 10000F, 0.8f, 1f)));

    public static final Rarity RARITY_COPPER = Rarity.create("paint_and_inovate:copper",
            style -> style.withColor(Color.HSBtoRGB(0.0389f, 0.72f, 1f)));






    public static final RegistryObject<Item> ELIXIR_DOUBLING = ITEMS.register("elixir_doubling",
            () -> new ElixirOfDoubling());

    public static final RegistryObject<Item> PAINT_ROLLER = ITEMS.register("paint_roller",
            () -> new PaintBrushItem());

    public static final RegistryObject<Item> COPPER_WIRE = ITEMS.register("copper_wire",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STEEL_WIRE = ITEMS.register("steel_wire",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> COPPER_KEY = ITEMS.register("copper_key",
            () -> new Item(new Item.Properties().rarity(RARITY_COPPER)));

    public static final RegistryObject<Item> COPPER_NUGGET = ITEMS.register("copper_nugget",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STEEL_NUGGET = ITEMS.register("steel_nugget",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot",
            () -> new Item(new Item.Properties()));



    public static final RegistryObject<Item> DISC_CAT = ITEMS.register("disc_cat",
            () -> new RecordItem(6, SoundEvents.MUSIC_DISC_CAT, new Item.Properties().stacksTo(1), 178));

    public static final RegistryObject<Item> DISC_CHIRP = ITEMS.register("disc_chirp",
            () -> new RecordItem(6, SoundEvents.MUSIC_DISC_CHIRP, new Item.Properties().stacksTo(1), 185));




    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}