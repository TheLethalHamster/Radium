package me.thelethalhamster.radium.wrapper;

import me.thelethalhamster.radium.util.RadiumUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.settings.GameSettings;

public class RadiumWrapper {
	
	private static RadiumWrapper theRadiumWrapper = new RadiumWrapper();
	private Minecraft mc = Minecraft.getMinecraft();
	private RadiumUtil radiumUtil = new RadiumUtil();
	
	public static RadiumWrapper getInstance(){
		return theRadiumWrapper;
	}
	
	public RadiumUtil getRadiumUtil(){
		return radiumUtil;
	}
	
	public Minecraft getMinecraft(){
		return mc;
	}
	
	public EntityClientPlayerMP getPlayer(){
		return mc.thePlayer;
	}
	
	public WorldClient getWorld(){
		return mc.theWorld;
	}
	
	public EntityRenderer getEntityRenderer(){
		return mc.entityRenderer;
	}
	
	public EffectRenderer getEffectRenderer(){
		return mc.effectRenderer;
	}
	
	public GameSettings getGameSettings(){
		return mc.gameSettings;
	}
}
