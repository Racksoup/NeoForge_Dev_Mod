package com.devmod.items.tiers;

import com.devmod.DevMod;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.EnumMap;
import java.util.List;

public class ArmorMageTier {

    public static final DeferredRegister<ArmorMaterial> MAGE_ARMOR_MATERIALS = DeferredRegister.create(Registries.ARMOR_MATERIAL, DevMod.MODID);
    public static final Holder<ArmorMaterial> MAGE_ARMOR =
            MAGE_ARMOR_MATERIALS.register("armor_mage", () -> new ArmorMaterial(
                    Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                        map.put(ArmorItem.Type.BOOTS, 2);
                        map.put(ArmorItem.Type.LEGGINGS, 4);
                        map.put(ArmorItem.Type.CHESTPLATE, 6);
                        map.put(ArmorItem.Type.HELMET, 2);
                        map.put(ArmorItem.Type.BODY, 4);
                    }),
                    20,
                    SoundEvents.ARMOR_EQUIP_GENERIC,
                    () -> Ingredient.of(Tags.Items.INGOTS_COPPER),
                    // This can also be specified by overriding 'IItemExtension#getArmorTexture' on your item if the armor texture needs to be more dynamic
                    List.of(
                            // Creates a new armor texture that will be located at:
                            // - 'assets/mod_id/textures/models/armor/copper_layer_1.png' for the outer texture
                            // - 'assets/mod_id/textures/models/armor/copper_layer_2.png' for the inner texture (only legs)
                            new ArmorMaterial.Layer(
                                    ResourceLocation.fromNamespaceAndPath(DevMod.MODID, "armor_mage")
                            )
                            // Creates a new armor texture that will be rendered on top of the previous at:
                            // - 'assets/mod_id/textures/models/armor/copper_layer_1_overlay.png' for the outer texture
                            // - 'assets/mod_id/textures/models/armor/copper_layer_2_overlay.png' for the inner texture (only legs)
                            // 'true' means that the armor material is dyeable; however, the item must also be added to the 'minecraft:dyeable' tag
//                            new ArmorMaterial.Layer(
//                                    ResourceLocation.fromNamespaceAndPath(DevMod.MODID, "armor_shaman"), "_overlay", false
//                            )
                    ),
                    0,
                    0
            ));
}
