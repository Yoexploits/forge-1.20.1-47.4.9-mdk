package net.raseli.genesismod.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.raseli.genesismod.GenesisMod;
import net.raseli.genesismod.entity.custom.PenPenEntity;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
    DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, GenesisMod.MOD_ID);


    public static final RegistryObject<EntityType<PenPenEntity>> PENPEN =
            ENTITY_TYPES.register("penpen", () -> EntityType.Builder.of(PenPenEntity::new, MobCategory.CREATURE)
                    .sized(1.5f, 1.5f).build("penpen"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
