package net.raseli.genesismod.client;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.raseli.genesismod.item.ModItems;
import net.raseli.genesismod.capabilities.ATFieldCapabilityProvider;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.raseli.genesismod.GenesisMod;

@Mod.EventBusSubscriber(modid = GenesisMod.MOD_ID, value = net.minecraftforge.api.distmarker.Dist.CLIENT)
public class ExtendedInventoryScreen {
    
    // Store the AT Field item for the custom slot (client-side visual)
    private static ItemStack atFieldSlotItem = ItemStack.EMPTY;

    @SubscribeEvent
    public static void onInventoryScreenRender(ScreenEvent.Render event) {
        if (event.getScreen() instanceof InventoryScreen inventoryScreen) {
            Player player = inventoryScreen.getMinecraft().player;
            if (player != null) {
                renderATFieldSlot(inventoryScreen, event.getGuiGraphics(), player);
            }
        }
    }
    
    @SubscribeEvent
    public static void onInventoryScreenMouseClick(ScreenEvent.MouseButtonPressed.Pre event) {
        if (event.getScreen() instanceof InventoryScreen inventoryScreen) {
            Player player = inventoryScreen.getMinecraft().player;
            if (player != null && event.getButton() == 0) { // Left click
                if (handleATFieldSlotClick(inventoryScreen, event.getMouseX(), event.getMouseY())) {
                    event.setCanceled(true); // Prevent default click behavior if we handled it
                }
            }
        }
    }
    
    private static void renderATFieldSlot(InventoryScreen screen, GuiGraphics guiGraphics, Player player) {
        int leftPos = screen.getGuiLeft();
        int topPos = screen.getGuiTop();
        
        // Position the custom AT Field slot next to helmet slot (left of helmet)
        int atFieldSlotX = leftPos + 77 - 18; // Left of helmet slot
        int atFieldSlotY = topPos + 8; // Same row as helmet
        
        // Render slot background (dark rectangle)
        guiGraphics.fill(atFieldSlotX, atFieldSlotY, atFieldSlotX + 16, atFieldSlotY + 16, 0xFF1A1A1A);
        
        // Render AT Field item if any
        if (!atFieldSlotItem.isEmpty()) {
            guiGraphics.renderItem(atFieldSlotItem, atFieldSlotX + 1, atFieldSlotY + 1);
        }
        
        // Render "AT" label on empty slot
        if (atFieldSlotItem.isEmpty()) {
            guiGraphics.drawString(screen.getMinecraft().font, "AT", atFieldSlotX + 4, atFieldSlotY + 4, 0xFFD700, true);
        }
        
        // Render AT Field status indicator
        player.getCapability(ATFieldCapabilityProvider.AT_FIELD_CAPABILITY).ifPresent(atField -> {
            atField.updateActiveState();
            if (atField.isActive()) {
                guiGraphics.drawString(screen.getMinecraft().font, "§6★ AT FIELD ACTIVE", leftPos + 8, topPos + 50, 0xFFD700, true);
            }
        });
    }
    
    private static boolean handleATFieldSlotClick(InventoryScreen screen, double mouseX, double mouseY) {
        int leftPos = screen.getGuiLeft();
        int topPos = screen.getGuiTop();
        int atFieldSlotX = leftPos + 77 - 18; // Left of helmet slot
        int atFieldSlotY = topPos + 8;
        
        // Check if click is within AT Field slot bounds
        if (mouseX >= atFieldSlotX && mouseX < atFieldSlotX + 16 && 
            mouseY >= atFieldSlotY && mouseY < atFieldSlotY + 16) {
            
            Player player = screen.getMinecraft().player;
            if (player != null) {
                net.minecraft.client.Minecraft minecraft = screen.getMinecraft();
                
                // Get cursor item (what player is holding/carrying)
                ItemStack cursorItem = minecraft.player.inventoryMenu.getCarried();
                
                if (!cursorItem.isEmpty() && cursorItem.getItem() == ModItems.AT_FIELD_AWAKENER.get()) {
                    // Place AT Field Awakener in slot (client-side visual)
                    atFieldSlotItem = cursorItem.copy();
                    
                    // Remove from cursor temporarily for visual
                    minecraft.player.inventoryMenu.setCarried(ItemStack.EMPTY);
                    
                    // Send command to server to confirm placement
                    if (player instanceof net.minecraft.client.player.LocalPlayer localPlayer) {
                        localPlayer.connection.sendCommand("atfield activate");
                    }
                    
                } else if (!atFieldSlotItem.isEmpty()) {
                    // Remove item from slot (client-side visual)
                    ItemStack removedItem = atFieldSlotItem.copy();
                    atFieldSlotItem = ItemStack.EMPTY;
                    
                    // Set cursor item
                    minecraft.player.inventoryMenu.setCarried(removedItem);
                    
                    // Send command to server to deactivate
                    if (player instanceof net.minecraft.client.player.LocalPlayer localPlayer) {
                        localPlayer.connection.sendCommand("atfield deactivate");
                    }
                }
                
                return true; // Indicate we handled the click
            }
        }
        
        return false; // Click was not on our slot
    }
}