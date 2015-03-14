package me.thelethalhamster.radium.module.modules;

import java.awt.Color;
import java.util.Iterator;

import me.thelethalhamster.radium.module.Category;
import me.thelethalhamster.radium.module.Module;
import me.thelethalhamster.radium.util.ColorUtil;
import me.thelethalhamster.radium.util.RenderUtil;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.AxisAlignedBB;

import org.lwjgl.opengl.GL11;

public class PlayerESP extends Module
{
  public PlayerESP()
  {
    super("PlayerESP", 0, Category.RENDER, true);
  }
  
  public void onRender()
  {
    for (Iterator playerIterator = getMinecraft().theWorld.loadedEntityList.iterator(); playerIterator.hasNext();)
    {
      Object theObject = playerIterator.next();
      if ((theObject instanceof EntityOtherPlayerMP))
      {
        EntityOtherPlayerMP thePlayer = (EntityOtherPlayerMP)theObject;
        double xPos = thePlayer.lastTickPosX + (thePlayer.posX - thePlayer.lastTickPosX) * getMinecraft().timer.renderPartialTicks;
        double yPos = thePlayer.lastTickPosY + (thePlayer.posY - thePlayer.lastTickPosY) * getMinecraft().timer.renderPartialTicks;
        double zPos = thePlayer.lastTickPosZ + (thePlayer.posZ - thePlayer.lastTickPosZ) * getMinecraft().timer.renderPartialTicks;
        getMinecraft().entityRenderer.disableLightmap(1.0D);
        Color espColor = ColorUtil.hexToRGBA(ColorUtil.playerESPColor);
        drawPlayerESP(xPos - RenderManager.renderPosX, yPos - RenderManager.renderPosY, zPos - RenderManager.renderPosZ, thePlayer, thePlayer.height - 0.1D, thePlayer.width - 0.12D, 0.45F, 0.45F, espColor);
        getMinecraft().entityRenderer.enableLightmap(1.0D);
      }
    }
  }
  
  private void drawPlayerESP(double xPos, double yPos, double zPos, EntityOtherPlayerMP thePlayer, double e, double f, float alpha, float alpha2, Color rgb)
  {
    GL11.glPushMatrix();
    GL11.glEnable(3042);
    getMinecraft().entityRenderer.disableLightmap(1.0D);
    GL11.glColor4f(rgb.getRed(), rgb.getGreen(), rgb.getBlue(), alpha);
    GL11.glDisable(3553);
    GL11.glDisable(2896);
    GL11.glDisable(2929);
    GL11.glDepthMask(false);
    GL11.glLineWidth(1.0F);
    GL11.glBlendFunc(770, 771);
    GL11.glEnable(2848);
    RenderUtil.drawBoundingBox(new AxisAlignedBB(xPos - f, yPos + 0.1D, zPos - f, xPos + f, yPos + e + 0.25D, zPos + f));
    GL11.glColor4f(1.0F, 1.0F, 1.0F, alpha2);
    RenderUtil.drawOutlinedBoundingBox(new AxisAlignedBB(xPos - f, yPos + 0.1D, zPos - f, xPos + f, yPos + e + 0.25D, zPos + f));
    GL11.glDepthMask(true);
    GL11.glEnable(2929);
    GL11.glEnable(3553);
    GL11.glEnable(2896);
    GL11.glDisable(2848);
    GL11.glDisable(3042);
    GL11.glPopMatrix();
  }
}
