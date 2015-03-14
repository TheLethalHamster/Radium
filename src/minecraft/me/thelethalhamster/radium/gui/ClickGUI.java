package me.thelethalhamster.radium.gui;

import org.lwjgl.input.Keyboard;

import me.thelethalhamster.radium.main.Radium;
import me.thelethalhamster.radium.module.Category;
import me.thelethalhamster.radium.module.Module;

public class ClickGUI extends Module{

	public ClickGUI() {
		super("ClickGUI", Keyboard.KEY_RSHIFT, Category.OTHER, false);
	}
	
	@Override
	public void onToggle(){
		getMinecraft().displayGuiScreen(Radium.getInstance().getGui());
	}
}
