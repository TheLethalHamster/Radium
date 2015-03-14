package me.thelethalhamster.radium.module.modules;

import me.thelethalhamster.radium.module.Category;
import me.thelethalhamster.radium.module.Module;

public class GodMode extends Module
{
  public GodMode()
  {
    super("GodMode", 0, Category.PLAYER, true);
  }
  
  public void onEnable()
  {
    getPlayer().isDead = true;
  }
  
  public void onDisable()
  {
    getPlayer().isDead = false;
  }
}
