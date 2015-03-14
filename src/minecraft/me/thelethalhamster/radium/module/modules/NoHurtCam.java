package me.thelethalhamster.radium.module.modules;

import me.thelethalhamster.radium.module.Category;
import me.thelethalhamster.radium.module.Module;

public class NoHurtCam extends Module{

	public NoHurtCam() {
		super("NoHurtCam", 0, Category.PLAYER, true);
	}
	
	public void onMotionUpdate(){
		getPlayer().maxHurtTime = 0;
	}

}
