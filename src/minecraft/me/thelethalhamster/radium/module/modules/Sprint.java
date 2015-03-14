package me.thelethalhamster.radium.module.modules;

import me.thelethalhamster.radium.module.Category;
import me.thelethalhamster.radium.module.Module;

public class Sprint extends Module{

	public Sprint() {
		super("Sprint", 0, Category.MOVEMENT, true);
	}
	
	@Override
	public void onMotionUpdate(){
		if(canSprint()){
			getPlayer().setSprinting(true);
		}
	}
	
	public boolean canSprint(){
		return getMinecraft().gameSettings.keyBindForward.getIsKeyPressed() && getPlayer().getFoodStats().getFoodLevel() > 14 && !getPlayer().isOnLadder() && !getPlayer().handleLavaMovement() && !getPlayer().handleWaterMovement();
	}

}
