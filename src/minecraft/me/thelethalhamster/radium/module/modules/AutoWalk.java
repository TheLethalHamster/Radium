package me.thelethalhamster.radium.module.modules;

import me.thelethalhamster.radium.module.Category;
import me.thelethalhamster.radium.module.Module;
import me.thelethalhamster.radium.wrapper.RadiumWrapper;

public class AutoWalk extends Module{

	public AutoWalk() {
		super("AutoWalk", 0, Category.MOVEMENT, true);
	}

	@Override
	public void onMotionUpdate(){
		RadiumWrapper.getInstance().getGameSettings().keyBindForward.pressed = true;
	}
	
	@Override
	public void onDisable(){
		RadiumWrapper.getInstance().getGameSettings().keyBindForward.pressed = false;
	}
}
