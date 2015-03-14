package me.thelethalhamster.radium.module.modules;

import me.thelethalhamster.radium.module.Category;
import me.thelethalhamster.radium.module.Module;
import me.thelethalhamster.radium.util.RadiumUtil;
import me.thelethalhamster.radium.wrapper.RadiumWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

public class ChestESP extends Module{
	public ChestESP() {
		super("ChestESP", 0, Category.RENDER, true);
	}
	
	@Override
	public void onRender() {
		for(Object o : RadiumWrapper.getInstance().getMinecraft().theWorld.field_147482_g) {
			if(o instanceof TileEntityChest) {
				TileEntityChest chest = (TileEntityChest)o;
				this.drawESP(chest, chest.field_145851_c, chest.field_145848_d, chest.field_145849_e, chest.field_145986_n);
			}
		}
	}
	
	public void drawESP(TileEntityChest chest, double x, double y, double z, float f) {
		if(!(chest.field_145851_c == 0 && chest.field_145848_d == 0 && chest.field_145849_e == 0)) {
			RadiumWrapper.getInstance().getMinecraft().entityRenderer.disableLightmap(f);
			//this.chestESP(x - RenderManager.renderPosX, y - RenderManager.renderPosY, z - RenderManager.renderPosZ);
			RadiumUtil.drawESP(x - RenderManager.renderPosX, y - RenderManager.renderPosY, z - RenderManager.renderPosZ, 0.0F, 1.0F, 1.0F);
			RadiumWrapper.getInstance().getMinecraft().entityRenderer.enableLightmap(f);
		}
	}
	
