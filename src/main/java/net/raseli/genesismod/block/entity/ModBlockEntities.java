package net.raseli.genesismod.block.entity;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.raseli.genesismod.GenesisMod;
import net.raseli.genesismod.block.ModBlocks;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, GenesisMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<BeerStationBlockEntity>> BEER_STATION_BE =
            BLOCK_ENTITIES.register("beer_station_be", () ->
                    BlockEntityType.Builder.of(BeerStationBlockEntity::new,
                            ModBlocks.BEER_STATION.get()).build(null));


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
