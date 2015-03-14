package me.thelethalhamster.radium.override;

import org.lwjgl.opengl.GL11;

import me.thelethalhamster.radium.ttf.TTFRenderer;
import me.thelethalhamster.radium.util.RadiumUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;

public class GuiRadiumButton extends GuiButton{

	FontRenderer fr = new TTFRenderer("Arial Bold", 17);

	public GuiRadiumButton(int par1, int par2, int par3, String par4Str) {
		super(par1, par2, par3, par4Str);
	}
	
	public GuiRadiumButton(int par1, int par2, int par3, int par4, int par5,
			String par6Str) {
		super(par1, par2, par3, par4, par5, par6Str);
	}

	public void drawButton(Minecraft p_146112_1_, int p_146112_2_, int p_146112_3_)
    {
        if (this.field_146125_m)
        {
            
            this.field_146123_n = p_146112_2_ >= this.field_146128_h && p_146112_3_ >= this.field_146129_i && p_146112_2_ < this.field_146128_h + this.field_146120_f && p_146112_3_ < this.field_146129_i + this.field_146121_g;
            int var5 = this.getHoverState(this.field_146123_n);
            if(field_146123_n){
            	RadiumUtil.drawRoundedRect(field_146128_h - 2, field_146129_i - 2, field_146128_h + field_146120_f + 2, field_146129_i + field_146121_g + 2, 0xff000000, 0x64ffffff);
            }else{
            	RadiumUtil.drawRoundedRect(field_146128_h, field_146129_i, field_146128_h + field_146120_f, field_146129_i + field_146121_g, 0xff000000, 0x1Effffff);
            }
            this.mouseDragged(p_146112_1_, p_146112_2_, p_146112_3_);
            int var6 = 0xffffffff;

            if (!this.enabled)
            {
                var6 = 0xff000000;
            }
            else if (this.field_146123_n)
            {
                //var6 = 0xffff0000;
            }
            this.drawCenteredString(fr, this.displayString, this.field_146128_h + this.field_146120_f / 2, this.field_146129_i + (this.field_146121_g - 8) / 2, var6); 
        }
    }
}
