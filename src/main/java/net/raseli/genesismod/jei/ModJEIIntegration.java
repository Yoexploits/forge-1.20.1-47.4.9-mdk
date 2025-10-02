package net.raseli.genesismod.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;
import net.raseli.genesismod.GenesisMod;
import net.raseli.genesismod.jei.BeerRecipeCategory;
import net.raseli.genesismod.recipe.BeerRecipe;
import net.raseli.genesismod.screen.BeerStationScreen;

import java.util.List;

@JeiPlugin
public class ModJEIIntegration implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.tryBuild(GenesisMod.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new BeerRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<BeerRecipe> polishingRecipes = recipeManager.getAllRecipesFor(BeerRecipe.Type.INSTANCE);
        registration.addRecipes(BeerRecipeCategory.BEER_RECIPE_TYPE, polishingRecipes);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(BeerStationScreen.class, 60, 30, 20, 30,
                BeerRecipeCategory.BEER_RECIPE_TYPE);
    }
}