package me.thelethalhamster.radium.util;

import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLineWidth;
import static org.lwjgl.opengl.GL11.glVertex3d;
import me.thelethalhamster.radium.wrapper.RadiumWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

public class GL11Helper {

	public static float[] getColor(final int color) {
        final float alpha = ((color >> 24) & 255) / 255.0F;
        final float red = ((color >> 16) & 255) / 255.0F;
        final float green = ((color >> 8) & 255) / 255.0F;
        final float blue = (color & 255) / 255.0F;
        return new float[] { red, green, blue, alpha };
}

public static void color(final int color) {
        final float alpha = ((color >> 24) & 255) / 255.0F;
        final float red = ((color >> 16) & 255) / 255.0F;
        final float green = ((color >> 8) & 255) / 255.0F;
        final float blue = (color & 255) / 255.0F;
        GL11.glColor4f(red, green, blue, alpha);
}

public static void drawESP(final AxisAlignedBB bb, final double r, final double g, final double b, final double a) {
        Minecraft.getMinecraft().entityRenderer.disableLightmap(0);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glLineWidth(2.0F);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDepthMask(false);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553 /* GL_TEXTURE_2D */);
        GL11.glDisable(2929 /* GL_DEPTH_TEST */);
        GL11.glDepthMask(false);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glColor4d(r, g, b, a);
        drawBoundingBox(bb);
        GL11.glColor4d(r, g, b, 1);
        drawOutlinedBoundingBox(bb);
        GL11.glDepthMask(true);
        GL11.glEnable(3553 /* GL_TEXTURE_2D */);
        GL11.glEnable(2929 /* GL_DEPTH_TEST */);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glColor4d(1, 1, 1, 1);

        Minecraft.getMinecraft().entityRenderer.enableLightmap(0);

}

public static void drawESP(final double d, final double d1, final double d2, final EntityPlayer p) {
        Minecraft.getMinecraft().entityRenderer.disableLightmap(0);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glLineWidth(2.0F);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDepthMask(false);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553 /* GL_TEXTURE_2D */);
        GL11.glDisable(2929 /* GL_DEPTH_TEST */);
        GL11.glDepthMask(false);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glColor4f(1, 1, 1, 1);
        if (p.isSneaking()) {
                color(0xfff62217);
        } else {
                color(0xffff83fa);
        }
        drawOutlinedBoundingBox(AxisAlignedBB.getBoundingBox(d - 0.5D, d1 + 0.10000000000000001D, d2 - 0.5D, d + 0.5D,
                        d1 + 2D, d2 + 0.5D));
        if (p.isSneaking()) {
                color(0x33f62217);
        } else {
                color(0x33aa2da5);
        }
        drawBoundingBox(AxisAlignedBB.getBoundingBox(d - 0.5D, d1 + 0.10000000000000001D, d2 - 0.5D, d + 0.5D, d1 + 2D,
                        d2 + 0.5D));
        GL11.glDepthMask(true);
        GL11.glEnable(3553 /* GL_TEXTURE_2D */);
        GL11.glEnable(2929 /* GL_DEPTH_TEST */);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glColor4f(1, 1, 1, 1);

        Minecraft.getMinecraft().entityRenderer.enableLightmap(0);
}

public static void drawBoundingBox(final AxisAlignedBB axisalignedbb) {
        final Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads(); // starts x
        tessellator.addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
        tessellator.addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
        tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
        tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
        tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
        tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
        tessellator.addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
        tessellator.addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
        tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
        tessellator.addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
        tessellator.addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
        tessellator.addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
        tessellator.addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
        tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
        tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
        tessellator.draw(); // ends x
        tessellator.startDrawingQuads(); // starts y
        tessellator.addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
        tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
        tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
        tessellator.addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
        tessellator.addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
        tessellator.addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
        tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
        tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
        tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
        tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
        tessellator.addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
        tessellator.addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
        tessellator.addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
        tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
        tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
        tessellator.draw(); // ends y
        tessellator.startDrawingQuads(); // starts z
        tessellator.addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
        tessellator.addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
        tessellator.addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
        tessellator.addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
        tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
        tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
        tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
        tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
        tessellator.draw();
        tessellator.startDrawingQuads();
        tessellator.addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.maxZ);
        tessellator.addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.maxZ);
        tessellator.addVertex(axisalignedbb.minX, axisalignedbb.maxY, axisalignedbb.minZ);
        tessellator.addVertex(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ);
        tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.minZ);
        tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.minZ);
        tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.maxY, axisalignedbb.maxZ);
        tessellator.addVertex(axisalignedbb.maxX, axisalignedbb.minY, axisalignedbb.maxZ);
        tessellator.draw(); // ends z
}

