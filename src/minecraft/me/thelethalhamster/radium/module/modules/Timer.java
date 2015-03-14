package me.thelethalhamster.radium.module.modules;

import me.thelethalhamster.radium.module.Category;
import me.thelethalhamster.radium.module.Module;
import me.thelethalhamster.radium.values.Value;

import org.darkstorm.minecraft.gui.component.BoundedRangeComponent.ValueDisplay;

public class Timer extends Module
{
	
	public Value speed = new Value("Timer Speed", 3, 1, 50, ValueDisplay.INTEGER);
	
  public Timer()
  {
    super("Timer", 0, Category.MOVEMENT, true);
  }
  
  public void onMotionUpdate()
  {
    getMinecraft().timer.timerSpeed = speed();
  }
  
  public void onDisable()
  {
    getMinecraft().timer.timerSpeed = 1.0F;
  }
  
  public int speed(){
	  return (int)speed.getValue();
  }
}
