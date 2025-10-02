package net.raseli.genesismod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.raseli.genesismod.GenesisMod;
import net.raseli.genesismod.block.ModBlocks;
import net.raseli.genesismod.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, GenesisMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ModTags.Blocks.BATATA_DETECTOR_VALUABLES)
                .add(ModBlocks.BATATA_ORE.get()).addTag(Tags.Blocks.ORES);
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.BATATA_ORE.get(),
                        ModBlocks.SHEEP_SUMMON_BLOCK.get(),
                        ModBlocks.ADAM_BLOCK.get()
                );
        this.tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.SOUND_BLOCK.get()

                );
        this.tag(BlockTags.MINEABLE_WITH_HOE)
                .add(ModBlocks.BATATA_BLOCK.get()

                );

        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.BATATA_ORE.get());
        this.tag(BlockTags.NEEDS_DIAMOND_TOOL);

        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.BATATA_ORE.get());
        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.BATATA_ORE.get());
        this.tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.ADAM_BLOCK.get());

        this.tag(BlockTags.FENCES)
                .add(ModBlocks.BATATA_FENCE.get());
        this.tag(BlockTags.FENCE_GATES)
                .add(ModBlocks.BATATA_FENCE_GATE.get());
        this.tag(ModTags.Blocks.NEEDS_BATATA_TOOL)
                .add(ModBlocks.SOUND_BLOCK.get());
    }
}