public static void drawESP(final double d, final double d1, final double d2, final double r, final double b,
                final double g) {
        Minecraft.getMinecraft().entityRenderer.disableLightmap(0);
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glLineWidth(1.5F);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glColor4d(r, g, b, 0.1825F);
        drawBoundingBox(AxisAlignedBB.getBoundingBox(d, d1, d2, d + 1.0, d1 + 1.0, d2 + 1.0));
        GL11.glColor4d(r, g, b, 1.0F);
        drawOutlinedBoundingBox(AxisAlignedBB.getBoundingBox(d, d1, d2, d + 1.0, d1 + 1.0, d2 + 1.0));
        GL11.glLineWidth(2.0F);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
        Minecraft.getMinecraft().entityRenderer.enableLightmap(0);
}

public static void drawEntityESP(final Entity e, final double r, final double b, final double g, final double a) {

        final AxisAlignedBB bb = AxisAlignedBB.getBoundingBox(e.posX - RenderManager.renderPosX - (e.width / 2), e.posY
                        - RenderManager.renderPosY, e.posZ - RenderManager.renderPosZ - (e.width / 2), (e.posX + (e.width / 2))
                        - RenderManager.renderPosX, (e.posY + e.height) - RenderManager.renderPosY, (e.posZ + (e.width / 2))
                        - RenderManager.renderPosZ);

        /*
         * Damn NPEs... For some reason, ModuleMobESP passes in null bbs, then
         * trys to render it, but it's null, so a huge amount of exceptions get
         * thrown then it crashes because it's already tessellating.
         */
        if (bb == null) {
                return;
        }

        GL11.glPushMatrix();
        Minecraft.getMinecraft().entityRenderer.disableLightmap(0);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glLineWidth(1.5F);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glColor4d(r, g, b, a);
        drawBoundingBox(bb);
        GL11.glColor4d(r, g, b, 1.0F);
        drawOutlinedBoundingBox(bb);
        GL11.glLineWidth(2.0F);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        Minecraft.getMinecraft().entityRenderer.enableLightmap(0);
        GL11.glPopMatrix();
}

public static void drawESP(final double d, final double d1, final double d2, final double r, final double b,
                final double g, final double a) {
        Minecraft.getMinecraft().entityRenderer.disableLightmap(0);
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glLineWidth(1.5F);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glColor4d(r, g, b, a);
        drawBoundingBox(AxisAlignedBB.getBoundingBox(d, d1, d2, d + 1.0, d1 + 1.0, d2 + 1.0));
        GL11.glColor4d(r, g, b, 1.0F);
        drawOutlinedBoundingBox(AxisAlignedBB.getBoundingBox(d, d1, d2, d + 1.0, d1 + 1.0, d2 + 1.0));
        GL11.glLineWidth(2.0F);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
        Minecraft.getMinecraft().entityRenderer.enableLightmap(0);
}

public static void drawESPNoWire(final double d, final double d1, final double d2, final double r, final double b,
                final double g, final double a) {
        Minecraft.getMinecraft().entityRenderer.disableLightmap(0);
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glLineWidth(1.5F);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        GL11.glColor4d(r, g, b, a);
        drawBoundingBox(AxisAlignedBB.getBoundingBox(d, d1, d2, d + 1.0, d1 + 1.0, d2 + 1.0));
        GL11.glLineWidth(2.0F);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glDepthMask(true);
        GL11.glPopMatrix();
        Minecraft.getMinecraft().entityRenderer.enableLightmap(0);
}

