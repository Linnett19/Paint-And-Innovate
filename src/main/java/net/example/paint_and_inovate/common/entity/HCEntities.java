package net.example.paint_and_inovate.common.entity;


import net.example.paint_and_inovate.Paint_and_inovate;
import net.example.paint_and_inovate.common.entity.custom.RoboCreeperEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


import java.util.function.Supplier;

public class HCEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Paint_and_inovate.MODID);

    public static final RegistryObject<EntityType<RoboCreeperEntity>> ROBO_CREEPER =
            ENTITY_TYPES.register("robo_creeper",
                    () -> EntityType.Builder.<RoboCreeperEntity>of(RoboCreeperEntity::new, MobCategory.MONSTER)
                            .sized(0.6F, 1.8F)
                            .build(ResourceLocation.fromNamespaceAndPath(Paint_and_inovate.MODID, "robo_creeper").toString()) // ВАЖНО!
            );

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}