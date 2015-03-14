package me.thelethalhamster.radium.module.modules;

import me.thelethalhamster.radium.module.Category;
import me.thelethalhamster.radium.module.Module;
import me.thelethalhamster.radium.util.RadiumUtil;
import me.thelethalhamster.radium.wrapper.RadiumWrapper;

public class Twerk extends Module
{
  private boolean skipTick;
  
  public Twerk()
  {
    super("Twerk", 0, Category.MISC, true);
  }
  
  public void onMotionUpdate()
  {
    if (!this.skipTick)
    {
      this.skipTick = true;
      RadiumUtil.setSneakKeyPressed(true);
    }
    else
    {
      this.skipTick = false;
      RadiumUtil.setSneakKeyPressed(false);
    }
  }
  
  public void onDisable()
  {
	  RadiumUtil.setSneakKeyPressed(false);
  }
}