public static void drawESP(final double d, final double d1, final double d2, final int c) {
        Minecraft.getMinecraft().entityRenderer.disableLightmap(0);
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glLineWidth(1.5F);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        color(c & (0x33 << 24));
        drawBoundingBox(AxisAlignedBB.getBoundingBox(d, d1, d2, d + 1.0, d1 + 1.0, d2 + 1.0));
        color((0xFF << 24) & c);
        drawOutlinedBoundingBox(AxisAlignedBB.getBoundingBox(d, d1, d2, d + 1.0, d1 + 1.0, d2 + 1.0));
        GL11.glLineWidth(2.0F);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
        Minecraft.getMinecraft().entityRenderer.enableLightmap(0);
}

public static void drawPlayerESP(final double d, final double d1, final double d2, final EntityPlayer e) {
        drawESP(d, d1, d2, e);
}

public static void drawWEESP(final AxisAlignedBB bb, final int c) {
        Minecraft.getMinecraft().entityRenderer.disableLightmap(0);
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glLineWidth(4F);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        Minecraft.getMinecraft().entityRenderer.disableLightmap(0);
        color(c + (0x33 << 24));
        drawBoundingBox(bb);
        color((0xFF << 24) + c);
        drawOutlinedBoundingBox(bb);
        Minecraft.getMinecraft().entityRenderer.enableLightmap(0);
        GL11.glLineWidth(2.0F);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
        Minecraft.getMinecraft().entityRenderer.enableLightmap(0);
}

public static void drawNukerESP(final AxisAlignedBB bb) {
        Minecraft.getMinecraft().entityRenderer.disableLightmap(0);
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glLineWidth(1.5F);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        Minecraft.getMinecraft().entityRenderer.disableLightmap(0);
        GL11.glEnable(2929);
        GL11.glDepthMask(false);
        GL11.glColor4d(0, 0.7, 0, 0.11F);
        drawBoundingBox(bb);
        GL11.glColor4d(0, 0.7, 0, 1.0F);
        drawOutlinedBoundingBox(bb);
        GL11.glLineWidth(2.0F);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_LIGHTING);
        Minecraft.getMinecraft().entityRenderer.enableLightmap(0);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
        Minecraft.getMinecraft().entityRenderer.enableLightmap(0);
}

/**
 * @param par1EntityLivingBase
 *            - Entity to tag
 * @param par2Str
 *            - String to draw
 * @param par3
 *            - x
 * @param par5
 *            - y
 * @param par7
 *            - z
 * @param par9
 *            - Math.sqrt(MAX_DIST);
 */
public static void drawTag(final EntityLivingBase par1EntityLivingBase, final String par2Str, final double par3,
                final double par5, final double par7, final int par9) {
        final double var10 = par1EntityLivingBase.getDistanceSqToEntity(Minecraft.getMinecraft().thePlayer);

        if (var10 <= (par9 * par9)) {

                Minecraft.getMinecraft().entityRenderer.disableLightmap(0);

                final FontRenderer var12 = RadiumWrapper.getInstance().getMinecraft().fontRenderer;
                float var13 = (float) (var10 / 15);
                if (var13 < 1) {
                        var13 = 1;
                } else if (var13 > par9) {
                        var13 = par9;
                }
                var13 *= 0.1;
                float var14 = 0.016666668F * var13;
                if (var14 < 0.05F) {
                        var14 = 0.05F;
                }

                GL11.glPushMatrix();
                GL11.glTranslated((par3 - RenderManager.renderPosX),
                                ((par5 + par1EntityLivingBase.height + 0.5) - RenderManager.renderPosY),
                                (par7 - RenderManager.renderPosZ));
                GL11.glNormal3f(0.0F, 1.0F, 0.0F);
                GL11.glRotated(-Minecraft.getMinecraft().thePlayer.rotationYaw, 0.0F, 1.0F, 0.0F);
                GL11.glRotated(Minecraft.getMinecraft().thePlayer.rotationPitch, 1.0F, 0.0F, 0.0F);

                GL11.glScaled(-var14, -var14, var14);

                GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glDepthMask(false);
                GL11.glDisable(GL11.GL_DEPTH_TEST);
                GL11.glEnable(GL11.GL_BLEND);
                GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                final Tessellator var15 = Tessellator.instance;
                byte var16 = 0;

                if (par2Str.equals("deadmau5")) {
                        var16 = -10;
                }

                GL11.glDisable(GL11.GL_TEXTURE_2D);
                var15.startDrawingQuads();
                final int var17 = var12.getStringWidth(par2Str) / 2;
                var15.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.25F);
                var15.addVertex(-var17 - 1, -1 + var16, 0.0D);
                var15.addVertex(-var17 - 1, 8 + var16, 0.0D);
                var15.addVertex(var17 + 1, 8 + var16, 0.0D);
                var15.addVertex(var17 + 1, -1 + var16, 0.0D);
                var15.draw();
                GL11.glEnable(GL11.GL_TEXTURE_2D);
                var12.drawString(par2Str, -var12.getStringWidth(par2Str) / 2, var16, 553648127);
                GL11.glEnable(GL11.GL_DEPTH_TEST);
                GL11.glDepthMask(true);
                var12.drawString(par2Str, -var12.getStringWidth(par2Str) / 2, var16, -1);
                GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glDisable(GL11.GL_BLEND);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                GL11.glPopMatrix();
                Minecraft.getMinecraft().entityRenderer.enableLightmap(0);
        }
}

