package me.thelethalhamster.radium.module.modules;

import me.thelethalhamster.radium.module.Category;
import me.thelethalhamster.radium.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;

public class AutoMine extends Module
{
  public AutoMine()
  {
    super("AutoMine", 0, Category.PLAYER, true);
  }
  
  public void preMotionUpdate()
  {
    if (!isState(true)) {
      return;
    }
    getMinecraft().gameSettings.keyBindAttack.pressed = true;
  }
  
  public void onDisable()
  {
    getMinecraft().gameSettings.keyBindAttack.pressed = false;
  }
}
