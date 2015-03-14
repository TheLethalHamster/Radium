package me.thelethalhamster.radium.module.modules;

import me.thelethalhamster.radium.module.Category;
import me.thelethalhamster.radium.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;

public class Astronaut extends Module
{
  boolean on;
  
  public Astronaut()
  {
    super("Astronaut", 0, Category.PLAYER, true);
  }
  
  public void onEnable()
  {
    this.on = this.getMinecraft().gameSettings.smoothCamera;
    this.getMinecraft().gameSettings.smoothCamera = true;
    super.onEnable();
  }
  
  public void onMotionUpdate()
  {
    if (!isState(true)) {
      return;
    }
    net.minecraft.util.Timer.timerSpeed = 0.2F;
    super.onMotionUpdate();
  }
  
  public void onDisable()
  {
    this.getMinecraft().gameSettings.smoothCamera = this.on;
    net.minecraft.util.Timer.timerSpeed = 1.0F;
    super.onDisable();
  }
}
