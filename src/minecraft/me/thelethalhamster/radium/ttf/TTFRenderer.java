package me.thelethalhamster.radium.ttf;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class TTFRenderer extends FontRenderer {
	//private final UnicodeFont font;
	
    public StringCache stringCache;
    int i11;

	@SuppressWarnings("unchecked")
	public TTFRenderer(String fontname, int size) {
		super(Minecraft.getMinecraft().gameSettings, new ResourceLocation("textures/font/ascii.png"), Minecraft.getMinecraft().renderEngine /* renderEngine */, false);
		stringCache = new StringCache(colorCode);
        stringCache.setDefaultFont(fontname,
                        size, true);
	}

	private int renderString(String par1Str, int par2, int par3, int par4, boolean par5){
		if (par1Str == null) {
			return 0;
		} else {
			if ((par4 & -67108864) == 0) {
				par4 |= -16777216;
			}
			if (par5) {
				par4 = ((par4 & 16579836) >> 2) | (par4 & -16777216);
			}
                red = ((par4 >> 16) & 255) / 255.0F;
                blue = ((par4 >> 8) & 255) / 255.0F;
                green = (par4 & 255) / 255.0F;
                alpha = ((par4 >> 24) & 255) / 255.0F;
                GL11.glColor4f(red, blue, green, alpha);
                posX = par2;
                posY = par3;
                if (stringCache != null) {
                	posX += stringCache.renderString(par1Str, par2, par3, par4, par5);
                }
                return (int) posX;
		}
	}
	
	private void resetStyles()
    {
        this.randomStyle = false;
        this.boldStyle = false;
        this.italicStyle = false;
        this.underlineStyle = false;
        this.strikethroughStyle = false;
    }

	@Override
	public int drawString(String par1Str, int par2, int par3, int par4, boolean par5){
		resetStyles();

        int var6;

        if (par5) {
       	 final int i;
           	 var6 = this.renderString(par1Str, par2, par3, par4, true);
                var6 = Math.max(var6, renderString(par1Str, par2, par3, par4, false));
        } else {
       	 var6 = renderString(par1Str, par2, par3, par4, false);
        }

        return var6;
	}

	@Override
	public int drawStringWithShadow(String string, int x, int y, int color) {
		return drawString(string, x, y, color);
	}

	public int getStringWidth(String string) {
			return stringCache.getStringWidth(string);
	}
}