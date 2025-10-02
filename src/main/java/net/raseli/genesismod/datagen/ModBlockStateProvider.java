package net.raseli.genesismod.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.raseli.genesismod.GenesisMod;
import net.raseli.genesismod.block.ModBlocks;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, GenesisMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.BATATA_BLOCK);
        blockWithItem(ModBlocks.BATATA_ENVENENADA_BLOCK);
        blockWithItem(ModBlocks.LILITH_BLOCK);

        simpleBlockWithItem(ModBlocks.BEER_STATION.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/beer_station")));

        blockWithItem(ModBlocks.BATATA_ORE);


        blockWithItem(ModBlocks.SOUND_BLOCK);
        blockWithItem(ModBlocks.SHEEP_SUMMON_BLOCK);

        stairsBlock(((StairBlock) ModBlocks.BATATA_STAIRS.get()), blockTexture(ModBlocks.BATATA_BLOCK.get()));
        slabBlock(((SlabBlock) ModBlocks.BATATA_SLAB.get()), blockTexture(ModBlocks.BATATA_BLOCK.get()), blockTexture(ModBlocks.BATATA_BLOCK.get()));

        buttonBlock(((ButtonBlock) ModBlocks.BATATA_BUTTON.get()), blockTexture(ModBlocks.BATATA_BLOCK.get()));
        pressurePlateBlock(((PressurePlateBlock) ModBlocks.BATATA_PRESSURE_PLATE.get()), blockTexture(ModBlocks.BATATA_BLOCK.get()));

        fenceBlock(((FenceBlock) ModBlocks.BATATA_FENCE.get()), blockTexture(ModBlocks.BATATA_BLOCK.get()));
        fenceGateBlock(((FenceGateBlock) ModBlocks.BATATA_FENCE_GATE.get()), blockTexture(ModBlocks.BATATA_BLOCK.get()));
        wallBlock(((WallBlock) ModBlocks.BATATA_WALL.get()), blockTexture(ModBlocks.BATATA_BLOCK.get()));

        doorBlockWithRenderType(((DoorBlock) ModBlocks.BATATA_DOOR.get()), modLoc("block/batata_door_bottom"), modLoc("block/batata_door_top"), "cutout");
        trapdoorBlockWithRenderType(((TrapDoorBlock) ModBlocks.BATATA_TRAPDOOR.get()), modLoc("block/batata_trapdoor"), true, "cutout");
    }
    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
