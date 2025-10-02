package net.raseli.genesismod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;
import net.raseli.genesismod.block.ModBlocks;
import net.raseli.genesismod.entity.ModEntities;

import java.util.List;

public class BatataStaffItem extends Item {
    private static final double RAYCAST_DISTANCE = 50.0;
    
    public BatataStaffItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        
        if (!level.isClientSide()) {
            // Check if player is crouching for area transformation
            boolean isCrouching = player.isCrouching();
            
            // Perform raycast to find what the player is looking at
            HitResult hitResult = performRaycast(player, level);
            
            if (hitResult.getType() == HitResult.Type.BLOCK) {
                BlockHitResult blockHit = (BlockHitResult) hitResult;
                BlockPos pos = blockHit.getBlockPos();
                
                if (isCrouching) {
                    // Transform 5x5 area
                    if (transformArea(level, pos)) {
                        level.playSound(null, pos, SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.PLAYERS, 1.0F, 1.0F);
                        return InteractionResultHolder.success(itemStack);
                    }
                } else {
                    // Transform single block
                    BlockState currentState = level.getBlockState(pos);
                    if (transformBlock(level, pos, currentState)) {
                        level.playSound(null, pos, SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.PLAYERS, 1.0F, 1.0F);
                        return InteractionResultHolder.success(itemStack);
                    }
                }
            } else if (hitResult.getType() == HitResult.Type.ENTITY) {
                EntityHitResult entityHit = (EntityHitResult) hitResult;
                Entity targetEntity = entityHit.getEntity();
                
                // Transform the entity (single entity only, no area effect for entities)
                if (transformEntity(level, targetEntity)) {
                    level.playSound(null, targetEntity.getX(), targetEntity.getY(), targetEntity.getZ(), 
                            SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.PLAYERS, 1.0F, 1.0F);
                    return InteractionResultHolder.success(itemStack);
                }
            }
        }
        
