package net.raseli.genesismod.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.raseli.genesismod.GenesisMod;
import net.raseli.genesismod.entity.ModEntities;
import net.raseli.genesismod.item.custom.BatataStaffItem;
import net.raseli.genesismod.item.custom.DetectorDeBatataItem;
import net.raseli.genesismod.item.custom.FumoItem;
import net.raseli.genesismod.item.custom.LonginusSpearItem;
import net.raseli.genesismod.sound.ModSounds;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, GenesisMod.MOD_ID);

    public static final RegistryObject<Item> BATATA = ITEMS.register("batata",
            () -> new Item(new Item.Properties().food(ModFoods.BATATA)));
    public static final RegistryObject<Item> BATATA_ENVENENADA = ITEMS.register("naobatata",
            () -> new Item(new Item.Properties().food(ModFoods.NAOBATATA)));
    public static final RegistryObject<Item> BEER = ITEMS.register("beer",
            () -> new Item(
                    new HoneyBottleItem.Properties().stacksTo(16).food(
                            (new FoodProperties.Builder())
                                    .nutrition(4)
                                    .saturationMod(0.1F)
                                    .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 100, 0), 0.8F)
                                    .build()
                    )
            )
    );

    public static final RegistryObject<Item> DETECTOR_DE_BATATA = ITEMS.register("detector_batata",
            () -> new DetectorDeBatataItem(new Item.Properties().durability(200)));
    public static final RegistryObject<Item> FUMO = ITEMS.register("fumo",
            () -> new FumoItem(new Item.Properties()));

    public static final RegistryObject<Item> BATATA_STAFF = ITEMS.register("batata_staff",
            () -> new BatataStaffItem(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> LONGINUS_SPEAR = ITEMS.register("longinus_spear",
            () -> new LonginusSpearItem(ModToolTiers.BATATA, 20, -3.0F, new Item.Properties().stacksTo(1).durability(1000)));

    public static final RegistryObject<Item> CRUEL_ANGEL_THESIS_MUSIC_DISC = ITEMS.register("cruel_angel_thesis_music_disc",
            () -> new RecordItem(6, ModSounds.CRUEL_ANGEL_THESIS, new Item.Properties().stacksTo(1), 4920)  );
    public static final RegistryObject<Item> CRUEL_ANGEL_THESIS_MUSIC_DISC_MONO = ITEMS.register("cruel_angel_thesis_music_disc_mono",
            () -> new RecordItem(6, ModSounds.CRUEL_ANGEL_THESIS_MONO, new Item.Properties().stacksTo(1), 4920)  );


    public static final RegistryObject<Item> BATATA_SWORD = ITEMS.register("batata_sword",
            () -> new SwordItem(ModToolTiers.BATATA, 1, 10, new Item.Properties()));
    public static final RegistryObject<Item> BATATA_PICKAXE = ITEMS.register("batata_pickaxe",
            () -> new PickaxeItem(ModToolTiers.BATATA, 1, 1, new Item.Properties()));
    public static final RegistryObject<Item> BATATA_AXE = ITEMS.register("batata_axe",
            () -> new AxeItem(ModToolTiers.BATATA, 2, 7, new Item.Properties()));
    public static final RegistryObject<Item> BATATA_SHOVEL = ITEMS.register("batata_shovel",
            () -> new ShovelItem(ModToolTiers.BATATA, 0, 0, new Item.Properties()));
    public static final RegistryObject<Item> BATATA_HOE = ITEMS.register("batata_hoe",
            () -> new HoeItem(ModToolTiers.BATATA, 0, 0, new Item.Properties()));

    public static final RegistryObject<Item> BATATA_HELMET = ITEMS.register("batata_helmet",
            () -> new ArmorItem(ModArmorMaterials.BATATA, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> BATATA_CHESTPLATE = ITEMS.register("batata_chestplate",
            () -> new ArmorItem(ModArmorMaterials.BATATA, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> BATATA_LEGGINGS = ITEMS.register("batata_leggings",
            () -> new ArmorItem(ModArmorMaterials.BATATA, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> BATATA_BOOTS = ITEMS.register("batata_boots",
            () -> new ArmorItem(ModArmorMaterials.BATATA, ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final RegistryObject<Item> PENPEN_SPAWN_EGG = ITEMS.register("penpen_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.PENPEN, 0x7e9680, 0xc5d1c5,
                    new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
