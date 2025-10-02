package net.raseli.genesismod.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.raseli.genesismod.player.PlayerATFieldSlot;
import net.raseli.genesismod.item.ModItems;

public class ATFieldCommand {
    
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("atfield")
            .requires(source -> source.hasPermission(0)) // Anyone can use
            .then(Commands.literal("activate")
                .executes(context -> activate(context)))
            .then(Commands.literal("deactivate")
                .executes(context -> deactivate(context))));
    }
    
    private static int activate(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();
        
        if (!(source.getEntity() instanceof ServerPlayer player)) {
            source.sendFailure(Component.literal("This command can only be used by players"));
            return 0;
        }
        
        // Check if player is holding AT Field Awakener
        ItemStack cursorItem = player.inventoryMenu.getCarried();
        
        if (!cursorItem.isEmpty() && cursorItem.getItem() == ModItems.AT_FIELD_AWAKENER.get()) {
            // Place item in server-side AT Field slot
            PlayerATFieldSlot slot = PlayerATFieldSlot.get(player);
            slot.setATFieldItem(cursorItem.copy());
            player.inventoryMenu.setCarried(ItemStack.EMPTY);
            
            source.sendSuccess(() -> Component.literal("§6AT Field Awakener placed in custom slot!"), false);
            return 1;
        } else {
            source.sendFailure(Component.literal("§cYou must hold an AT Field Awakener to place it"));
            return 0;
        }
    }
    
    private static int deactivate(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();
        
        if (!(source.getEntity() instanceof ServerPlayer player)) {
            source.sendFailure(Component.literal("This command can only be used by players"));
            return 0;
        }
        
        // Remove item from server-side AT Field slot
        PlayerATFieldSlot slot = PlayerATFieldSlot.get(player);
        
        if (slot.hasATFieldItem()) {
            ItemStack removedItem = slot.getATFieldItem().copy();
            slot.clearATFieldItem();
            
            // Give item back to player
            player.getInventory().add(removedItem);
            
            source.sendSuccess(() -> Component.literal("§7AT Field Awakener removed from custom slot!"), false);
            return 1;
        } else {
            source.sendFailure(Component.literal("§cNo AT Field Awakener in custom slot"));
            return 0;
        }
    }
}
