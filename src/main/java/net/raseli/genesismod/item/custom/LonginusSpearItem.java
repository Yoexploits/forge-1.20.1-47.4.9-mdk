package net.raseli.genesismod.item.custom;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class LonginusSpearItem extends SwordItem {
    public static final float BASE_DAMAGE = 20.0F;
    public static final float ATTACK_SPEED = -3.0F; // Slow attack speed

    public LonginusSpearItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        if (pAttacker instanceof Player player) {
            performSpearAttack(player, pStack);
        }
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }

    private void performSpearAttack(Player player, ItemStack stack) {
        if (player.level().isClientSide) return;
        
        // Calculate attack range (much larger than normal)
        double range = 8.0; // 8 block range
        double reach = 6.0; // 6 block reach
        
        // Get player's look direction
        Vec3 lookDirection = player.getLookAngle();
        Vec3 startPos = player.getEyePosition();
        Vec3 endPos = startPos.add(lookDirection.scale(range));
        
        // Create bounding box for attack area
        AABB attackBox = new AABB(
            Math.min(startPos.x, endPos.x) - 1.0,
            Math.min(startPos.y, endPos.y) - 1.0,
            Math.min(startPos.z, endPos.z) - 1.0,
            Math.max(startPos.x, endPos.x) + 1.0,
            Math.max(startPos.y, endPos.y) + 1.0,
            Math.max(startPos.z, endPos.z) + 1.0
        );
        
        // Find all entities in the attack area
        List<LivingEntity> entities = player.level().getEntitiesOfClass(LivingEntity.class, attackBox);
        
        // Find the closest entity in range
        LivingEntity closestEntity = null;
        double closestDistance = Double.MAX_VALUE;
        
        for (LivingEntity entity : entities) {
            if (entity != player && entity.isAlive()) {
                double distance = player.distanceTo(entity);
                if (distance <= reach && distance < closestDistance) {
                    closestEntity = entity;
                    closestDistance = distance;
                }
            }
        }
        
        // Attack only the closest entity
        if (closestEntity != null) {
            // Deal damage
            closestEntity.hurt(player.damageSources().playerAttack(player), BASE_DAMAGE);
            
            // Knockback effect
            Vec3 knockback = closestEntity.position().subtract(player.position()).normalize().scale(1.5);
            closestEntity.setDeltaMovement(knockback.x, 0.3, knockback.z);
        }
        
        // Play sound
        player.level().playSound(null, player.getX(), player.getY(), player.getZ(), 
            SoundEvents.TRIDENT_THROW, SoundSource.PLAYERS, 1.0F, 1.0F);
    }
}
