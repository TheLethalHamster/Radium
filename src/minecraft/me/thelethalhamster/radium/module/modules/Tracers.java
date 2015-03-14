package me.thelethalhamster.radium.module.modules;

import java.util.Iterator;

import me.thelethalhamster.radium.module.Category;
import me.thelethalhamster.radium.module.Module;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;

import org.lwjgl.opengl.GL11;

public class Tracers extends Module
{
  public Tracers()
  {
    super("Tracers", 0, Category.RENDER, true);
  }
  
  public void onRender()
  {
    try
    {
      getMinecraft().gameSettings.viewBobbing = false;
      GL11.glPushMatrix();
      getMinecraft().entityRenderer.disableLightmap(1.0D);
      GL11.glEnable(3042);
      GL11.glEnable(2848);
      GL11.glDisable(2929);
      GL11.glDisable(2896);
      GL11.glDisable(3553);
      GL11.glBlendFunc(770, 771);
      GL11.glEnable(3042);
      GL11.glLineWidth(1.0F);
      for (Iterator playerIterator = getMinecraft().theWorld.loadedEntityList.iterator(); playerIterator.hasNext();)
      {
        Object theObject = playerIterator.next();
        if ((theObject != getMinecraft().thePlayer) && (theObject != null)) {
          if ((theObject instanceof EntityPlayer))
          {
            EntityPlayer entity = (EntityPlayer)theObject;
            double posX = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) - RenderManager.renderPosX;
            double posY = entity.lastTickPosY + 1.0D + (entity.posY - entity.lastTickPosY) - RenderManager.renderPosY;
            double posZ = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) - RenderManager.renderPosZ;
            
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glBegin(2);
            GL11.glVertex3d(0.0D, 0.0D, 0.0D);
            GL11.glVertex3d(posX, posY, posZ);
            GL11.glEnd();
          }
        }
      }
      GL11.glDisable(3042);
      GL11.glEnable(3553);
      GL11.glEnable(2929);
      GL11.glDisable(2848);
      GL11.glDisable(3042);
      GL11.glEnable(2896);
      getMinecraft().entityRenderer.enableLightmap(1.0D);
      GL11.glPopMatrix();
    }
    catch (Exception localException) {}
  }
}
