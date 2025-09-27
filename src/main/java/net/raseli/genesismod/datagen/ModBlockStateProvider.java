package net.raseli.genesismod.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.raseli.genesismod.GenesisMod;
import net.raseli.genesismod.block.modblocks;

import java.awt.*;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, GenesisMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(modblocks.BATATA_BLOCK);
        blockWithItem(modblocks.BATATA_ENVENENADA_BLOCK);

        blockWithItem(modblocks.BATATA_ORE);

        blockWithItem(modblocks.SOUND_BLOCK);

        stairsBlock(((StairBlock) modblocks.BATATA_STAIRS.get()), blockTexture(modblocks.BATATA_BLOCK.get()));
        slabBlock(((SlabBlock) modblocks.BATATA_SLAB.get()), blockTexture(modblocks.BATATA_BLOCK.get()), blockTexture(modblocks.BATATA_BLOCK.get()));

        buttonBlock(((ButtonBlock) modblocks.BATATA_BUTTON.get()), blockTexture(modblocks.BATATA_BLOCK.get()));
        pressurePlateBlock(((PressurePlateBlock) modblocks.BATATA_PRESSURE_PLATE.get()), blockTexture(modblocks.BATATA_BLOCK.get()));

        fenceBlock(((FenceBlock) modblocks.BATATA_FENCE.get()), blockTexture(modblocks.BATATA_BLOCK.get()));
        fenceGateBlock(((FenceGateBlock) modblocks.BATATA_FENCE_GATE.get()), blockTexture(modblocks.BATATA_BLOCK.get()));
        wallBlock(((WallBlock) modblocks.BATATA_WALL.get()), blockTexture(modblocks.BATATA_BLOCK.get()));

        doorBlockWithRenderType(((DoorBlock) modblocks.BATATA_DOOR.get()), modLoc("block/batata_door_bottom"), modLoc("block/batata_door_top"), "cutout");
        trapdoorBlockWithRenderType(((TrapDoorBlock) modblocks.BATATA_TRAPDOOR.get()), modLoc("block/batata_trapdoor"), true, "cutout");
    }
    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
