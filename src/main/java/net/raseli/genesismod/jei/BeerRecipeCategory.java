package net.raseli.genesismod.jei;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.raseli.genesismod.GenesisMod;
import net.raseli.genesismod.block.ModBlocks;
import net.raseli.genesismod.recipe.BeerRecipe;

public class BeerRecipeCategory implements IRecipeCategory<BeerRecipe> {
    public static final ResourceLocation UID = ResourceLocation.tryBuild(GenesisMod.MOD_ID, "beer_recipe");
    public static final ResourceLocation TEXTURE = ResourceLocation.tryBuild(GenesisMod.MOD_ID,
            "textures/gui/beer_station.png");

    public static final RecipeType<BeerRecipe> BEER_RECIPE_TYPE =
            new RecipeType<>(UID, BeerRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public BeerRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.BEER_STATION.get()));
    }

    @Override
    public RecipeType<BeerRecipe> getRecipeType() {
        return BEER_RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.tutorialmod.gem_polishing_station");
    }

    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, BeerRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 80, 11).addIngredients(recipe.getIngredients().get(0));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 80, 59).addItemStack(recipe.getResultItem(null));
    }
}