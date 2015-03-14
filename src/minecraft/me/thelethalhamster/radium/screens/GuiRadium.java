package me.thelethalhamster.radium.screens;

import me.thelethalhamster.radium.main.Radium;
import me.thelethalhamster.radium.override.GuiRadiumButton;
import me.thelethalhamster.radium.ttf.TTFRenderer;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class GuiRadium extends GuiScreen{
	private GuiScreen parentScreen;
	GuiButton protocol;
	public FontRenderer fontRendererObj = new TTFRenderer("Arial Bold", 17);
	
	public GuiRadium(GuiScreen parentScreen){
		this.parentScreen = parentScreen;
	}
	
	public void initGui() {
		int var3 = this.height / 4 + 48;
		this.buttonList.add(new GuiRadiumButton(0, this.width / 2 - 100, var3 + 24 * 3, "Back"));
		this.buttonList.add(protocol = new GuiRadiumButton(1, this.width / 2 - 100, var3 + 24 * -1, "Change Version (On 1.7.2)"));
		this.buttonList.add(new GuiRadiumButton(100, this.width / 2 - 100, var3 + 24 + 24 * -1, "MP3 Player"));
		protocol.enabled = true;
	}
	
	public void updateScreen() {
		if(Radium.getInstance().theProtocol == 4){
			protocol.displayString = "Change Version (On 1.7.2)";
		}else{
			protocol.displayString = "Change Version (On 1.7.9)";
		}
	}
	
	protected void actionPerformed(GuiButton p_146284_1_) {
		
		if (p_146284_1_.id == 1) {
			if(Radium.getInstance().theProtocol == 4){
				Radium.getInstance().theProtocol = 5;
			}else{
				Radium.getInstance().theProtocol = 4;
			}
		}
		
		if (p_146284_1_.id == 100) 
		{
			this.mc.displayGuiScreen(new GuiMP3Player());
		}
		
		if(p_146284_1_.id == 0){
			this.mc.displayGuiScreen(parentScreen);
		}
	}
	
	public void drawScreen(int par1, int par2, float par3) {
		this.drawDefaultBackground();
		fontRendererObj.drawString("Radium Options", (this.width / 2) - (fontRendererObj.getStringWidth("Radium Options") / 2), 20, 0xffffffff);
		super.drawScreen(par1, par2, par3);
	}
}
