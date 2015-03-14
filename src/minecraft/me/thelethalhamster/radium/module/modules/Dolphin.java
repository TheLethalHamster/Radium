package me.thelethalhamster.radium.module.modules;

import me.thelethalhamster.radium.module.Category;
import me.thelethalhamster.radium.module.Module;
import me.thelethalhamster.radium.wrapper.RadiumWrapper;

public class Dolphin extends Module{
	public Dolphin() {
		super("Dolphin", 0, Category.WORLD, true);
	}
	
	private int count = 0;
	
	@Override
	public void preMotionUpdate() {
		count++;
		if(count >= 3) {
			if(RadiumWrapper.getInstance().getMinecraft().thePlayer.handleWaterMovement()) {
				RadiumWrapper.getInstance().getMinecraft().thePlayer.motionY = 0.1D;
			}
			count = 0;
		}
	}
}