/**
 * Draws lines for the edges of the bounding box.
 */
public static void drawOutlinedBoundingBox(final AxisAlignedBB par1AxisAlignedBB) {
        final Tessellator var2 = Tessellator.instance;
        var2.startDrawing(3);
        var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY, par1AxisAlignedBB.minZ);
        var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.minY, par1AxisAlignedBB.minZ);
        var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.minY, par1AxisAlignedBB.maxZ);
        var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY, par1AxisAlignedBB.maxZ);
        var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY, par1AxisAlignedBB.minZ);
        var2.draw();
        var2.startDrawing(3);
        var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.minZ);
        var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.minZ);
        var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.maxZ);
        var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.maxZ);
        var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.minZ);
        var2.draw();
        var2.startDrawing(1);
        var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY, par1AxisAlignedBB.minZ);
        var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.minZ);
        var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.minY, par1AxisAlignedBB.minZ);
        var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.minZ);
        var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.minY, par1AxisAlignedBB.maxZ);
        var2.addVertex(par1AxisAlignedBB.maxX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.maxZ);
        var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.minY, par1AxisAlignedBB.maxZ);
        var2.addVertex(par1AxisAlignedBB.minX, par1AxisAlignedBB.maxY, par1AxisAlignedBB.maxZ);
        var2.draw();
}

