package net.raseli.genesismod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HouseBuilderBlock extends Block {
    public HouseBuilderBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {
        if (!pLevel.isClientSide()) {
            // Find the nearest player
            Player nearestPlayer = pLevel.getNearestPlayer(pPos.getX(), pPos.getY(), pPos.getZ(), 10.0, false);
            if (nearestPlayer != null) {
                buildHouse(pLevel, pPos, nearestPlayer);
            }
        }
        super.onPlace(pState, pLevel, pPos, pOldState, pIsMoving);
    }

    private void buildHouse(Level level, BlockPos centerPos, Player player) {
        // Count available wood in player's inventory
        int woodCount = 0;
        int logCount = 0;
        int plankCount = 0;
        
        for (ItemStack stack : player.getInventory().items) {
            if (stack.getItem() == Items.OAK_LOG || stack.getItem() == Items.BIRCH_LOG || 
                stack.getItem() == Items.SPRUCE_LOG || stack.getItem() == Items.JUNGLE_LOG ||
                stack.getItem() == Items.ACACIA_LOG || stack.getItem() == Items.DARK_OAK_LOG ||
                stack.getItem() == Items.MANGROVE_LOG || stack.getItem() == Items.CHERRY_LOG) {
                logCount += stack.getCount();
            } else if (stack.getItem() == Items.OAK_PLANKS || stack.getItem() == Items.BIRCH_PLANKS ||
                      stack.getItem() == Items.SPRUCE_PLANKS || stack.getItem() == Items.JUNGLE_PLANKS ||
                      stack.getItem() == Items.ACACIA_PLANKS || stack.getItem() == Items.DARK_OAK_PLANKS ||
                      stack.getItem() == Items.MANGROVE_PLANKS || stack.getItem() == Items.CHERRY_PLANKS) {
                plankCount += stack.getCount();
            }
        }
        
        woodCount = logCount + plankCount;
        
        if (woodCount < 10) {
            player.sendSystemMessage(Component.literal("Not enough wood! Need at least 10 wood blocks."));
            return;
        }
        
        // Calculate house size based on available materials
        int houseSize = Math.min(woodCount / 10, 8); // Max 8x8 house
        houseSize = Math.max(houseSize, 3); // Min 3x3 house
        
        // Consume wood from inventory
        consumeWood(player, woodCount);
        
        // Build the house
        buildHouseStructure(level, centerPos, houseSize);
        
        // Play sound and send message
        level.playSound(null, centerPos, SoundEvents.ANVIL_USE, SoundSource.BLOCKS, 1.0f, 1.0f);
        player.sendSystemMessage(Component.literal("Built a " + houseSize + "x" + houseSize + " house using " + woodCount + " wood blocks!"));
    }
    
    private void consumeWood(Player player, int totalWood) {
        int remaining = totalWood;
        
        // First consume planks
        for (ItemStack stack : player.getInventory().items) {
            if (remaining <= 0) break;
            
            if (stack.getItem() == Items.OAK_PLANKS || stack.getItem() == Items.BIRCH_PLANKS ||
                stack.getItem() == Items.SPRUCE_PLANKS || stack.getItem() == Items.JUNGLE_PLANKS ||
                stack.getItem() == Items.ACACIA_PLANKS || stack.getItem() == Items.DARK_OAK_PLANKS ||
                stack.getItem() == Items.MANGROVE_PLANKS || stack.getItem() == Items.CHERRY_PLANKS) {
                
                int consume = Math.min(remaining, stack.getCount());
                stack.shrink(consume);
                remaining -= consume;
            }
        }
        
        // Then consume logs
        for (ItemStack stack : player.getInventory().items) {
            if (remaining <= 0) break;
            
            if (stack.getItem() == Items.OAK_LOG || stack.getItem() == Items.BIRCH_LOG || 
                stack.getItem() == Items.SPRUCE_LOG || stack.getItem() == Items.JUNGLE_LOG ||
                stack.getItem() == Items.ACACIA_LOG || stack.getItem() == Items.DARK_OAK_LOG ||
                stack.getItem() == Items.MANGROVE_LOG || stack.getItem() == Items.CHERRY_LOG) {
                
                int consume = Math.min(remaining, stack.getCount());
                stack.shrink(consume);
                remaining -= consume;
            }
        }
    }
    
    private void buildHouseStructure(Level level, BlockPos centerPos, int size) {
        int halfSize = size / 2;
        
        // Clear area first
        for (int x = -halfSize - 1; x <= halfSize + 1; x++) {
            for (int z = -halfSize - 1; z <= halfSize + 1; z++) {
                for (int y = 1; y <= 4; y++) {
                    BlockPos pos = centerPos.offset(x, y, z);
                    if (level.getBlockState(pos).getBlock() != Blocks.AIR) {
                        level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
                    }
                }
            }
        }
        
        // Build foundation
        for (int x = -halfSize; x <= halfSize; x++) {
            for (int z = -halfSize; z <= halfSize; z++) {
                BlockPos pos = centerPos.offset(x, 0, z);
                level.setBlock(pos, Blocks.OAK_PLANKS.defaultBlockState(), 3);
            }
        }
        
        // Build walls
        for (int y = 1; y <= 3; y++) {
            // North and South walls
            for (int x = -halfSize; x <= halfSize; x++) {
                BlockPos northPos = centerPos.offset(x, y, -halfSize);
                BlockPos southPos = centerPos.offset(x, y, halfSize);
                level.setBlock(northPos, Blocks.OAK_PLANKS.defaultBlockState(), 3);
                level.setBlock(southPos, Blocks.OAK_PLANKS.defaultBlockState(), 3);
            }
            
            // East and West walls
            for (int z = -halfSize; z <= halfSize; z++) {
                BlockPos eastPos = centerPos.offset(halfSize, y, z);
                BlockPos westPos = centerPos.offset(-halfSize, y, z);
                level.setBlock(eastPos, Blocks.OAK_PLANKS.defaultBlockState(), 3);
                level.setBlock(westPos, Blocks.OAK_PLANKS.defaultBlockState(), 3);
            }
        }
        
        // Build roof
        for (int x = -halfSize; x <= halfSize; x++) {
            for (int z = -halfSize; z <= halfSize; z++) {
                BlockPos pos = centerPos.offset(x, 4, z);
                level.setBlock(pos, Blocks.OAK_PLANKS.defaultBlockState(), 3);
            }
        }
        
        // Add door (remove wall blocks)
        BlockPos doorPos = centerPos.offset(0, 1, -halfSize);
        level.setBlock(doorPos, Blocks.AIR.defaultBlockState(), 3);
        level.setBlock(doorPos.above(), Blocks.AIR.defaultBlockState(), 3);
        
        // Add windows
        if (size >= 5) {
            // North wall window
            BlockPos windowPos1 = centerPos.offset(-1, 2, -halfSize);
            BlockPos windowPos2 = centerPos.offset(1, 2, -halfSize);
            level.setBlock(windowPos1, Blocks.AIR.defaultBlockState(), 3);
            level.setBlock(windowPos2, Blocks.AIR.defaultBlockState(), 3);
            
            // South wall window
            BlockPos windowPos3 = centerPos.offset(-1, 2, halfSize);
            BlockPos windowPos4 = centerPos.offset(1, 2, halfSize);
            level.setBlock(windowPos3, Blocks.AIR.defaultBlockState(), 3);
            level.setBlock(windowPos4, Blocks.AIR.defaultBlockState(), 3);
        }
        
        // Add floor
        for (int x = -halfSize + 1; x <= halfSize - 1; x++) {
            for (int z = -halfSize + 1; z <= halfSize - 1; z++) {
                BlockPos pos = centerPos.offset(x, 0, z);
                level.setBlock(pos, Blocks.OAK_PLANKS.defaultBlockState(), 3);
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        pTooltip.add(Component.translatable("tooltip.genesis.house_builder_block.tooltip"));
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
    }
}
