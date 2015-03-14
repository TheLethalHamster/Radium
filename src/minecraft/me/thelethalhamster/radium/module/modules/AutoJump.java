package me.thelethalhamster.radium.module.modules;

import me.thelethalhamster.radium.module.Category;
import me.thelethalhamster.radium.module.Module;
import me.thelethalhamster.radium.util.RadiumUtil;

public class AutoJump extends Module
{
  public AutoJump()
  {
    super("AutoJump", 0, Category.MOVEMENT, true);
  }
  
  public void onMotionUpdate()
  {
    RadiumUtil.setJumpKeyPressed(true);
  }
  
  public void onDisable()
  {
    RadiumUtil.setJumpKeyPressed(false);
  }
}
