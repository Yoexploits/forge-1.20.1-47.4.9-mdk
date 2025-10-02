package net.raseli.genesismod.capabilities;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ATFieldCapabilityProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    
    public static Capability<ATFieldCapability> AT_FIELD_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {});
    
    private ATFieldCapability atFieldCapability = null;
    private final LazyOptional<ATFieldCapability> optional = LazyOptional.of(this::createCapability);

    private ATFieldCapability createCapability() {
        if (atFieldCapability == null) {
            atFieldCapability = new ATFieldCapability(null);
        }
        return atFieldCapability;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == AT_FIELD_CAPABILITY) {
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        ATFieldCapability capability = createCapability();
        capability.serializeNBT(tag);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createCapability().deserializeNBT(nbt);
    }
}
