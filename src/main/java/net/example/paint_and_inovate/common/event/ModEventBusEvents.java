package net.example.paint_and_inovate.common.event;


import net.example.paint_and_inovate.Paint_and_inovate;
import net.example.paint_and_inovate.common.entity.HCEntities;
import net.example.paint_and_inovate.common.entity.client.RoboCreeperModel;
import net.example.paint_and_inovate.common.entity.custom.RoboCreeperEntity;
import net.example.paint_and_inovate.common.item.ModItems;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Paint_and_inovate.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(RoboCreeperModel.LAYER_LOCATION, RoboCreeperModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(HCEntities.ROBO_CREEPER.get(), RoboCreeperEntity.createAttributes().build());
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

}