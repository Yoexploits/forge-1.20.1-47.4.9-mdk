package net.raseli.genesismod.datagen.loot;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;
import net.raseli.genesismod.block.modblocks;
import net.raseli.genesismod.item.moditems;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(modblocks.BATATA_BLOCK.get());
        this.dropSelf(modblocks.BATATA_ENVENENADA_BLOCK.get());
        this.dropSelf(modblocks.SOUND_BLOCK.get());

        this.dropSelf(modblocks.BATATA_WALL.get());
        this.dropSelf(modblocks.BATATA_FENCE.get());
        this.dropSelf(modblocks.BATATA_FENCE_GATE.get());
        this.dropSelf(modblocks.BATATA_TRAPDOOR.get());
        this.dropSelf(modblocks.BATATA_BUTTON.get());
        this.dropSelf(modblocks.BATATA_STAIRS.get());
        this.dropSelf(modblocks.BATATA_PRESSURE_PLATE.get());

        this.add(modblocks.BATATA_SLAB.get(),
                block -> createSlabItemTable(modblocks.BATATA_SLAB.get()));
        this.add(modblocks.BATATA_DOOR.get(),
                block -> createDoorTable(modblocks.BATATA_DOOR.get()));


        this.add(modblocks.BATATA_ORE.get(),
                block -> createCopperLikeOreDrops(modblocks.BATATA_ORE.get(), moditems.BATATA.get()));

    }

    protected LootTable.Builder createCopperLikeOreDrops(Block pBlock, Item item) {
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return modblocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
