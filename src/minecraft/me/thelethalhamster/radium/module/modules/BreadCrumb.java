package me.thelethalhamster.radium.module.modules;

import java.util.ArrayList;
import java.util.Iterator;

import me.thelethalhamster.radium.module.Category;
import me.thelethalhamster.radium.module.Module;
import me.thelethalhamster.radium.util.GL11Helper;
import me.thelethalhamster.radium.wrapper.RadiumWrapper;
import net.minecraft.client.renderer.entity.RenderManager;

import org.lwjgl.opengl.GL11;

public class BreadCrumb extends Module{
	long prevPosition = -1L;
	long delayTime = 100L;
	public static boolean record = true;
	public static ArrayList<double[]> crumbs = new ArrayList<double[]>();

	public BreadCrumb() 
	{
		super("BreadCrumb", 0, Category.RENDER, true);
	}

	@Override
	public void onMotionUpdate() 
	{
		if (System.currentTimeMillis() >= prevPosition + delayTime && record) 
		{
			crumbs.add(getPosition());
			this.prevPosition = System.currentTimeMillis();
		}
	}

	public static double[] getPosition() 
	{
		double pos[] = { RadiumWrapper.getInstance().getMinecraft().thePlayer.posX, RadiumWrapper.getInstance().getMinecraft().thePlayer.posY + 1, RadiumWrapper.getInstance().getMinecraft().thePlayer.posZ };
		return pos;
	}

	@Override
	public void onRender() 
	{
		drawCrumbs();
	}

	@Override
	public void onDisable() 
	{
		this.prevPosition = -1L;
	}

	private void drawCrumbs() 
	{
		GL11.glPushMatrix();
		GL11Helper.enableDefaults();
		GL11.glBegin(GL11.GL_LINE_STRIP);
		Iterator iterator = crumbs.iterator();

		while (iterator.hasNext()) 
		{
			double[] positionArray = (double[]) iterator.next();
			RenderManager rm = RenderManager.instance;
			double x1 = positionArray[0] - rm.viewerPosX;
			double y1 = positionArray[1] - rm.viewerPosY;
			double z1 = positionArray[2] - rm.viewerPosZ;
			GL11.glColor4f(0F, 1F, 1.0F, 0.75F);

			GL11.glVertex3d(x1, y1 - 2.5D, z1);
		}

		GL11.glEnd();
		GL11Helper.disableDefaults();
		GL11.glPopMatrix();
	}

	public double getDistToXYZ(double x, double y, double z) 
	{
		double d1 = RadiumWrapper.getInstance().getMinecraft().thePlayer.posX - x;
		double d2 = RadiumWrapper.getInstance().getMinecraft().thePlayer.posY - (y + 0.5D);
		double d3 = RadiumWrapper.getInstance().getMinecraft().thePlayer.posZ - z;
		double d4 = Math.sqrt(d1 * d1 + d2 * d2 + d3 * d3);
		return d4;
	}
}
