package me.thelethalhamster.radium.override;

import me.thelethalhamster.radium.main.Radium;
import me.thelethalhamster.radium.module.Category;
import me.thelethalhamster.radium.module.Module;
import me.thelethalhamster.radium.module.ModuleManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;

public class GuiIngameHook extends GuiIngame{

	public GuiIngameHook(Minecraft par1Minecraft) {
		super(par1Minecraft);
	}
	
	FontRenderer fr = Minecraft.getMinecraft().fontRenderer;
	
	public void renderGameOverlay(float par1, boolean par2, int par3, int par4){
		super.renderGameOverlay(par1, par2, par3, par4);
		ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft().gameSettings, Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
		Minecraft.getMinecraft().fontRenderer.drawString("Radium v1 (rel-1.7.x)", 2, 2, 0xFF8F0000);
		int i = 0;
		for(Module m : ModuleManager.getInstance().getModules()){
			if(m.getState() && m.getVisible() && !m.isCategory(Category.OTHER)){
				int width = sr.getScaledWidth() - fr.getStringWidth(m.getName());
				int height = (10 * i);
				fr.drawString(m.getName(), width, height, 0xFFFFFFFF);	
				i++;
			}
		}
		if(Minecraft.getMinecraft().currentScreen != Radium.getInstance().getGui()){
			Radium.getInstance().getGuiManager().renderPinned();
		}
		
		Radium.getInstance().getGuiManager().update();
	}
}
