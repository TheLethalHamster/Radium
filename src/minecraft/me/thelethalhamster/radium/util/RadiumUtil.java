package me.thelethalhamster.radium.util;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.List;

import me.thelethalhamster.radium.wrapper.RadiumWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.biome.BiomeCache.Block;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.glu.Sphere;

public class RadiumUtil {
	
	private static ByteBuffer boxSides;
	private static int cube;

	public FontRenderer getFontRenderer(){
		return Minecraft.getMinecraft().fontRenderer;
	}
	
	public void printToConsole(Object o){
		System.out.println(o);
	}
	
	public void addChatMessage(String s){
		RadiumWrapper.getInstance().getPlayer().addChatMessage(new ChatComponentText("Radium: " + s));
	}
	
	public static void drawESP(double d, double d1, double d2, double r, double b, double g)
	{
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
		drawBoundingBox(new AxisAlignedBB(d, d1, d2, d + 1.0, d1 + 1.0, d2 + 1.0));
		GL11.glColor4d(r, g, b, 1.0F);
		drawOutlinedBoundingBox(new AxisAlignedBB(d, d1, d2, d + 1.0, d1 + 1.0, d2 + 1.0));
		GL11.glLineWidth(2.0F);
		GL11.glDisable(GL11.GL_LINE_SMOOTH);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(2929);
		GL11.glDepthMask(true);
		GL11.glDisable(3042);
		GL11.glPopMatrix();
	}
	
