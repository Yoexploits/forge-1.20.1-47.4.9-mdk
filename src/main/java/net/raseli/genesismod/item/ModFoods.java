package net.raseli.genesismod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties BATATA = new FoodProperties.Builder().alwaysEat().meat().nutrition(4).saturationMod(0.4f).effect(() -> new MobEffectInstance(MobEffects.GLOWING, 200), 0.5f).build();
    public static final FoodProperties NAOBATATA = new FoodProperties.Builder().alwaysEat().meat().nutrition(-2).saturationMod(-0.2f).effect(() -> new MobEffectInstance(MobEffects.POISON, 100), 1f).build();
    public static final FoodProperties BEER = new FoodProperties.Builder().alwaysEat().meat().nutrition(7).saturationMod(0.7f).build();
}
