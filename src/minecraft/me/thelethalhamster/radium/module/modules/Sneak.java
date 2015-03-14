package me.thelethalhamster.radium.module.modules;

import me.thelethalhamster.radium.module.Category;
import me.thelethalhamster.radium.module.Module;

public class Sneak extends Module{

	public Sneak() {
		super("Sneak", 0, Category.PLAYER, true);
	}
	
	public void onEnable(){
		getGameSettings().keyBindSneak.pressed = true;
	}
	
	public void onMotionUpdate(){
		getGameSettings().keyBindSneak.pressed = true;
	}

	public void onDisable(){
		getGameSettings().keyBindSneak.pressed = false;
	}
}
