package net.example.paint_and_inovate;

import com.mojang.logging.LogUtils;
import net.example.paint_and_inovate.common.block.ModBlockRegistry;
import net.example.paint_and_inovate.common.entity.HCEntities;

import net.example.paint_and_inovate.common.entity.client.RoboCreeperRenderer;
import net.example.paint_and_inovate.common.item.ModCreativeTabs;
import net.example.paint_and_inovate.common.item.ModItems;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(Paint_and_inovate.MODID)
public class Paint_and_inovate {
    public static final String MODID = "paint_and_inovate";
    private static final Logger LOGGER = LogUtils.getLogger();


    public Paint_and_inovate() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Регистрация ивентов и компонентов
        modEventBus.addListener(this::commonSetup);
        ModItems.register(modEventBus);
        ModBlockRegistry.register(modEventBus);
        HCEntities.register(modEventBus);
        ModCreativeTabs.register(modEventBus);




        // Регистрируем все события на клиенте (которые подписаны на EventBus)
        MinecraftForge.EVENT_BUS.register(ClientModEvents.class);
        // Регистрация событий
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Общая инициализация
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ItemProperties.register(
                    ModItems.PAINT_ROLLER.get(), // ваш предмет кисти
                    ResourceLocation.fromNamespaceAndPath("paint_and_inovate","has_dye"), // название свойства
                    (itemStack, level, livingEntity, seed) -> {
                        if (livingEntity == null) return 0.0f; // если не в руках игрока

                        // Проверяем, есть ли краситель в другой руке
                        ItemStack mainHand = livingEntity.getMainHandItem();
                        ItemStack offHand = livingEntity.getOffhandItem();

                        boolean hasDyeInOtherHand =
                                (itemStack == mainHand && offHand.getItem() instanceof DyeItem) ||
                                        (itemStack == offHand && mainHand.getItem() instanceof DyeItem);

                        return hasDyeInOtherHand ? 1.0f : 0.0f; // 1 = краситель есть, 0 = нет
                    }
            );
        });
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Сервер запускается
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(HCEntities.ROBO_CREEPER.get(), RoboCreeperRenderer::new);
        }
    }
}