public static void drawLine(final double x, final double y, final double z, final double x2, final double y2,
                final double z2, final int col) {
        color(col);
        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex3d(x, y, z);
        GL11.glEnd();
        color(0xffffffff);
}
	
	public static void drawBlockESP(double x, double y, double z, float r, float g, float b) {
		EntityPlayerSP ep = RadiumWrapper.getInstance().getMinecraft().thePlayer;
		double d = ep.lastTickPosX + (ep.posX - ep.lastTickPosX) * (double) RadiumWrapper.getInstance().getMinecraft().timer.renderPartialTicks;
		double d1 = ep.lastTickPosY + (ep.posY - ep.lastTickPosY) * (double) RadiumWrapper.getInstance().getMinecraft().timer.renderPartialTicks;
		double d2 = ep.lastTickPosZ + (ep.posZ - ep.lastTickPosZ) * (double) RadiumWrapper.getInstance().getMinecraft().timer.renderPartialTicks;
		double d3 = x - d;
		double d4 = y - d1;
		double d5 = z - d2;
		GL11.glPushMatrix();
		GL11.glEnable(3042 /* GL_BLEND */);
		GL11.glDisable(3553 /* GL_TEXTURE_2D */);
		GL11.glDisable(2896 /* GL_LIGHTING */);
		GL11.glDisable(2929 /* GL_DEPTH_TEST */);
		GL11.glDepthMask(false);
		GL11.glBlendFunc(770, 771);
		GL11.glEnable(2848 /* GL_LINE_SMOOTH */);
		GL11.glColor4f(r, g, b, 0.25F);
		drawBox(d3, d4, d5, d3 + 1, d4 + 1, d5 + 1);
		GL11.glColor4f(r, g, b, 0.15F);
		drawOutlinedBox(d3, d4, d5, d3 + 1, d4 + 1, d5 + 1, 1.6F);
		GL11.glDisable(2848 /* GL_LINE_SMOOTH */);
		GL11.glDepthMask(true);
		GL11.glEnable(2929 /* GL_DEPTH_TEST */);
		GL11.glEnable(3553 /* GL_TEXTURE_2D */);
		GL11.glEnable(2896 /* GL_LIGHTING */);
		GL11.glDisable(3042 /* GL_BLEND */);
		GL11.glPopMatrix();
	}

	public static void drawNukerESP(double x, double y, double z, float r, float g, float b) {
		EntityPlayerSP ep = RadiumWrapper.getInstance().getMinecraft().thePlayer;
		double d = ep.lastTickPosX + (ep.posX - ep.lastTickPosX) * RadiumWrapper.getInstance().getMinecraft().timer.renderPartialTicks;
		double d1 = ep.lastTickPosY + (ep.posY - ep.lastTickPosY) * RadiumWrapper.getInstance().getMinecraft().timer.renderPartialTicks;
		double d2 = ep.lastTickPosZ + (ep.posZ - ep.lastTickPosZ) * RadiumWrapper.getInstance().getMinecraft().timer.renderPartialTicks;
		double d3 = x - d;
		double d4 = y - d1;
		double d5 = z - d2;
		GL11.glPushMatrix();
		GL11.glDisable(2929);
		GL11.glDepthMask(false);
		GL11.glEnable(3042);
		GL11.glDisable(3553);
		GL11.glEnable(2884);
		GL11.glCullFace(1029);
		GL11.glBlendFunc(770, 771);
		GL11.glLineWidth(1.0F);
		GL11.glColor4f(r, g, b, 0.15F);
		drawBox(d3, d4, d5, d3 + 1, d4 + 1, d5 + 1);
		GL11.glColor4f(r, g, b, 0.15F);
		drawOutlinedBox(d3, d4, d5, d3 + 1, d4 + 1, d5 + 1, 1.6F);
		GL11.glDepthMask(true);
		GL11.glDisable(3042);
		GL11.glEnable(3553);
		GL11.glDisable(2884);
		GL11.glEnable(2929);
		GL11.glDisable(2848);
		GL11.glPopMatrix();
	}

	public static void drawBox(double x, double y, double z, double x2, double y2, double z2) {
		glBegin(GL_QUADS);
		glVertex3d(x, y, z);
		glVertex3d(x, y2, z);
		glVertex3d(x2, y, z);
		glVertex3d(x2, y2, z);
		glVertex3d(x2, y, z2);
		glVertex3d(x2, y2, z2);
		glVertex3d(x, y, z2);
		glVertex3d(x, y2, z2);
		glEnd();

		glBegin(GL_QUADS);
		glVertex3d(x2, y2, z);
		glVertex3d(x2, y, z);
		glVertex3d(x, y2, z);
		glVertex3d(x, y, z);
		glVertex3d(x, y2, z2);
		glVertex3d(x, y, z2);
		glVertex3d(x2, y2, z2);
		glVertex3d(x2, y, z2);
		glEnd();

		glBegin(GL_QUADS);
		glVertex3d(x, y2, z);
		glVertex3d(x2, y2, z);
		glVertex3d(x2, y2, z2);
		glVertex3d(x, y2, z2);
		glVertex3d(x, y2, z);
		glVertex3d(x, y2, z2);
		glVertex3d(x2, y2, z2);
		glVertex3d(x2, y2, z);
		glEnd();

		glBegin(GL_QUADS);
		glVertex3d(x, y, z);
		glVertex3d(x2, y, z);
		glVertex3d(x2, y, z2);
		glVertex3d(x, y, z2);
		glVertex3d(x, y, z);
		glVertex3d(x, y, z2);
		glVertex3d(x2, y, z2);
		glVertex3d(x2, y, z);
		glEnd();

		glBegin(GL_QUADS);
		glVertex3d(x, y, z);
		glVertex3d(x, y2, z);
		glVertex3d(x, y, z2);
		glVertex3d(x, y2, z2);
		glVertex3d(x2, y, z2);
		glVertex3d(x2, y2, z2);
		glVertex3d(x2, y, z);
		glVertex3d(x2, y2, z);
		glEnd();

		glBegin(GL_QUADS);
		glVertex3d(x, y2, z2);
		glVertex3d(x, y, z2);
		glVertex3d(x, y2, z);
		glVertex3d(x, y, z);
		glVertex3d(x2, y2, z);
		glVertex3d(x2, y, z);
		glVertex3d(x2, y2, z2);
		glVertex3d(x2, y, z2);
		glEnd();
	}

	public static void drawOutlinedBox(double x, double y, double z, double x2, double y2, double z2, float l1) {
		glLineWidth(l1);

		glBegin(GL_LINES);
		glVertex3d(x, y, z);
		glVertex3d(x, y2, z);
		glVertex3d(x2, y, z);
		glVertex3d(x2, y2, z);
		glVertex3d(x2, y, z2);
		glVertex3d(x2, y2, z2);
		glVertex3d(x, y, z2);
		glVertex3d(x, y2, z2);
		glEnd();

		glBegin(GL_LINES);
		glVertex3d(x2, y2, z);
		glVertex3d(x2, y, z);
		glVertex3d(x, y2, z);
		glVertex3d(x, y, z);
		glVertex3d(x, y2, z2);
		glVertex3d(x, y, z2);
		glVertex3d(x2, y2, z2);
		glVertex3d(x2, y, z2);
		glEnd();

		glBegin(GL_LINES);
		glVertex3d(x, y2, z);
		glVertex3d(x2, y2, z);
		glVertex3d(x2, y2, z2);
		glVertex3d(x, y2, z2);
		glVertex3d(x, y2, z);
		glVertex3d(x, y2, z2);
		glVertex3d(x2, y2, z2);
		glVertex3d(x2, y2, z);
		glEnd();

		glBegin(GL_LINES);
		glVertex3d(x, y, z);
		glVertex3d(x2, y, z);
		glVertex3d(x2, y, z2);
		glVertex3d(x, y, z2);
		glVertex3d(x, y, z);
		glVertex3d(x, y, z2);
		glVertex3d(x2, y, z2);
		glVertex3d(x2, y, z);
		glEnd();

		glBegin(GL_LINES);
		glVertex3d(x, y, z);
		glVertex3d(x, y2, z);
		glVertex3d(x, y, z2);
		glVertex3d(x, y2, z2);
		glVertex3d(x2, y, z2);
		glVertex3d(x2, y2, z2);
		glVertex3d(x2, y, z);
		glVertex3d(x2, y2, z);
		glEnd();

		glBegin(GL_LINES);
		glVertex3d(x, y2, z2);
		glVertex3d(x, y, z2);
		glVertex3d(x, y2, z);
		glVertex3d(x, y, z);
		glVertex3d(x2, y2, z);
		glVertex3d(x2, y, z);
		glVertex3d(x2, y2, z2);
		glVertex3d(x2, y, z2);
		glEnd();
	}

	public static void enableDefaults() {
		RadiumWrapper.getInstance().getMinecraft().entityRenderer.disableLightmap(0.0D);
		GL11.glEnable(3042);
		GL11.glDisable(2896);
		GL11.glDisable(2929);
		GL11.glEnable(2848);
		GL11.glDisable(3553);
		GL11.glHint(3154, 4354);
		GL11.glBlendFunc(770, 771);
		GL11.glEnable(32925);
		GL11.glEnable(32926);
		GL11.glShadeModel(7425);
		GL11.glLineWidth(1.8F);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_LINE_SMOOTH);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glHint(GL11.GL_LINE_SMOOTH_HINT, GL11.GL_NICEST);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glEnable(GL13.GL_MULTISAMPLE);
		GL11.glEnable(GL13.GL_SAMPLE_ALPHA_TO_COVERAGE);
		GL11.glShadeModel(GL11.GL_SMOOTH);
		GL11.glDepthMask(false);
	}

	public static void disableDefaults() {
		GL11.glDisable(3042);
		GL11.glEnable(3553);
		GL11.glDisable(2848);
		GL11.glEnable(2896);
		GL11.glEnable(2929);
		GL11.glDisable(32925);
		GL11.glDisable(32926);
		GL11.glDepthMask(true);
		GL11.glDisable(GL13.GL_SAMPLE_ALPHA_TO_COVERAGE);
		GL11.glDisable(GL13.GL_MULTISAMPLE);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_LINE_SMOOTH);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_BLEND);
		RadiumWrapper.getInstance().getMinecraft().entityRenderer.enableLightmap(0.0D);
	}

	public static void lines(AxisAlignedBB ax) {
		GL11.glLineWidth(1.8F);
		GL11.glPushMatrix();
		GL11.glBegin(GL11.GL_LINES);
		GL11.glVertex3d(ax.minX, ax.maxY, ax.minZ);
		GL11.glVertex3d(ax.minX, ax.minY, ax.maxZ);
		GL11.glEnd();
		GL11.glBegin(GL11.GL_LINES);
		GL11.glVertex3d(ax.maxX, ax.maxY, ax.minZ);
		GL11.glVertex3d(ax.maxX, ax.minY, ax.maxZ);
		GL11.glEnd();
		GL11.glBegin(GL11.GL_LINES);
		GL11.glVertex3d(ax.minX, ax.maxY, ax.minZ);
		GL11.glVertex3d(ax.maxX, ax.maxY, ax.maxZ);
		GL11.glEnd();
		GL11.glBegin(GL11.GL_LINES);
		GL11.glVertex3d(ax.maxX, ax.minY, ax.maxZ);
		GL11.glVertex3d(ax.minX, ax.maxY, ax.maxZ);
		GL11.glEnd();
		GL11.glBegin(GL11.GL_LINES);
		GL11.glVertex3d(ax.maxX, ax.maxY, ax.minZ);
		GL11.glVertex3d(ax.minX, ax.minY, ax.minZ);
		GL11.glEnd();
		GL11.glBegin(GL11.GL_LINES);
		GL11.glVertex3d(ax.maxX, ax.minY, ax.maxZ);
		GL11.glVertex3d(ax.minX, ax.minY, ax.minZ);
		GL11.glEnd();
		GL11.glPopMatrix();
	}

	public static void drawCrossedOutlinedBoundingBox(AxisAlignedBB var0) {
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawing(3);
		tessellator.addVertex(var0.minX, var0.minY, var0.minZ);
		tessellator.addVertex(var0.maxX, var0.minY, var0.minZ);
		tessellator.addVertex(var0.maxX, var0.minY, var0.maxZ);
		tessellator.addVertex(var0.minX, var0.minY, var0.maxZ);
		tessellator.addVertex(var0.minX, var0.minY, var0.minZ);
		tessellator.draw();
		tessellator.startDrawing(3);
		tessellator.addVertex(var0.minX, var0.maxY, var0.minZ);
		tessellator.addVertex(var0.maxX, var0.maxY, var0.minZ);
		tessellator.addVertex(var0.maxX, var0.maxY, var0.maxZ);
		tessellator.addVertex(var0.minX, var0.maxY, var0.maxZ);
		tessellator.addVertex(var0.minX, var0.maxY, var0.minZ);
		tessellator.draw();
		tessellator.startDrawing(1);
		tessellator.addVertex(var0.minX, var0.minY, var0.minZ);
		tessellator.addVertex(var0.minX, var0.maxY, var0.minZ);
		tessellator.addVertex(var0.maxX, var0.minY, var0.minZ);
		tessellator.addVertex(var0.maxX, var0.maxY, var0.minZ);
		tessellator.addVertex(var0.maxX, var0.minY, var0.maxZ);
		tessellator.addVertex(var0.maxX, var0.maxY, var0.maxZ);
		tessellator.addVertex(var0.minX, var0.minY, var0.maxZ);
		tessellator.addVertex(var0.minX, var0.maxY, var0.maxZ);
		tessellator.addVertex(var0.minX, var0.minY, var0.minZ);
		tessellator.addVertex(var0.minX, var0.maxY, var0.maxZ);
		tessellator.addVertex(var0.maxX, var0.minY, var0.minZ);
		tessellator.addVertex(var0.maxX, var0.maxY, var0.maxZ);
		tessellator.draw();
	}
}
