package me.thelethalhamster.radium.module.modules;

import me.thelethalhamster.radium.module.Category;
import me.thelethalhamster.radium.module.Module;
import me.thelethalhamster.radium.values.Value;

import org.darkstorm.minecraft.gui.component.BoundedRangeComponent.ValueDisplay;

public class Step extends Module
{
	
	public Value stepHeight = new Value("Step Height", 1, 1, 1000, ValueDisplay.DECIMAL);
	
  public Step()
  {
    super("Step", 0, Category.MOVEMENT, true);
  }
  
  public void onMotionUpdate()
  {
    getPlayer().stepHeight = height();
  }
  
  public void onDisable()
  {
    getMinecraft().thePlayer.stepHeight = 0.5F;
  }
  
  private float height()
	{
		return (float)stepHeight.getValue();
	}
}
