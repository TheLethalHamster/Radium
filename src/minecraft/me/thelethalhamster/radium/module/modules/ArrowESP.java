package me.thelethalhamster.radium.module.modules;

import me.thelethalhamster.radium.module.Category;
import me.thelethalhamster.radium.module.Module;
import me.thelethalhamster.radium.util.GL11Helper;
import me.thelethalhamster.radium.wrapper.RadiumWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.AxisAlignedBB;

public class ArrowESP extends Module{

	public ArrowESP() {
		super("ArrowESP", 0, Category.RENDER, true);
	}
	
	@Override
	public void onRender(){
		for (Object o : RadiumWrapper.getInstance().getMinecraft().theWorld.getLoadedEntityList()) {
            if (o instanceof EntityArrow) {
            		EntityArrow e = (EntityArrow) o;
            		RadiumWrapper.getInstance().getMinecraft().entityRenderer.disableLightmap(0);
                    final AxisAlignedBB bb = e.boundingBox;
                    GL11Helper.drawESP(bb.minX - Minecraft.getMinecraft().thePlayer.posX, bb.minY - Minecraft.getMinecraft().thePlayer.posY, bb.minZ - RadiumWrapper.getInstance().getMinecraft().thePlayer.posZ, 01, 0, 0, 0.2);
                    RadiumWrapper.getInstance().getMinecraft().entityRenderer.enableLightmap(0);
            }
		}
	}
}
