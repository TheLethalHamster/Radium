package me.thelethalhamster.radium.screens.audioplayer.components;

import java.text.DecimalFormat;

import me.thelethalhamster.radium.screens.audioplayer.GuiAudioPlayer;
import me.thelethalhamster.radium.util.RenderUtil;
import me.thelethalhamster.radium.values.Value;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

import org.lwjgl.input.Mouse;

public class Slider
{
	private String title;
	private Value value;
	private GuiAudioPlayer window;
	
	public Slider(String title, Value value, GuiAudioPlayer window)
	{
		this.title = title;
		this.value = value;
		this.window = window;
	}
	
	int z;
	
	public void drawSlider(int x, int z, int x1, int z1)
	{
		this.z = z;
		RenderUtil.drawBorderedRect(x + 2, z, window.w - 2, z + 12, 0xff000000, 0x66111111);
		
		double range = getValue().getMax() - getValue().getMin();
		//TODO: 
		int percent = (int)(((window.w - window.x) / range) * (getValue().getValue() - getValue().getMin()));
		
		if (getValue().getMin() != getValue().getValue())
			RenderUtil.drawBorderedRect(window.x + 2, z, window.x + percent - 2, z + 12, 0xff000000, 0x66ff6666);
		
		FontRenderer fr = Minecraft.getMinecraft().fontRenderer;
		fr.drawString(getTitle(), x + 4, z + 2, 0xffffffff);
		String value = new DecimalFormat("##").format((int)getValue().getValue());
		fr.drawString(value, window.w - 4 - fr.getStringWidth(value), z + 2, 0xffffffff);
		
		if (Mouse.isButtonDown(0))
		{
			if(isMouseOverButton(x1, z1)){
				double a = ((range * (window.x - x1 - 4)) / -(window.w - window.x)) + getValue().getMin();
				getValue().setValue(a);
			}
		}
	}
	
	public boolean isMouseOverButton(int x, int z)
	{
		return x >= window.x + 2 && x <= window.w - 2 && z >= this.z && z <= this.z + 12;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public Value getValue()
	{
		return value;
	}
	
}
