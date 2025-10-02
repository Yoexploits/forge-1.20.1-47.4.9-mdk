package net.raseli.genesismod.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.raseli.genesismod.GenesisMod;
import net.raseli.genesismod.block.ModBlocks;
import net.raseli.genesismod.item.ModItems;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, GenesisMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.BATATA);
        simpleItem(ModItems.BATATA_ENVENENADA);
        simpleItem(ModItems.DETECTOR_DE_BATATA);
        simpleItem(ModItems.CRUEL_ANGEL_THESIS_MUSIC_DISC);
        simpleItem(ModItems.CRUEL_ANGEL_THESIS_MUSIC_DISC_MONO);
        simpleItem(ModItems.BEER);
        simpleItem(ModItems.FUMO);

        simpleBlockItem(ModBlocks.BATATA_DOOR);

        fenceItem(ModBlocks.BATATA_FENCE, ModBlocks.BATATA_BLOCK);
        buttonItem(ModBlocks.BATATA_BUTTON, ModBlocks.BATATA_BLOCK);
        wallItem(ModBlocks.BATATA_WALL, ModBlocks.BATATA_BLOCK);

        evenSimplerBlockItem(ModBlocks.BATATA_STAIRS);
        evenSimplerBlockItem(ModBlocks.BATATA_SLAB);
        evenSimplerBlockItem(ModBlocks.BATATA_PRESSURE_PLATE);
        evenSimplerBlockItem(ModBlocks.BATATA_FENCE_GATE);

        trapdoorItem(ModBlocks.BATATA_TRAPDOOR);

        handheldItem(ModItems.BATATA_SWORD);
        handheldItem(ModItems.BATATA_PICKAXE);
        handheldItem(ModItems.BATATA_AXE);
        handheldItem(ModItems.BATATA_SHOVEL);
        handheldItem(ModItems.BATATA_HOE);
        handheldItem(ModItems.BATATA_HELMET);
        handheldItem(ModItems.BATATA_CHESTPLATE);
        handheldItem(ModItems.BATATA_LEGGINGS);
        handheldItem(ModItems.BATATA_BOOTS);
        withExistingParent(ModItems.PENPEN_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));

    }
    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.tryParse("item/generated")).texture("layer0",
                ResourceLocation.tryBuild(GenesisMod.MOD_ID, "item/" + item.getId().getPath()));
    }

    public void evenSimplerBlockItem(RegistryObject<Block> block) {
        this.withExistingParent(GenesisMod.MOD_ID + ":" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath()));
    }

    public void trapdoorItem(RegistryObject<Block> block) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath() + "_bottom"));
    }

    public void fenceItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/fence_inventory"))
                .texture("texture", ResourceLocation.tryBuild(GenesisMod.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }
    public void buttonItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/button_inventory"))
                .texture("texture", ResourceLocation.tryBuild(GenesisMod.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }
    public void wallItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/wall_inventory"))
                .texture("wall", ResourceLocation.tryBuild(GenesisMod.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }



    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.tryParse("item/handheld")).texture("layer0",
                ResourceLocation.tryBuild(GenesisMod.MOD_ID, "item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleBlockItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.tryParse("item/generated")).texture("layer0",
                ResourceLocation.tryBuild(GenesisMod.MOD_ID,"item/" + item.getId().getPath()));
    }
}

