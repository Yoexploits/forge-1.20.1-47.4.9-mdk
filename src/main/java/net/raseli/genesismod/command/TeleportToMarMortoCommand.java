package net.raseli.genesismod.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.biome.Biome;
import net.raseli.genesismod.GenesisMod;


public class TeleportToMarMortoCommand {
    
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("tpmar")
                .requires(source -> source.hasPermission(2))
                .executes(context -> teleportToMarMorto(context, context.getSource().getPlayerOrException(), 1000))
                .then(Commands.argument("radius", IntegerArgumentType.integer(100, 10000))
                        .executes(context -> teleportToMarMorto(context, context.getSource().getPlayerOrException(), IntegerArgumentType.getInteger(context, "radius")))
                        .then(Commands.argument("targets", EntityArgument.players())
                                .executes(context -> teleportToMarMorto(context, EntityArgument.getPlayer(context, "targets"), IntegerArgumentType.getInteger(context, "radius"))))));
    }
    
    private static int teleportToMarMorto(CommandContext<CommandSourceStack> context, ServerPlayer player, int radius) throws CommandSyntaxException {
        ServerLevel level = player.serverLevel();
        BlockPos playerPos = player.blockPosition();
        
        // Search for MAR MORTO biome within radius
        BlockPos marMortoPos = findNearestMarMortoBiome(level, playerPos, radius);
        
        if (marMortoPos == null) {
            player.sendSystemMessage(Component.literal("§cNo MAR MORTO biome found within " + radius + " blocks!"));
            return 0;
        }
        
        // Find a safe position to teleport to (on surface)
        BlockPos safePos = findSafeTeleportPosition(level, marMortoPos);
        
        if (safePos == null) {
            player.sendSystemMessage(Component.literal("§cCould not find a safe position to teleport to!"));
            return 0;
        }
        
        // Teleport the player
        player.teleportTo(safePos.getX() + 0.5, safePos.getY(), safePos.getZ() + 0.5);
        player.sendSystemMessage(Component.literal("§aTeleported to MAR MORTO biome at " + safePos.getX() + ", " + safePos.getY() + ", " + safePos.getZ()));
        
        return 1;
    }
    
    private static BlockPos findNearestMarMortoBiome(ServerLevel level, BlockPos center, int radius) {
        
        // Search in a spiral pattern from center outward
        for (int r = 0; r <= radius; r += 16) { // Check every 16 blocks for performance
            for (int x = -r; x <= r; x += 16) {
                for (int z = -r; z <= r; z += 16) {
                    // Only check positions on the perimeter of the current radius
                    if (Math.abs(x) == r || Math.abs(z) == r) {
                        BlockPos checkPos = center.offset(x, 0, z);
                        
                        // Get biome at this position
                        Biome biome = level.getBiome(checkPos).value();
                        
                        // Check if this is the MAR MORTO biome
                        if (level.registryAccess().registry(net.minecraft.core.registries.Registries.BIOME)
                                .map(registry -> registry.getKey(biome))
                                .map(key -> key.toString().equals(GenesisMod.MOD_ID + ":mar_morto"))
                                .orElse(false)) {
                            return checkPos;
                        }
                    }
                }
            }
        }
        
        return null;
    }
    
    private static BlockPos findSafeTeleportPosition(ServerLevel level, BlockPos biomePos) {
        // Search for a safe position near the biome center
        for (int x = -8; x <= 8; x += 2) {
            for (int z = -8; z <= 8; z += 2) {
                BlockPos checkPos = biomePos.offset(x, 0, z);
                
                // Find the surface level
                BlockPos surfacePos = level.getHeightmapPos(net.minecraft.world.level.levelgen.Heightmap.Types.WORLD_SURFACE, checkPos);
                
                // Check if position is safe (not in water, not in lava, solid ground)
                if (isSafePosition(level, surfacePos)) {
                    return surfacePos;
                }
            }
        }
        
        return null;
    }
    
    private static boolean isSafePosition(ServerLevel level, BlockPos pos) {
        // Check if the position is safe to teleport to
        if (level.getBlockState(pos).isAir() && 
            level.getBlockState(pos.below()).canOcclude() &&
            !level.getBlockState(pos.above()).canOcclude()) {
            return true;
        }
        return false;
    }
}