	public static void drawOutlinedBoundingBox(AxisAlignedBB par1AxisAlignedBB)
	{
		Tessellator var2 = Tessellator.instance;
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

	public static void drawBoundingBox(AxisAlignedBB axisalignedbb)
	{
		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
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
		tessellator.draw();
		tessellator.startDrawingQuads();
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
		tessellator.draw();
		tessellator.startDrawingQuads();
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
		tessellator.draw();
	}
	
	public static void sendPacket(Packet p)
	{
		RadiumWrapper.getInstance().getMinecraft().thePlayer.sendQueue.addToSendQueue(p);
	}
	
	public static void renderPlayerSphere(double par3, double par5, double par7) {
        float x = (float)par3;
        float y = (float)par5;
        float z = (float)par7;
        renderSphere(x, y,  z);
    }
	static Sphere s = new Sphere();
	
	private static void renderSphere(float x, float y, float z) {
        GL11.glPushMatrix();
       GL11.glTranslatef(x, y + 1, z);
        GL11.glColor4f(0.0F, 1.0F, 1.0F, 0.5F);
        GL11.glEnable(3042 /*GL_BLEND*/);
     GL11.glDisable(2929 /*GL_DEPTH_TEST*/);
     GL11.glDepthMask(true);
     GL11.glDisable(GL11.GL_TEXTURE_2D);  
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glLineWidth(0.5F);
        
        s.setDrawStyle(GLU.GLU_LINE);
        float radius = 4.0F;
        s.draw(radius, 32,32);
       GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glPopMatrix();
    }
	
	public static void drawFullCircle(int cx, int cy, double r, int c)
	  {
	    GL11.glScalef(0.5F, 0.5F, 0.5F);
	    r *= 2.0D;
	    cx *= 2;
	    cy *= 2;
	    float f = (c >> 24 & 0xFF) / 255.0F;
	    float f1 = (c >> 16 & 0xFF) / 255.0F;
	    float f2 = (c >> 8 & 0xFF) / 255.0F;
	    float f3 = (c & 0xFF) / 255.0F;
	    GL11.glEnable(3042);
	    GL11.glDisable(3553);
	    GL11.glEnable(2848);
	    GL11.glBlendFunc(770, 771);
	    GL11.glColor4f(f1, f2, f3, f);
	    GL11.glBegin(6);
	    for (int i = 0; i <= 360; i++)
	    {
	      double x = Math.sin(i * 3.141592653589793D / 180.0D) * r;
	      double y = Math.cos(i * 3.141592653589793D / 180.0D) * r;
	      GL11.glVertex2d(cx + x, cy + y);
	    }
	    GL11.glEnd();
	    GL11.glDisable(2848);
	    GL11.glEnable(3553);
	    GL11.glDisable(3042);
	    GL11.glScalef(2.0F, 2.0F, 2.0F);
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
	  
	  public static boolean stringListContains(List<String> list, String needle)
	  {
	    for (String s : list) {
	      if (s.trim().equalsIgnoreCase(needle.trim())) {
	        return true;
	      }
	    }
	    return false;
	  }
	  
	  public static void drawBorderedRect(double x, double y, double x2, double y2, float l1, int col1, int col2)
	  {
	    drawRect((float)x, (float)y, (float)x2, (float)y2, col2);
	    
	    float f = (col1 >> 24 & 0xFF) / 255.0F;
	    float f1 = (col1 >> 16 & 0xFF) / 255.0F;
	    float f2 = (col1 >> 8 & 0xFF) / 255.0F;
	    float f3 = (col1 & 0xFF) / 255.0F;
	    
	    GL11.glEnable(3042);
	    GL11.glDisable(3553);
	    GL11.glBlendFunc(770, 771);
	    GL11.glEnable(2848);
	    
	    GL11.glPushMatrix();
	    GL11.glColor4f(f1, f2, f3, f);
	    GL11.glLineWidth(l1);
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
	    
	    GL11.glEnable(3553);
	    GL11.glDisable(3042);
	    GL11.glDisable(2848);
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
	  
	  public static void drawRect(float paramXStart, float paramYStart, float paramXEnd, float paramYEnd, int paramColor)
	  {
	    float alpha = (paramColor >> 24 & 0xFF) / 255.0F;
	    float red = (paramColor >> 16 & 0xFF) / 255.0F;
	    float green = (paramColor >> 8 & 0xFF) / 255.0F;
	    float blue = (paramColor & 0xFF) / 255.0F;
	    
	    GL11.glEnable(3042);
	    GL11.glDisable(3553);
	    GL11.glBlendFunc(770, 771);
	    GL11.glEnable(2848);
	    
	    GL11.glPushMatrix();
	    GL11.glColor4f(red, green, blue, alpha);
	    GL11.glBegin(7);
	    GL11.glVertex2d(paramXEnd, paramYStart);
	    GL11.glVertex2d(paramXStart, paramYStart);
	    GL11.glVertex2d(paramXStart, paramYEnd);
	    GL11.glVertex2d(paramXEnd, paramYEnd);
	    GL11.glEnd();
	    GL11.glPopMatrix();
	    
	    GL11.glEnable(3553);
	    GL11.glDisable(3042);
	    GL11.glDisable(2848);
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
	  
	  public static void setSneakKeyPressed(boolean pressed)
	  {
		  Minecraft.getMinecraft().gameSettings.keyBindSneak.pressed = pressed;
	  }
	  
	  public static void setJumpKeyPressed(boolean pressed)
	  {
		  Minecraft.getMinecraft().gameSettings.keyBindJump.pressed = pressed;
	  }
	  
	  public static void setMotionX(double x)
	  {
		  Minecraft.getMinecraft().thePlayer.motionX = x;
	  }
	  
	  public static void setMotionY(double y)
	  {
		  Minecraft.getMinecraft().thePlayer.motionY = y;
	  }
	  
	  public static void setMotionZ(double z)
	  {
	      Minecraft.getMinecraft().thePlayer.motionZ = z;
	  }
	  
	  public static int getForwardCode()
	  {
	      return Minecraft.getMinecraft().gameSettings.keyBindForward.getKeyCode();
	  }
	  
	  public static double getMotionX()
	  {
		  return Minecraft.getMinecraft().thePlayer.motionX;
	  }
	  
	  public static double getMotionY()
	  {
		  return Minecraft.getMinecraft().thePlayer.motionY;
	  }
	  
	  public static double getMotionZ()
	  {
	      return Minecraft.getMinecraft().thePlayer.motionZ;
	  }
	  
	  public static void setOnGround(boolean b)
	  {
	      Minecraft.getMinecraft().thePlayer.onGround = b;
	  }
	  
	  public static AxisAlignedBB getAABB(int x, int y, int z)
	  {
	    Entity p = Minecraft.getMinecraft().thePlayer;
	    double var8 = p.lastTickPosX + (p.posX - p.lastTickPosX);
	    double var10 = p.lastTickPosY + (p.posY - p.lastTickPosY);
	    double var12 = p.lastTickPosZ + (p.posZ - p.lastTickPosZ);
	    float var6 = 0.002F;
	    net.minecraft.block.Block block = Minecraft.getMinecraft().theWorld.getBlock(x, y, z);
	    return block.getSelectedBoundingBoxFromPool(Minecraft.getMinecraft().theWorld, x, y, z).expand(0.002000000094994903D, 0.002000000094994903D, 0.002000000094994903D).getOffsetBoundingBox(-var8, -var10, -var12);
	  }
	  
	  public static FloatBuffer getBox(AxisAlignedBB bound)
	  {
	    return getBox((float)bound.minX, (float)bound.minY, (float)bound.minZ, (float)bound.maxX, (float)bound.maxY, (float)bound.maxZ);
	  }
	  
	  public static FloatBuffer getBox(float minX, float minY, float minZ, float maxX, float maxY, float maxZ)
	  {
	    FloatBuffer vertices = BufferUtils.createFloatBuffer(24);
	    vertices.put(new float[] { minX, minY, minZ, maxX, minY, minZ, maxX, maxY, minZ, minX, maxY, minZ, minX, maxY, maxZ, maxX, maxY, maxZ, maxX, minY, maxZ, minX, minY, maxZ });
	    vertices.flip();
	    return vertices;
	  }
	  
	  public static ByteBuffer getSides()
	  {
	    boxSides = BufferUtils.createByteBuffer(24);
	    boxSides.put(new byte[] { 0, 3, 2, 1, 2, 5, 6, 1, 6, 7, 0, 1, 0, 7, 4, 3, 4, 7, 6, 5, 2, 3, 4, 5 });
	    boxSides.flip();
	    cube = GL15.glGenBuffers();
	    GL15.glBindBuffer(34962, cube);
	    GL15.glBufferData(34962, getBox(-0.5F, -0.5F, -0.5F, 0.5F, 0.5F, 0.5F), 35044);
	    GL15.glBindBuffer(34962, 0);
	    return boxSides;
	  }
}
