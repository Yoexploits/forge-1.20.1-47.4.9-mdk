package net.raseli.genesismod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.raseli.genesismod.GenesisMod;
import net.raseli.genesismod.block.modblocks;
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
                .add(modblocks.BATATA_ORE.get()).addTag(Tags.Blocks.ORES);
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(modblocks.BATATA_ORE.get()

                );
        this.tag(BlockTags.MINEABLE_WITH_AXE)
                .add(modblocks.SOUND_BLOCK.get()

                );
        this.tag(BlockTags.MINEABLE_WITH_HOE)
                .add(modblocks.BATATA_BLOCK.get()

                );

        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(modblocks.BATATA_ORE.get());
        this.tag(BlockTags.NEEDS_DIAMOND_TOOL);

        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(modblocks.BATATA_ORE.get());
        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(modblocks.BATATA_ORE.get());

        this.tag(BlockTags.FENCES)
                .add(modblocks.BATATA_FENCE.get());
        this.tag(BlockTags.FENCE_GATES)
                .add(modblocks.BATATA_FENCE_GATE.get());
        this.tag(ModTags.Blocks.NEEDS_BATATA_TOOL)
                .add(modblocks.SOUND_BLOCK.get());
    }
}
