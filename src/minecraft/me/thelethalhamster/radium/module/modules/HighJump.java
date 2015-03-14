package me.thelethalhamster.radium.module.modules;

import me.thelethalhamster.radium.module.Category;
import me.thelethalhamster.radium.module.Module;
import me.thelethalhamster.radium.values.Value;
import net.minecraft.potion.PotionEffect;

import org.darkstorm.minecraft.gui.component.BoundedRangeComponent.ValueDisplay;

public class HighJump extends Module
{
	
	public Value jumpHeight = new Value("HighJump Height", 5, 1, 10, ValueDisplay.INTEGER);
	
  public HighJump()
  {
    super("HighJump", 0, Category.PLAYER, true);
  }
  
  public void onGameTick()
  {
    if (getMinecraft().theWorld == null) {
      return;
    }
    getMinecraft().thePlayer.addPotionEffect(new PotionEffect(8, 999999999, height()));
  }
  
  public void onDisable()
  {
    getMinecraft().thePlayer.removePotionEffect(8);
  }
  
  private int height(){
	  return (int)jumpHeight.getValue();
  }
}
