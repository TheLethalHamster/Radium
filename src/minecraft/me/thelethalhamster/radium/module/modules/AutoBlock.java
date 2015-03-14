package me.thelethalhamster.radium.module.modules;

import me.thelethalhamster.radium.module.Category;
import me.thelethalhamster.radium.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.settings.GameSettings;

public class AutoBlock extends Module
{
  public AutoBlock()
  {
    super("AutoBlock", 0, Category.COMBAT, true);
  }
  
  public void setUseItemKeyPressed(boolean pressed)
  {
    this.getMinecraft().gameSettings.keyBindUseItem.pressed = pressed;
  }
  
  public void onMotionUpdate()
  {
    if (!isState(true)) {
      return;
    }
    if (this.getMinecraft().thePlayer.getItemInUse() != null) {
      setUseItemKeyPressed(false);
    } else {
      setUseItemKeyPressed(true);
    }
    super.onMotionUpdate();
  }
  
  public void onDisable()
  {
    setUseItemKeyPressed(false);
    super.onDisable();
  }
}
