package net.raseli.genesismod.entity.client;// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.raseli.genesismod.entity.animations.ModAnimationDefinitions;
import net.raseli.genesismod.entity.custom.PenPenEntity;

public class PenPenModel<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart penpen;
	private final ModelPart body;
	private final ModelPart rightleg;
	private final ModelPart leftleg;
	private final ModelPart leftwing;
	private final ModelPart rightwing;
	private final ModelPart head;
	private final ModelPart beek;

	public PenPenModel(ModelPart root) {
		this.penpen = root.getChild("penpen");
		this.body = this.penpen.getChild("body");
		this.rightleg = this.body.getChild("rightleg");
		this.leftleg = this.body.getChild("leftleg");
		this.leftwing = this.body.getChild("leftwing");
		this.rightwing = this.body.getChild("rightwing");
		this.head = this.penpen.getChild("head");
		this.beek = this.head.getChild("beek");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition penpen = partdefinition.addOrReplaceChild("penpen", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = penpen.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-10.0F, -23.0F, -3.0F, 13.0F, 14.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(30, 20).addBox(-8.0F, -19.0F, -4.0F, 9.0F, 9.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(56, 61).addBox(-7.2813F, -26.0F, -3.1367F, 7.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(3.3086F, 0.0F, 2.0F));

		PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(86, 40).addBox(-4.0F, -6.0F, 0.0F, 4.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.5769F, -20.9026F, -3.0195F, 0.0F, 0.0F, 0.48F));

		PartDefinition cube_r2 = body.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 85).addBox(-4.0F, -6.0F, 0.0F, 4.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.997F, -21.7578F, -3.0898F, 0.0F, 0.0F, -0.3491F));

		PartDefinition rightleg = body.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(48, 77).addBox(-1.6836F, -3.8047F, 1.5742F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(80, 88).addBox(-2.6836F, -9.8047F, 0.5742F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.3867F, -0.1953F, -2.5742F));

		PartDefinition cube_r3 = rightleg.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(48, 82).addBox(-2.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.3672F, -0.0313F, 1.1797F, 0.0F, -0.5672F, 0.0F));

		PartDefinition cube_r4 = rightleg.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(90, 15).addBox(-1.0F, -1.0F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.7539F, 0.0F, 0.1562F, 0.0F, 0.4363F, 0.0F));

		PartDefinition cube_r5 = rightleg.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(90, 10).addBox(-1.0F, -1.0F, -3.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.1992F, -0.0703F, 1.6094F, 0.0F, -0.5672F, 0.0F));

		PartDefinition cube_r6 = rightleg.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(76, 40).addBox(-1.0F, -1.0F, -3.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0781F, -0.082F, 1.0703F, 0.0F, -0.0873F, 0.0F));

		PartDefinition cube_r7 = rightleg.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(52, 17).addBox(-2.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.4363F, 0.0F));

		PartDefinition leftleg = body.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(52, 12).addBox(-1.5313F, -3.8047F, 1.5742F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(48, 89).addBox(-2.6133F, -9.8047F, 0.5742F, 4.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.6133F, -0.1953F, -2.5742F));

		PartDefinition cube_r8 = leftleg.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(90, 35).addBox(-2.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.3672F, -0.0313F, 1.1797F, 0.0F, -0.5672F, 0.0F));

		PartDefinition cube_r9 = leftleg.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(90, 30).addBox(-1.0F, -1.0F, -2.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.7539F, 0.0F, 0.1562F, 0.0F, 0.4363F, 0.0F));

		PartDefinition cube_r10 = leftleg.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(90, 25).addBox(-1.0F, -1.0F, -3.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.1992F, -0.0703F, 1.6094F, 0.0F, -0.5672F, 0.0F));

		PartDefinition cube_r11 = leftleg.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(90, 20).addBox(-1.0F, -1.0F, -3.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0781F, -0.082F, 1.0703F, 0.0F, -0.0873F, 0.0F));

		PartDefinition cube_r12 = leftleg.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(48, 85).addBox(-2.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.4363F, 0.0F));

		PartDefinition leftwing = body.addOrReplaceChild("leftwing", CubeListBuilder.create(), PartPose.offset(11.4219F, -21.2226F, -4.0F));

		PartDefinition leftwing_r1 = leftwing.addOrReplaceChild("leftwing_r1", CubeListBuilder.create().texOffs(30, 35).addBox(-9.0F, -9.0F, 0.0F, 13.0F, 9.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.6581F, 0.0F, 0.3927F));

		PartDefinition rightwing = body.addOrReplaceChild("rightwing", CubeListBuilder.create(), PartPose.offset(-14.5781F, -23.2226F, -4.0F));

		PartDefinition rightwing_r1 = rightwing.addOrReplaceChild("rightwing_r1", CubeListBuilder.create().texOffs(38, 0).addBox(-9.0F, -9.0F, 0.0F, 13.0F, 9.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.6326F, -0.0617F, -0.3908F));

		PartDefinition head = penpen.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 20).addBox(-8.0F, -27.0F, -3.9961F, 7.0F, 10.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 70).addBox(-3.0703F, -24.0F, -4.0F, 4.0F, 7.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(56, 74).addBox(-9.5233F, -24.027F, -4.0F, 4.0F, 7.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0273F, -8.0F, 2.0F));

		PartDefinition cube_r13 = head.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(0, 54).addBox(-4.0F, -6.0F, 0.0F, 4.0F, 6.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0439F, -23.6563F, -4.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition cube_r14 = head.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(70, 0).addBox(-4.0F, -4.0F, 6.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(86, 64).addBox(-4.0F, -6.0F, 6.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.9561F, -26.6523F, -1.7109F, -0.3054F, 0.0F, -0.1745F));

		PartDefinition cube_r15 = head.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(28, 47).addBox(-4.0F, -6.0F, 0.0F, 4.0F, 6.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.9561F, -24.6563F, -4.0F, 0.0F, 0.0F, -0.1745F));

		PartDefinition cube_r16 = head.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(86, 54).addBox(-4.0F, -4.0F, 6.0F, 4.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.7157F, -26.1758F, -1.7109F, -0.2835F, 0.1153F, 0.2017F));

		PartDefinition cube_r17 = head.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(64, 89).addBox(-4.0F, -6.0F, 6.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1806F, -25.7227F, -1.7109F, -0.2666F, 0.1509F, 0.3288F));

		PartDefinition cube_r18 = head.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(24, 77).addBox(-4.0F, -6.0F, 0.0F, 4.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0439F, -23.6563F, -3.9883F, 0.0F, 0.0F, -0.3491F));

		PartDefinition cube_r19 = head.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(80, 74).addBox(-4.0F, -6.0F, 0.0F, 4.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0417F, -22.2698F, -3.9883F, 0.0F, 0.0F, 0.48F));

		PartDefinition beek = head.addOrReplaceChild("beek", CubeListBuilder.create(), PartPose.offset(-1.5147F, -18.9414F, -7.9961F));

		PartDefinition cube_r20 = beek.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(56, 47).addBox(-4.0F, -1.0F, -3.0F, 2.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1601F, 0.4219F, 0.2851F, 0.0F, 0.0F, 0.0087F));

		PartDefinition cube_r21 = beek.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(62, 26).addBox(-3.0F, -1.0F, -3.0F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.6172F, 0.6797F, 0.2851F, 0.0F, 0.0F, 0.2182F));

		PartDefinition cube_r22 = beek.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(38, 12).addBox(-4.0F, -1.0F, 0.0F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.9219F, 0.0F, -0.1758F, 0.0F, 0.3927F, 0.0087F));

		PartDefinition cube_r23 = beek.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(24, 91).addBox(-4.0F, -1.0F, -4.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1601F, -1.4688F, -0.3242F, 0.3054F, 0.0F, 0.0087F));

		PartDefinition cube_r24 = beek.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(28, 63).addBox(-4.0F, -1.0F, -3.0F, 1.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.4258F, -0.3438F, 0.2851F, 0.0F, 0.0F, -0.1309F));

		PartDefinition cube_r25 = beek.addOrReplaceChild("cube_r25", CubeListBuilder.create().texOffs(62, 40).addBox(-4.0F, -1.0F, 0.0F, 2.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0625F, 0.0F, 2.0937F, -0.0087F, -0.3927F, 0.0067F));

		PartDefinition cube_r26 = beek.addOrReplaceChild("cube_r26", CubeListBuilder.create().texOffs(90, 5).addBox(-4.0F, -1.0F, -1.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.793F, 0.0F, 4.8828F, -0.0118F, -0.8203F, 0.012F));

		PartDefinition cube_r27 = beek.addOrReplaceChild("cube_r27", CubeListBuilder.create().texOffs(60, 12).addBox(-4.0F, -2.0F, -3.0F, 2.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1601F, 0.1133F, 0.2851F, 0.0F, 0.0F, 0.0087F));

		PartDefinition cube_r28 = beek.addOrReplaceChild("cube_r28", CubeListBuilder.create().texOffs(90, 0).addBox(-4.0F, -1.0F, 0.0F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.021F, 0.0087F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);

        this.animateWalk(ModAnimationDefinitions.WALK, 0.0f, 0.0f, 2f,2.5f);
        this.animate(((PenPenEntity) entity).idleAnimationState, ModAnimationDefinitions.IDLE, ageInTicks, 1f);
	}
    private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
        pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
        pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

        this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
        this.head.yRot = pHeadPitch * ((float)Math.PI / 180F);
    }

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		penpen.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

    @Override
    public ModelPart root() {
        return penpen;
    }
}