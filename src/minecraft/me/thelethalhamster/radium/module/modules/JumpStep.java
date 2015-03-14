package me.thelethalhamster.radium.module.modules;

import me.thelethalhamster.radium.module.Category;
import me.thelethalhamster.radium.module.Module;
import me.thelethalhamster.radium.values.Value;

import org.darkstorm.minecraft.gui.component.BoundedRangeComponent.ValueDisplay;

public class JumpStep extends Module{
	
	public Value jumpstepHeight = new Value("JumpStep Height", 0.5, 0.5, 10, ValueDisplay.DECIMAL);

	public JumpStep() {
		super("JumpStep", 0, Category.MOVEMENT, true);
	}
	
	public void onMotionUpdate(){
		if(canStep()){
			getPlayer().motionY = height();
		}
	}
	
	public boolean canStep(){
		return getState() && getPlayer().onGround && getPlayer().isCollidedHorizontally && !getPlayer().isOnLadder();
	}
	
	private float height(){
		return (float)jumpstepHeight.getValue();
	}
}
