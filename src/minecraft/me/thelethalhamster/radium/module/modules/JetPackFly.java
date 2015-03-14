package me.thelethalhamster.radium.module.modules;

import me.thelethalhamster.radium.module.Category;
import me.thelethalhamster.radium.module.Module;
import me.thelethalhamster.radium.values.Value;

import org.darkstorm.minecraft.gui.component.BoundedRangeComponent.ValueDisplay;

public class JetPackFly extends Module{
	
	public Value jetpackflySpeed = new Value("JetPackFly Speed", 0.4, 0.3, 10, ValueDisplay.DECIMAL);

	public JetPackFly() {
		super("JetPackFly", 0, Category.MOVEMENT, true);
	}
	
	@Override
	public void onMotionUpdate(){
		if(getMinecraft().gameSettings.keyBindJump.getIsKeyPressed()){
			getPlayer().motionY = speed();
		}
	}
	
	public float speed(){
		return (float)jetpackflySpeed.getValue();
	}
}
