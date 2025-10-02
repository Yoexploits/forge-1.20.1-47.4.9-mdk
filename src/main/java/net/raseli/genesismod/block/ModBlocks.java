package net.raseli.genesismod.block;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.raseli.genesismod.GenesisMod;
import net.raseli.genesismod.block.custom.BeerStationBlock;
import net.raseli.genesismod.block.custom.SoundBlock;
import net.raseli.genesismod.block.custom.SheepSummonBlock;
import net.raseli.genesismod.block.custom.HouseBuilderBlock;
import net.raseli.genesismod.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, GenesisMod.MOD_ID);

    public static final RegistryObject<Block> BATATA_BLOCK = registerBlock("batata_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.ROOTS)));
    public static final RegistryObject<Block> BATATA_ENVENENADA_BLOCK = registerBlock("batata_envenenada_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.ROOTS)));
    public static final RegistryObject<Block> BATATA_ORE = registerBlock("batata_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_ORE).requiresCorrectToolForDrops(), UniformInt.of(10, 20)));
    public static final RegistryObject<Block> SOUND_BLOCK = registerBlock("sound_block",
            () -> new SoundBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> SHEEP_SUMMON_BLOCK = registerBlock("sheep_summon_block",
            () -> new SheepSummonBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> HOUSE_BUILDER_BLOCK = registerBlock("house_builder_block",
            () -> new HouseBuilderBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> LILITH_BLOCK = registerBlock("lilith_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.WHITE_CONCRETE)));

    public static final RegistryObject<Block> BATATA_STAIRS = registerBlock("batata_stairs",
            () -> new StairBlock(() -> ModBlocks.BATATA_BLOCK.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.ROOTS)));
    public static final RegistryObject<Block> BATATA_SLAB = registerBlock("batata_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.ROOTS)));

    public static final RegistryObject<Block> BATATA_BUTTON = registerBlock("batata_button",
            () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_BUTTON).sound(SoundType.ROOTS),
                    BlockSetType.WARPED, 10, true));
    public static final RegistryObject<Block> BATATA_PRESSURE_PLATE = registerBlock("batata_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.ROOTS),
                    BlockSetType.WARPED));

    public static final RegistryObject<Block> BATATA_FENCE = registerBlock("batata_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.ROOTS)));
    public static final RegistryObject<Block> BATATA_FENCE_GATE = registerBlock("batata_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.ROOTS), SoundEvents.ROOTS_BREAK, SoundEvents.GHAST_DEATH));
    public static final RegistryObject<Block> BATATA_WALL = registerBlock("batata_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.ROOTS)));

    public static final RegistryObject<Block> BATATA_DOOR = registerBlock("batata_door",
            () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.ROOTS).noOcclusion(), BlockSetType.WARPED));
    public static final RegistryObject<Block> BATATA_TRAPDOOR = registerBlock("batata_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).sound(SoundType.ROOTS).noOcclusion(), BlockSetType.WARPED));
    public static final RegistryObject<Block> BEER_STATION = registerBlock("beer_station",
            () -> new BeerStationBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion()));


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}
