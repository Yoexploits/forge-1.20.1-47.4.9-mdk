package net.raseli.genesismod.player;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.raseli.genesismod.item.ModItems;

public class PlayerATFieldSlot {
    private static final String AT_FIELD_DATA_KEY = "GenesisModATFieldSlot";
    private static final String AT_FIELD_ITEM_KEY = "atFieldItem";
    
    private ItemStack atFieldItem = ItemStack.EMPTY;
    private final Player player;
    
    public PlayerATFieldSlot(Player player) {
        this.player = player;
        loadFromPlayer();
    }
    
    public void setATFieldItem(ItemStack item) {
        // Only allow AT Field Awakener to be placed
        if (item.isEmpty()) {
            this.atFieldItem = ItemStack.EMPTY;
        } else if (item.getItem() == ModItems.AT_FIELD_AWAKENER.get()) {
            this.atFieldItem = item.copy();
            this.atFieldItem.setCount(1); // Ensure only 1 item
        }
        // Invalid items are ignored
        saveToPlayer();
    }
    
    public ItemStack getATFieldItem() {
        return atFieldItem;
    }
    
    public boolean hasATFieldItem() {
        return !atFieldItem.isEmpty() && atFieldItem.getItem() == ModItems.AT_FIELD_AWAKENER.get();
    }
    
    public void clearATFieldItem() {
        this.atFieldItem = ItemStack.EMPTY;
        saveToPlayer();
    }
    
    private void loadFromPlayer() {
        if (player instanceof ServerPlayer serverPlayer) {
            CompoundTag persistentData = serverPlayer.getPersistentData();
            CompoundTag genesisModData = persistentData.getCompound(AT_FIELD_DATA_KEY);
            
            if (genesisModData.contains(AT_FIELD_ITEM_KEY)) {
                atFieldItem = ItemStack.of(genesisModData.getCompound(AT_FIELD_ITEM_KEY));
            } else {
                atFieldItem = ItemStack.EMPTY;
            }
        }
    }
    
    private void saveToPlayer() {
        if (player instanceof ServerPlayer serverPlayer) {
            CompoundTag persistentData = serverPlayer.getPersistentData();
            CompoundTag genesisModData = persistentData.getCompound(AT_FIELD_DATA_KEY);
            
            CompoundTag itemTag = new CompoundTag();
            atFieldItem.save(itemTag);
            genesisModData.put(AT_FIELD_ITEM_KEY, itemTag);
            
            persistentData.put(AT_FIELD_DATA_KEY, genesisModData);
        }
    }
    
    public static PlayerATFieldSlot get(Player player) {
        return new PlayerATFieldSlot(player);
    }
}
