package me.thelethalhamster.radium.module.modules;

import me.thelethalhamster.radium.module.Category;
import me.thelethalhamster.radium.module.Module;
import me.thelethalhamster.radium.util.RadiumUtil;

import org.lwjgl.input.Keyboard;

public class FastLadder extends Module
{
  private int ticks = 0;
  
  public FastLadder()
  {
    super("FastLadder", 0, Category.MOVEMENT, true);
  }
  
  public void onMotionUpdate()
  {
    this.ticks += 1;
    if ((getPlayer().isOnLadder()) && (Keyboard.isKeyDown(RadiumUtil.getForwardCode()))) {
      RadiumUtil.setMotionY(0.25D);
    } else if ((getPlayer().isOnLadder()) && (!Keyboard.isKeyDown(RadiumUtil.getForwardCode()))) {
      RadiumUtil.setMotionY(-0.25D);
    }
  }
}
