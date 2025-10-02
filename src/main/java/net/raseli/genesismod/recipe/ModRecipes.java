package net.raseli.genesismod.recipe;


import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.raseli.genesismod.GenesisMod;
import net.raseli.genesismod.recipe.BeerRecipe;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, GenesisMod.MOD_ID);
    
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES =
            DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, GenesisMod.MOD_ID);

    public static final RegistryObject<RecipeSerializer<BeerRecipe>> BEER_RECIPE_SERIALIZER =
            SERIALIZERS.register("beer_recipe", () -> BeerRecipe.Serializer.INSTANCE);
    
    public static final RegistryObject<RecipeType<BeerRecipe>> BEER_RECIPE_TYPE =
            RECIPE_TYPES.register("beer_recipe", () -> BeerRecipe.Type.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
        RECIPE_TYPES.register(eventBus);
    }
}