	public static void chestESP(double x, double y, double z){
            load();
            GL11.glBlendFunc(770, 771);
    GL11.glColor4f(MathHelper.sin((float)Minecraft.getSystemTime() / 200.0F) * 0.2F + 0.5F, MathHelper.sin((float)Minecraft.getSystemTime() / 200.0F) * 0.2F + 0.2F, MathHelper.sin((float)Minecraft.getSystemTime() / 200.0F) * 0.5F + 1F, 0.2F);
      GL11.glDisable(GL11.GL_CULL_FACE);
      GL11.glDisable(3553 /*GL_TEXTURE_2D*/);
      GL11.glDisable(2929 /*GL_DEPTH_TEST*/);
      GL11.glDisable(GL11.GL_LIGHTING);
      GL11.glDepthMask(false);
      GL11.glLineWidth(1F);
      GL11.glBegin(GL11.GL_QUADS);
      //Back
      GL11.glVertex3d(x, y, z);
      GL11.glVertex3d(x, y + 1, z);
      GL11.glVertex3d(x + 1, y + 1, z);
      GL11.glVertex3d(x + 1, y, z);
      //Left
      GL11.glVertex3d(x + 1, y, z);
      GL11.glVertex3d(x + 1, y + 1, z);
      GL11.glVertex3d(x + 1, y + 1, z + 1);
      GL11.glVertex3d(x + 1, y, z + 1);
      //Front
      GL11.glVertex3d(x + 1, y, z + 1);
      GL11.glVertex3d(x + 1, y + 1, z + 1);
      GL11.glVertex3d(x, y + 1, z + 1);
      GL11.glVertex3d(x, y, z + 1);
      //Right
      GL11.glVertex3d(x, y, z + 1);
      GL11.glVertex3d(x, y + 1, z + 1);
      GL11.glVertex3d(x, y + 1, z);
      GL11.glVertex3d(x, y, z);
      //Top
      GL11.glVertex3d(x, y, z);
      GL11.glVertex3d(x + 1, y, z);
      GL11.glVertex3d(x + 1, y, z + 1);
      GL11.glVertex3d(x, y, z + 1);
      //Bottom
      GL11.glVertex3d(x, y + 1, z);
      GL11.glVertex3d(x + 1, y + 1, z);
      GL11.glVertex3d(x + 1, y + 1, z + 1);
      GL11.glVertex3d(x, y + 1, z + 1);
      GL11.glEnd();
      /**Dem lines*/
    GL11.glColor4f(MathHelper.sin((float)Minecraft.getSystemTime() / 200.0F) * 0.2F + 0.5F, MathHelper.sin((float)Minecraft.getSystemTime() / 200.0F) * 0.2F + 0.2F, MathHelper.sin((float)Minecraft.getSystemTime() / 200.0F) * 0.5F + 1F, 1F);
      GL11.glBegin(GL11.GL_LINES);
      //Back
      GL11.glVertex3d(x, y, z);
      GL11.glVertex3d(x, y + 1, z);
      GL11.glVertex3d(x, y + 1, z);
      GL11.glVertex3d(x + 1, y + 1, z);
      GL11.glVertex3d(x + 1, y + 1, z);
      GL11.glVertex3d(x + 1, y, z);
      GL11.glVertex3d(x + 1, y, z);
      GL11.glVertex3d(x, y, z);
      //Left
      GL11.glVertex3d(x + 1, y, z);
      GL11.glVertex3d(x + 1, y + 1, z);
      GL11.glVertex3d(x + 1, y + 1, z);
      GL11.glVertex3d(x + 1, y + 1, z + 1);
      GL11.glVertex3d(x + 1, y + 1, z + 1);
      GL11.glVertex3d(x + 1, y, z + 1);
      GL11.glVertex3d(x + 1, y, z + 1);
      GL11.glVertex3d(x + 1, y, z);
      //Front
      GL11.glVertex3d(x + 1, y, z + 1);
      GL11.glVertex3d(x + 1, y + 1, z + 1);
      GL11.glVertex3d(x + 1, y + 1, z + 1);
      GL11.glVertex3d(x, y + 1, z + 1);
      GL11.glVertex3d(x, y + 1, z + 1);
      GL11.glVertex3d(x, y, z + 1);
      GL11.glVertex3d(x, y, z + 1);
      GL11.glVertex3d(x + 1, y, z + 1);
      //Right
      GL11.glVertex3d(x, y, z + 1);
      GL11.glVertex3d(x, y + 1, z + 1);
      GL11.glVertex3d(x, y + 1, z + 1);
      GL11.glVertex3d(x, y + 1, z);
      GL11.glVertex3d(x, y + 1, z);
      GL11.glVertex3d(x, y, z);
      GL11.glVertex3d(x, y, z);
      GL11.glVertex3d(x, y, z + 1);
      //Tracer
      //  GL11.glVertex3d(0, 0, 0);
      //  GL11.glVertex3d(x + 0.5F, y + 0.5F, z + 0.5F);
      GL11.glEnd();
      float var13 = 1.6F;
      float var14 = 0.01668F * var13;
      GL11.glPushMatrix();
      GL11.glTranslatef((float)x + 0.5F, (float)y + 0.5F, (float)z + 0.5F);
      GL11.glNormal3f(0.0F, 1.0F, 0.0F);
      GL11.glRotatef(-RenderManager.playerViewY, 0.0F, 1.0F, 0.0F);
      GL11.glRotatef(RenderManager.playerViewX, 1.0F, 0.0F, 0.0F);
      GL11.glScalef(-var14, -var14, var14);
      GL11.glEnable(GL11.GL_TEXTURE_2D);
      RadiumWrapper.getInstance().getMinecraft().fontRenderer.drawStringWithShadow("\247aChest",  -15, -40, 0xff);
      GL11.glPopMatrix();
      GL11.glDepthMask(true);
      GL11.glEnable(GL11.GL_CULL_FACE);
      GL11.glEnable(3553 /*GL_TEXTURE_2D*/);
      GL11.glEnable(2929 /*GL_DEPTH_TEST*/);
      GL11.glEnable(GL11.GL_LIGHTING);
      reset();
        
    }

    public static void load(){
  GL11.glEnable(GL11.GL_BLEND);
  GL11.glDisable(GL11.GL_TEXTURE_2D);
  GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
    }

    public static void reset(){
  GL11.glEnable(GL11.GL_TEXTURE_2D);
  GL11.glDisable(GL11.GL_BLEND);
    }
}
