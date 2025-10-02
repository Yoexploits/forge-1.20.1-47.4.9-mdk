package net.raseli.genesismod.capabilities;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.raseli.genesismod.item.ModItems;

public class ATFieldCapability {
    private boolean isActive = false;
    private final Player player;

    public ATFieldCapability(Player player) {
        this.player = player;
        updateActiveState();
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    public void updateActiveState() {
        // Check the extended AT Field slot (we'll sync this from the client-side GUI)
        this.isActive = isATFieldActiveOnServer();
    }
    
    private boolean isATFieldActiveOnServer() {
        // For now, check if player has AT Field Awakener anywhere in inventory as fallback
        // This will be synced with client-side GUI later
        for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
            ItemStack stack = player.getInventory().getItem(i);
            if (!stack.isEmpty() && stack.getItem() == ModItems.AT_FIELD_AWAKENER.get()) {
                return true;
            }
        }
        return false;
        
        // TODO: Implement proper server-side AT Field slot tracking
        // Right now this is a temporary fallback - we'll implement proper syncing later
    }

    private int getATFieldSlotIndex() {
        // Assuming helmet slot is index 39, AT Field slot would be 40
        return 40;
    }

    public void serializeNBT(CompoundTag nbt) {
        nbt.putBoolean("isActive", isActive);
    }

    public void deserializeNBT(CompoundTag nbt) {
        this.isActive = nbt.getBoolean("isActive");
    }

    public ATFieldCapability copy() {
        ATFieldCapability copy = new ATFieldCapability(player);
        copy.isActive = this.isActive;
        return copy;
    }
}
