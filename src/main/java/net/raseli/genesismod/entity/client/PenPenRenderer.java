package net.raseli.genesismod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.raseli.genesismod.GenesisMod;
import net.raseli.genesismod.entity.custom.PenPenEntity;

public class PenPenRenderer extends MobRenderer<PenPenEntity, PenPenModel<PenPenEntity>> {
    public PenPenRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new PenPenModel<>(pContext.bakeLayer(ModModelLayers.PENPEN_LAYER)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(PenPenEntity pEntity) {
        return ResourceLocation.tryBuild(GenesisMod.MOD_ID, "textures/entity/penpen.png");
    }

    @Override
    public void render(PenPenEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
    if(pEntity.isBaby()) {
        pMatrixStack.scale(0.2f, 0.2f, 0.2f);
    } else {
        pMatrixStack.scale(0.5f, 0.5f, 0.5f);
    }

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}
