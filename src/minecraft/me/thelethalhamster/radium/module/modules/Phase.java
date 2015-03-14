package me.thelethalhamster.radium.module.modules;

import me.thelethalhamster.radium.module.Category;
import me.thelethalhamster.radium.module.Module;

public class Phase extends Module
{
  public Phase()
  {
    super("Phase", 0, Category.WORLD, true);
  }
  
  public void onEnable()
  {
    if (getMinecraft().thePlayer != null) {
      getMinecraft().thePlayer.noClip = true;
    }
  }
  
  public void onDisable()
  {
    getMinecraft().thePlayer.noClip = false;
  }
}
