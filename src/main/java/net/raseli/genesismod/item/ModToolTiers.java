package net.raseli.genesismod.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;
import net.raseli.genesismod.GenesisMod;
import net.raseli.genesismod.util.ModTags;

import java.util.List;

public class ModToolTiers {
    public static final Tier BATATA = TierSortingRegistry.registerTier(
            new ForgeTier(5, 1500,5f,4f,25,
                    ModTags.Blocks.NEEDS_BATATA_TOOL, () -> Ingredient.of(ModItems.BATATA.get())),
            ResourceLocation.tryBuild(GenesisMod.MOD_ID, "batata"), List.of(Tiers.NETHERITE), List.of());
}
