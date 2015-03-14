package me.thelethalhamster.radium.module.modules;

import me.thelethalhamster.radium.module.Category;
import me.thelethalhamster.radium.module.Module;
import net.minecraft.client.Minecraft;

public class Flight extends Module{
	
	public Flight() {
		super("Flight", 0, Category.MOVEMENT, true);
	}
	
	public void onMotionUpdate(){
		Minecraft.getMinecraft().thePlayer.capabilities.isFlying = true;
	}
	
	public void onDisable(){
		Minecraft.getMinecraft().thePlayer.capabilities.isFlying = false;
	}
}
