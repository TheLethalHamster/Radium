package me.thelethalhamster.radium.util;

import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import org.lwjgl.opengl.GL11;

public class RenderUtil
  extends Gui
{
  private static RenderItem itemRenderer = new RenderItem();
  
  public static void drawOutlinedBoundingBox(AxisAlignedBB aa)
  {
    Tessellator t = Tessellator.instance;
    t.startDrawing(3);
    t.addVertex(aa.minX, aa.minY, aa.minZ);
    t.addVertex(aa.maxX, aa.minY, aa.minZ);
    t.addVertex(aa.maxX, aa.minY, aa.maxZ);
    t.addVertex(aa.minX, aa.minY, aa.maxZ);
    t.addVertex(aa.minX, aa.minY, aa.minZ);
    t.draw();
    t.startDrawing(3);
    t.addVertex(aa.minX, aa.maxY, aa.minZ);
    t.addVertex(aa.maxX, aa.maxY, aa.minZ);
    t.addVertex(aa.maxX, aa.maxY, aa.maxZ);
    t.addVertex(aa.minX, aa.maxY, aa.maxZ);
    t.addVertex(aa.minX, aa.maxY, aa.minZ);
    t.draw();
    t.startDrawing(1);
    t.addVertex(aa.minX, aa.minY, aa.minZ);
    t.addVertex(aa.minX, aa.maxY, aa.minZ);
    t.addVertex(aa.maxX, aa.minY, aa.minZ);
    t.addVertex(aa.maxX, aa.maxY, aa.minZ);
    t.addVertex(aa.maxX, aa.minY, aa.maxZ);
    t.addVertex(aa.maxX, aa.maxY, aa.maxZ);
    t.addVertex(aa.minX, aa.minY, aa.maxZ);
    t.addVertex(aa.minX, aa.maxY, aa.maxZ);
    t.draw();
  }
  
  public static void drawBoundingBox(AxisAlignedBB aa)
  {
    Tessellator t = Tessellator.instance;
    t.startDrawingQuads();
    t.addVertex(aa.minX, aa.minY, aa.minZ);
    t.addVertex(aa.minX, aa.maxY, aa.minZ);
    t.addVertex(aa.maxX, aa.minY, aa.minZ);
    t.addVertex(aa.maxX, aa.maxY, aa.minZ);
    t.addVertex(aa.maxX, aa.minY, aa.maxZ);
    t.addVertex(aa.maxX, aa.maxY, aa.maxZ);
    t.addVertex(aa.minX, aa.minY, aa.maxZ);
    t.addVertex(aa.minX, aa.maxY, aa.maxZ);
    t.draw();
    t.startDrawingQuads();
    t.addVertex(aa.maxX, aa.maxY, aa.minZ);
    t.addVertex(aa.maxX, aa.minY, aa.minZ);
    t.addVertex(aa.minX, aa.maxY, aa.minZ);
    t.addVertex(aa.minX, aa.minY, aa.minZ);
    t.addVertex(aa.minX, aa.maxY, aa.maxZ);
    t.addVertex(aa.minX, aa.minY, aa.maxZ);
    t.addVertex(aa.maxX, aa.maxY, aa.maxZ);
    t.addVertex(aa.maxX, aa.minY, aa.maxZ);
    t.draw();
    t.startDrawingQuads();
    t.addVertex(aa.minX, aa.maxY, aa.minZ);
    t.addVertex(aa.maxX, aa.maxY, aa.minZ);
    t.addVertex(aa.maxX, aa.maxY, aa.maxZ);
    t.addVertex(aa.minX, aa.maxY, aa.maxZ);
    t.addVertex(aa.minX, aa.maxY, aa.minZ);
    t.addVertex(aa.minX, aa.maxY, aa.maxZ);
    t.addVertex(aa.maxX, aa.maxY, aa.maxZ);
    t.addVertex(aa.maxX, aa.maxY, aa.minZ);
    t.draw();
    t.startDrawingQuads();
    t.addVertex(aa.minX, aa.minY, aa.minZ);
    t.addVertex(aa.maxX, aa.minY, aa.minZ);
    t.addVertex(aa.maxX, aa.minY, aa.maxZ);
    t.addVertex(aa.minX, aa.minY, aa.maxZ);
    t.addVertex(aa.minX, aa.minY, aa.minZ);
    t.addVertex(aa.minX, aa.minY, aa.maxZ);
    t.addVertex(aa.maxX, aa.minY, aa.maxZ);
    t.addVertex(aa.maxX, aa.minY, aa.minZ);
    t.draw();
    t.startDrawingQuads();
    t.addVertex(aa.minX, aa.minY, aa.minZ);
    t.addVertex(aa.minX, aa.maxY, aa.minZ);
    t.addVertex(aa.minX, aa.minY, aa.maxZ);
    t.addVertex(aa.minX, aa.maxY, aa.maxZ);
    t.addVertex(aa.maxX, aa.minY, aa.maxZ);
    t.addVertex(aa.maxX, aa.maxY, aa.maxZ);
    t.addVertex(aa.maxX, aa.minY, aa.minZ);
    t.addVertex(aa.maxX, aa.maxY, aa.minZ);
    t.draw();
    t.startDrawingQuads();
    t.addVertex(aa.minX, aa.maxY, aa.maxZ);
    t.addVertex(aa.minX, aa.minY, aa.maxZ);
    t.addVertex(aa.minX, aa.maxY, aa.minZ);
    t.addVertex(aa.minX, aa.minY, aa.minZ);
    t.addVertex(aa.maxX, aa.maxY, aa.minZ);
    t.addVertex(aa.maxX, aa.minY, aa.minZ);
    t.addVertex(aa.maxX, aa.maxY, aa.maxZ);
    t.addVertex(aa.maxX, aa.minY, aa.maxZ);
    t.draw();
  }
  
  public static void drawESP(double d, double d1, double d2, double r, double b, double g, double alpha)
  {
    GL11.glPushMatrix();
    GL11.glEnable(3042);
    GL11.glBlendFunc(770, 771);
    GL11.glLineWidth(1.0F);
    GL11.glDisable(2896);
    GL11.glDisable(3553);
    GL11.glEnable(2848);
    GL11.glDisable(2929);
    GL11.glDepthMask(false);
    GL11.glColor4d(r, g, b, alpha);
    drawBoundingBox(new AxisAlignedBB(d, d1, d2, d + 1.0D, d1 + 1.0D, d2 + 1.0D));
    GL11.glColor4d(r, g, b, 1.0D);
    drawOutlinedBoundingBox(new AxisAlignedBB(d, d1, d2, d + 1.0D, d1 + 1.0D, d2 + 1.0D));
    GL11.glLineWidth(2.0F);
    GL11.glDisable(2848);
    GL11.glEnable(3553);
    GL11.glEnable(2896);
    GL11.glEnable(2929);
    GL11.glDepthMask(true);
    GL11.glDisable(3042);
    GL11.glPopMatrix();
  }
  
  public static void drawNukerESP(double d, double d1, double d2, double r, double b, double g, double alpha)
  {
    GL11.glPushMatrix();
    GL11.glEnable(3042);
    GL11.glBlendFunc(770, 771);
    GL11.glLineWidth(1.0F);
    GL11.glDisable(2896);
    GL11.glDisable(3553);
    GL11.glEnable(2848);
    GL11.glDisable(2929);
    GL11.glDepthMask(false);
    GL11.glColor4d(r, g, b, alpha);
    drawBoundingBox(new AxisAlignedBB(d, d1, d2, d + 1.0D, d1 + 1.0D, d2 + 1.0D));
    GL11.glColor4d(r, g, b, 0.6D);
    drawOutlinedBoundingBox(new AxisAlignedBB(d, d1, d2, d + 1.0D, d1 + 1.0D, d2 + 1.0D));
    GL11.glLineWidth(2.0F);
    GL11.glDisable(2848);
    GL11.glEnable(3553);
    GL11.glEnable(2896);
    GL11.glEnable(2929);
    GL11.glDepthMask(true);
    GL11.glDisable(3042);
    GL11.glPopMatrix();
  }
  
  public static void drawGradientRect(double x, double y, double x2, double y2, int col1, int col2)
  {
    float f = (col1 >> 24 & 0xFF) / 255.0F;
    float f1 = (col1 >> 16 & 0xFF) / 255.0F;
    float f2 = (col1 >> 8 & 0xFF) / 255.0F;
    float f3 = (col1 & 0xFF) / 255.0F;
    
    float f4 = (col2 >> 24 & 0xFF) / 255.0F;
    float f5 = (col2 >> 16 & 0xFF) / 255.0F;
    float f6 = (col2 >> 8 & 0xFF) / 255.0F;
    float f7 = (col2 & 0xFF) / 255.0F;
    
    GL11.glEnable(3042);
    GL11.glDisable(3553);
    GL11.glBlendFunc(770, 771);
    GL11.glEnable(2848);
    GL11.glShadeModel(7425);
    
    GL11.glPushMatrix();
    GL11.glBegin(7);
    GL11.glColor4f(f1, f2, f3, f);
    GL11.glVertex2d(x2, y);
    GL11.glVertex2d(x, y);
    
    GL11.glColor4f(f5, f6, f7, f4);
    GL11.glVertex2d(x, y2);
    GL11.glVertex2d(x2, y2);
    GL11.glEnd();
    GL11.glPopMatrix();
    
    GL11.glEnable(3553);
    GL11.glDisable(3042);
    GL11.glDisable(2848);
    GL11.glShadeModel(7424);
  }
  
  public static void drawGradientBorderedRect(double x, double y, double x2, double y2, float l1, int col1, int col2, int col3)
  {
    float f = (col1 >> 24 & 0xFF) / 255.0F;
    float f1 = (col1 >> 16 & 0xFF) / 255.0F;
    float f2 = (col1 >> 8 & 0xFF) / 255.0F;
    float f3 = (col1 & 0xFF) / 255.0F;
    
    GL11.glDisable(3553);
    GL11.glBlendFunc(770, 771);
    GL11.glEnable(2848);
    GL11.glDisable(3042);
    
    GL11.glPushMatrix();
    GL11.glColor4f(f1, f2, f3, f);
    GL11.glLineWidth(1.0F);
    GL11.glBegin(1);
    GL11.glVertex2d(x, y);
    GL11.glVertex2d(x, y2);
    GL11.glVertex2d(x2, y2);
    GL11.glVertex2d(x2, y);
    GL11.glVertex2d(x, y);
    GL11.glVertex2d(x2, y);
    GL11.glVertex2d(x, y2);
    GL11.glVertex2d(x2, y2);
    GL11.glEnd();
    GL11.glPopMatrix();
    
    drawGradientRect(x, y, x2, y2, col2, col3);
    
    GL11.glEnable(3042);
    GL11.glEnable(3553);
    GL11.glDisable(2848);
  }
  
  public static void drawRect(float par1, float f, float g, float par3, int par4)
  {
    if (par1 < g)
    {
      float var5 = par1;
      par1 = g;
      g = var5;
    }
    if (f < par3)
    {
      float var5 = f;
      f = par3;
      par3 = var5;
    }
    float var10 = (par4 >> 24 & 0xFF) / 255.0F;
    float var6 = (par4 >> 16 & 0xFF) / 255.0F;
    float var7 = (par4 >> 8 & 0xFF) / 255.0F;
    float var8 = (par4 & 0xFF) / 255.0F;
    Tessellator var9 = Tessellator.instance;
    GL11.glEnable(3042);
    GL11.glDisable(3553);
    GL11.glDisable(2896);
    OpenGlHelper.glBlendFunc(770, 771, 1, 0);
    GL11.glColor4f(var6, var7, var8, var10);
    var9.startDrawingQuads();
    var9.addVertex(par1, par3, 0.0D);
    var9.addVertex(g, par3, 0.0D);
    var9.addVertex(g, f, 0.0D);
    var9.addVertex(par1, f, 0.0D);
    var9.draw();
    GL11.glEnable(3553);
    GL11.glDisable(3042);
  }
  
  public static void drawBorderedRect(float x, float y, float x1, float y1, int borderC, int insideC)
  {
    x *= 2.0F;x1 *= 2.0F;y *= 2.0F;y1 *= 2.0F;
    GL11.glScalef(0.5F, 0.5F, 0.5F);
    drawVLine(x, y, y1 - 1.0F, borderC);
    drawVLine(x1 - 1.0F, y, y1, borderC);
    drawHLine(x, x1 - 1.0F, y, borderC);
    drawHLine(x, x1 - 2.0F, y1 - 1.0F, borderC);
    drawRect(x + 1.0F, y + 1.0F, x1 - 1.0F, y1 - 1.0F, insideC);
    GL11.glScalef(2.0F, 2.0F, 2.0F);
  }
  
  public static void drawHLine(float par1, float par2, float par3, int par4)
  {
    if (par2 < par1)
    {
      float var5 = par1;
      par1 = par2;
      par2 = var5;
    }
    drawRect(par1, par3, par2 + 1.0F, par3 + 1.0F, par4);
  }
  
  public static void drawVLine(float par1, float par2, float par3, int par4)
  {
    if (par3 < par2)
    {
      float var5 = par2;
      par2 = par3;
      par3 = var5;
    }
    drawRect(par1, par2 + 1.0F, par1 + 1.0F, par3, par4);
  }
  
  public static void drawRoundedRect(float x, float y, float x1, float y1, int borderC, int insideC)
  {
    x *= 2.0F;y *= 2.0F;x1 *= 2.0F;y1 *= 2.0F;
    GL11.glScalef(0.5F, 0.5F, 0.5F);
    drawVLine(x, y + 1.0F, y1 - 2.0F, borderC);
    drawVLine(x1 - 1.0F, y + 1.0F, y1 - 2.0F, borderC);
    drawHLine(x + 2.0F, x1 - 3.0F, y, borderC);
    drawHLine(x + 2.0F, x1 - 3.0F, y1 - 1.0F, borderC);
    drawHLine(x + 1.0F, x + 1.0F, y + 1.0F, borderC);
    drawHLine(x1 - 2.0F, x1 - 2.0F, y + 1.0F, borderC);
    drawHLine(x1 - 2.0F, x1 - 2.0F, y1 - 2.0F, borderC);
    drawHLine(x + 1.0F, x + 1.0F, y1 - 2.0F, borderC);
    drawRect(x + 1.0F, y + 1.0F, x1 - 1.0F, y1 - 1.0F, insideC);
    GL11.glScalef(2.0F, 2.0F, 2.0F);
  }
  
  public static void drawRGBRect(float par1, float f, float g, float par3, Color par4)
  {
    if (par1 < g)
    {
      float var5 = par1;
      par1 = g;
      g = var5;
    }
    if (f < par3)
    {
      float var5 = f;
      f = par3;
      par3 = var5;
    }
    Tessellator var9 = Tessellator.instance;
    GL11.glEnable(3042);
    GL11.glDisable(3553);
    GL11.glDisable(2896);
    OpenGlHelper.glBlendFunc(770, 771, 1, 0);
    GL11.glColor4f(par4.getRed(), par4.getGreen(), par4.getBlue(), 63.0F);
    var9.startDrawingQuads();
    var9.addVertex(par1, par3, 0.0D);
    var9.addVertex(g, par3, 0.0D);
    var9.addVertex(g, f, 0.0D);
    var9.addVertex(par1, f, 0.0D);
    var9.draw();
    GL11.glEnable(3553);
    GL11.glDisable(3042);
  }
  
  public static void drawItem(int x, int y, ItemStack stack)
  {
    itemRenderer.renderItemIntoGUI(Minecraft.getMinecraft().fontRenderer, Minecraft.getMinecraft().renderEngine, stack, x, y);
    itemRenderer.renderItemAndEffectIntoGUI(Minecraft.getMinecraft().fontRenderer, Minecraft.getMinecraft().renderEngine, stack, x, y);
    
    GL11.glDisable(2884);
    GL11.glEnable(3008);
    GL11.glDisable(3042);
    GL11.glDisable(2896);
    GL11.glDisable(2884);
    GL11.glClear(256);
  }
  
  public static final void drawNametagRect(double x, double y, double x1, double y1, int color2, int color)
  {
    GL11.glEnable(3042);
    GL11.glEnable(2848);
    drawRect((int)x, (int)y, (int)x1, (int)y1, color);
    GL11.glScalef(0.5F, 0.5F, 0.5F);
    drawRect((int)x * 2 - 1, (int)y * 2, (int)x * 2, (int)y1 * 2 - 1, color2);
    drawRect((int)x * 2, (int)y * 2 - 1, (int)x1 * 2, (int)y * 2, color2);
    drawRect((int)x1 * 2, (int)y * 2, (int)x1 * 2 + 1, (int)y1 * 2 - 1, color2);
    drawRect((int)x * 2, (int)y1 * 2 - 1, (int)x1 * 2, (int)y1 * 2, color2);
    GL11.glDisable(3042);
    GL11.glScalef(2.0F, 2.0F, 2.0F);
  }
}
