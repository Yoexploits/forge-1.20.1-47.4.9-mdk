package net.raseli.genesismod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

public class SheepSummonBlock extends Block {
    public SheepSummonBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        if (!pLevel.isClientSide() && pEntity instanceof Player) {
            Player player = (Player) pEntity;
            
            // Create a sheep entity
            Sheep sheep = new Sheep(net.minecraft.world.entity.EntityType.SHEEP, pLevel);
            sheep.setPos(pPos.getX() + 0.5, pPos.getY() + 1, pPos.getZ() + 0.5);
            
            // Randomly choose a color and set the name
            Random random = new Random();
            int colorChoice = random.nextInt(3); // 0, 1, or 2
            
            switch (colorChoice) {
                case 0: // Yellow
                    sheep.setColor(DyeColor.YELLOW);
                    sheep.setCustomName(Component.literal("neru"));
                    break;
                case 1: // Red
                    sheep.setColor(DyeColor.RED);
                    sheep.setCustomName(Component.literal("teto"));
                    break;
                case 2: // Blue
                    sheep.setColor(DyeColor.BLUE);
                    sheep.setCustomName(Component.literal("miku"));
                    break;
            }
            
            sheep.setCustomNameVisible(true);
            
            // Add the sheep to the world
            pLevel.addFreshEntity(sheep);
            
            // Play a sound effect
            pLevel.playSound(null, pPos, SoundEvents.SHEEP_AMBIENT, SoundSource.BLOCKS, 1.0f, 1.0f);
        }
        
        super.stepOn(pLevel, pPos, pState, pEntity);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        pTooltip.add(Component.translatable("tooltip.genesis.sheep_summon_block.tooltip"));
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
    }
}