        return InteractionResultHolder.pass(itemStack);
    }

    private HitResult performRaycast(Player player, Level level) {
        Vec3 eyePosition = player.getEyePosition();
        Vec3 lookDirection = player.getLookAngle();
        Vec3 endPosition = eyePosition.add(lookDirection.scale(RAYCAST_DISTANCE));
        
        // First try block raycast
        BlockHitResult blockHit = level.clip(new ClipContext(eyePosition, endPosition, 
                ClipContext.Block.OUTLINE, ClipContext.Fluid.NONE, player));
        
        if (blockHit.getType() != HitResult.Type.MISS) {
            // Check if there's an entity in the way
            AABB searchBox = new AABB(eyePosition, endPosition).inflate(1.0);
            List<Entity> entities = level.getEntities(player, searchBox);
            
            Entity closestEntity = null;
            double closestDistance = Double.MAX_VALUE;
            
            for (Entity entity : entities) {
                if (entity.isPickable()) {
                    AABB entityBox = entity.getBoundingBox().inflate(entity.getPickRadius());
                    Vec3 entityHit = entityBox.clip(eyePosition, endPosition).orElse(null);
                    
                    if (entityHit != null) {
                        double distance = eyePosition.distanceToSqr(entityHit);
                        if (distance < closestDistance) {
                            closestDistance = distance;
                            closestEntity = entity;
                        }
                    }
                }
            }
            
            // If we found an entity closer than the block, return entity hit
            if (closestEntity != null) {
                double blockDistance = eyePosition.distanceToSqr(blockHit.getLocation());
                if (closestDistance < blockDistance) {
                    return new EntityHitResult(closestEntity);
                }
            }
        }
        
        return blockHit;
    }

    private boolean transformArea(Level level, BlockPos centerPos) {
        boolean anyTransformed = false;
        
        // Transform a 5x5 area centered on the target block
        for (int x = -2; x <= 2; x++) {
            for (int z = -2; z <= 2; z++) {
                BlockPos pos = centerPos.offset(x, 0, z);
                BlockState currentState = level.getBlockState(pos);
                
                if (transformBlock(level, pos, currentState)) {
                    anyTransformed = true;
                }
            }
        }
        
        return anyTransformed;
    }

    private boolean transformBlock(Level level, BlockPos pos, BlockState currentState) {
        Block currentBlock = currentState.getBlock();
        
        // Don't transform air or already batata blocks
        if (currentBlock == Blocks.AIR || ForgeRegistries.BLOCKS.getKey(currentBlock).getPath().contains("batata")) {
            return false;
        }
        
        Block targetBlock = getBatataEquivalent(currentBlock);
        if (targetBlock != null) {
            // Preserve block state properties where possible
            BlockState newState = targetBlock.defaultBlockState();
            
            // Try to preserve some common properties
            if (currentState.hasProperty(net.minecraft.world.level.block.state.properties.BlockStateProperties.FACING)) {
                newState = newState.setValue(net.minecraft.world.level.block.state.properties.BlockStateProperties.FACING, 
                        currentState.getValue(net.minecraft.world.level.block.state.properties.BlockStateProperties.FACING));
            }
            if (currentState.hasProperty(net.minecraft.world.level.block.state.properties.BlockStateProperties.HORIZONTAL_FACING)) {
                newState = newState.setValue(net.minecraft.world.level.block.state.properties.BlockStateProperties.HORIZONTAL_FACING, 
                        currentState.getValue(net.minecraft.world.level.block.state.properties.BlockStateProperties.HORIZONTAL_FACING));
            }
            if (currentState.hasProperty(net.minecraft.world.level.block.state.properties.BlockStateProperties.OPEN)) {
                newState = newState.setValue(net.minecraft.world.level.block.state.properties.BlockStateProperties.OPEN, 
                        currentState.getValue(net.minecraft.world.level.block.state.properties.BlockStateProperties.OPEN));
            }
            if (currentState.hasProperty(net.minecraft.world.level.block.state.properties.BlockStateProperties.POWERED)) {
                newState = newState.setValue(net.minecraft.world.level.block.state.properties.BlockStateProperties.POWERED, 
                        currentState.getValue(net.minecraft.world.level.block.state.properties.BlockStateProperties.POWERED));
            }
            
            level.setBlock(pos, newState, 3);
            return true;
        }
        
        return false;
    }

    private boolean transformEntity(Level level, Entity entity) {
        // Don't transform players or already PenPen entities
        if (entity instanceof Player || entity.getType() == ModEntities.PENPEN.get()) {
            return false;
        }
        
        if (level instanceof ServerLevel serverLevel) {
            // Create new PenPen entity at the same location
            var penPen = ModEntities.PENPEN.get().create(serverLevel);
            if (penPen != null) {
                penPen.moveTo(entity.getX(), entity.getY(), entity.getZ(), entity.getYRot(), entity.getXRot());
                
                // Copy some properties from the original entity
                if (entity instanceof LivingEntity) {
                    penPen.setHealth(penPen.getMaxHealth());
                }
                
                // Remove the original entity and spawn the new one
                entity.remove(Entity.RemovalReason.DISCARDED);
                serverLevel.addFreshEntity(penPen);
                return true;
            }
        }
        
        return false;
    }

    private Block getBatataEquivalent(Block originalBlock) {
        String blockName = ForgeRegistries.BLOCKS.getKey(originalBlock).getPath().toLowerCase();
        
        // Map common blocks to their batata equivalents
        if (blockName.contains("door")) {
            return ModBlocks.BATATA_DOOR.get();
        } else if (blockName.contains("trapdoor")) {
            return ModBlocks.BATATA_TRAPDOOR.get();
        } else if (blockName.contains("button")) {
            return ModBlocks.BATATA_BUTTON.get();
        } else if (blockName.contains("pressure_plate")) {
            return ModBlocks.BATATA_PRESSURE_PLATE.get();
        } else if (blockName.contains("fence_gate")) {
            return ModBlocks.BATATA_FENCE_GATE.get();
        } else if (blockName.contains("fence")) {
            return ModBlocks.BATATA_FENCE.get();
        } else if (blockName.contains("wall")) {
            return ModBlocks.BATATA_WALL.get();
        } else if (blockName.contains("stairs")) {
            return ModBlocks.BATATA_STAIRS.get();
        } else if (blockName.contains("slab")) {
            return ModBlocks.BATATA_SLAB.get();
        } else {
            // Default to batata block for other blocks
            return ModBlocks.BATATA_BLOCK.get();
        }
    }
}
