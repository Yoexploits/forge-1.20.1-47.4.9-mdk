package net.raseli.genesismod.item;

import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.raseli.genesismod.GenesisMod;
import net.raseli.genesismod.item.custom.DetectorDeBatataItem;
import net.raseli.genesismod.sound.ModSounds;

public class moditems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, GenesisMod.MOD_ID);

    public static final RegistryObject<Item> BATATA = ITEMS.register("batata",
            () -> new Item(new Item.Properties().food(ModFoods.BATATA)));
    public static final RegistryObject<Item> BATATA_ENVENENADA = ITEMS.register("naobatata",
            () -> new Item(new Item.Properties().food(ModFoods.NAOBATATA)));

    public static final RegistryObject<Item> DETECTOR_DE_BATATA = ITEMS.register("detector_batata",
            () -> new DetectorDeBatataItem(new Item.Properties().durability(200)));

    public static final RegistryObject<Item> BATATA_STAFF = ITEMS.register("batata_staff",
            () -> new Item(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> CRUEL_ANGEL_THESIS_MUSIC_DISC = ITEMS.register("cruel_angel_thesis_music_disc",
            () -> new RecordItem(6, ModSounds.CRUEL_ANGEL_THESIS, new Item.Properties().stacksTo(1), 4920));


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


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
