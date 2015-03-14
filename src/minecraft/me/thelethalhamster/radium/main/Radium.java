package me.thelethalhamster.radium.main;

import org.darkstorm.minecraft.gui.GuiManagerImpl;
import org.darkstorm.minecraft.gui.theme.radium.RadiumTheme;
import org.darkstorm.minecraft.gui.util.GuiManagerDisplayScreen;

import me.thelethalhamster.radium.file.FileManager;
import me.thelethalhamster.radium.module.HookManager;
import me.thelethalhamster.radium.module.ModuleManager;
import me.thelethalhamster.radium.override.GuiIngameHook;
import me.thelethalhamster.radium.wrapper.RadiumWrapper;
import net.minecraft.client.Minecraft;

public class Radium {
	
	private static Radium theRadium = new Radium();
	private GuiManagerDisplayScreen gui; 
	private GuiManagerImpl guiManager;
	public int theProtocol = 4;
	
	public static Radium getInstance(){
		return theRadium;
	}
	
	public GuiManagerImpl getGuiManager() {
		if (guiManager == null) {
			guiManager = new GuiManagerImpl();
			guiManager.setTheme(new RadiumTheme());
			guiManager.setup();
		}
		return guiManager;
	}

	public GuiManagerDisplayScreen getGui() { 
		if (gui == null) {
			gui = new GuiManagerDisplayScreen(getGuiManager()); 
		}
		return gui;
	}
	
	public void initClient(){
		ModuleManager.getInstance();
		RadiumWrapper.getInstance();
		HookManager.getInstance();
		Minecraft.getMinecraft().ingameGUI = new GuiIngameHook(Minecraft.getMinecraft());
		FileManager.getInstance();
	}
	
	public String getClientName(){
		return "Radium";
	}
}