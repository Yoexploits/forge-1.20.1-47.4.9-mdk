package net.raseli.genesismod.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.animal.Panda;
import net.minecraft.world.level.block.Block;
import net.raseli.genesismod.GenesisMod;

import javax.swing.text.html.HTML;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> BATATA_DETECTOR_VALUABLES = tag("batata_detector_valuables");
        public static final TagKey<Block> NEEDS_BATATA_TOOL = tag("needs_batata_tool");

        private static TagKey<Block> tag (String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(GenesisMod.MOD_ID, name));
        }
    }

    public static class Items {

    }
}
