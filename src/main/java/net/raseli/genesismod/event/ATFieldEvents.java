package net.raseli.genesismod.event;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.raseli.genesismod.GenesisMod;
import net.raseli.genesismod.item.ModItems;
import net.raseli.genesismod.player.PlayerATFieldSlot;

@Mod.EventBusSubscriber(modid = GenesisMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ATFieldEvents {
    // Static storage for tracking AT Field block status
    private static final java.util.Map<Player, Boolean> WEAK_ATTACK_BLOCKED = new java.util.concurrent.ConcurrentHashMap<>();

    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent event) {
        if (!(event.getEntity() instanceof Player player)) {
            return;
        }

        // Check if player has AT Field Awakener in server-side custom slot
        boolean hasATFieldItem = PlayerATFieldSlot.get(player).hasATFieldItem();

        // If player has AT Field item, apply damage reduction
        if (hasATFieldItem) {
            float originalDamage = event.getAmount();
            float modifiedDamage = calculateATFieldDamage(originalDamage);
            
            // Check if damage was completely nullified (≤5 damage)
            boolean isCompletelyBlocked = originalDamage <= 5.0f && modifiedDamage == 0.0f;
            
            if (modifiedDamage != originalDamage) {
                event.setAmount(modifiedDamage);
                
                // Play shield sound when AT Field blocks/redirects damage
                if (isCompletelyBlocked) {
                    // For complete blocks, play a more dramatic sound
                    player.playSound(net.minecraft.sounds.SoundEvents.ENDERMAN_TELEPORT, 1.0f, 1.5f);
                } else {
                    // For partial blocks, play shield sound
                    player.playSound(net.minecraft.sounds.SoundEvents.SHIELD_BLOCK, 1.0f, 1.5f);
                }
            }
            
            // If damage is ≤5, also block all status effects (poison, etc.) and reduce knockback
            if (isCompletelyBlocked) {
                blockStatusEffects(event);
                // Mark that this player's next knockback should be nullified
                WEAK_ATTACK_BLOCKED.put(player, true);
            }
        }
    }

    private static float calculateATFieldDamage(float originalDamage) {
        // AT Field damage mitigation logic based on requirements:
        // - 5 damage or less: completely nullified (0%)
        // - 5-20 damage: take half (50%)
        // - 20+ damage: take 80% (weakened by powerful attacks)
        
        if (originalDamage <= 5.0f) {
            // Completely block weak attacks
            return 0.0f;
        } else if (originalDamage <= 20.0f) {
            // Moderate damage reduction
            return originalDamage * 0.5f;
        } else {
            // Powerful attacks overwhelm the AT Field
            return originalDamage * 0.8f;
        }
    }

    @SubscribeEvent
    public static void onLivingKnockBack(LivingKnockBackEvent event) {
        if (!(event.getEntity() instanceof Player player)) {
            return;
        }

        // Check if player has AT Field device in server-side custom slot
        boolean hasATFieldItem = PlayerATFieldSlot.get(player).hasATFieldItem();

        // Reduce knockback when AT Field is active
        if (hasATFieldItem) {
            // Check if this player had a weak attack completely blocked
            if (WEAK_ATTACK_BLOCKED.containsKey(player)) {
                // Completely nullify knockback for weak attacks
                event.setStrength(0.0f);
                WEAK_ATTACK_BLOCKED.remove(player); // Clean up
            } else {
                // Default knockback reduction for regular attacks
                event.setStrength(event.getStrength() * 0.25f);
            }
        }
    }

    private static void blockStatusEffects(LivingDamageEvent event) {
        // Clear harmful status effects for complete AT Field blocks
        if (event.getEntity() instanceof Player player) {
            // Remove common harmful effects that could be applied by attackers
            player.removeEffect(net.minecraft.world.effect.MobEffects.POISON);
            player.removeEffect(net.minecraft.world.effect.MobEffects.WITHER);
            player.removeEffect(net.minecraft.world.effect.MobEffects.HUNGER);
        }
    }

    private static void reduceKnockback(LivingDamageEvent event) {
        // Knockback reduction is handled in the separate LivingKnockBackEvent
        // This method is kept for potential future extension
    }
    
}
