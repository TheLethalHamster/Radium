package me.thelethalhamster.radium.module.modules;

import me.thelethalhamster.radium.module.Category;
import me.thelethalhamster.radium.module.Module;
import net.minecraft.entity.player.EntityPlayer;

public class Spider extends Module
{
  public Spider()
  {
    super("Spider", 0, Category.MOVEMENT, true);
  }
  
  public void onMotionUpdate()
  {
    EntityPlayer thePlayer = getPlayer();
    if (thePlayer.isCollidedHorizontally)
    {
      thePlayer.motionY = 0.2D;
      
      float climbCount = 0.15F;
      if (thePlayer.motionX < -climbCount) {
        thePlayer.motionX = (-climbCount);
      }
      if (thePlayer.motionX > climbCount) {
        thePlayer.motionX = climbCount;
      }
      if (thePlayer.motionZ < -climbCount) {
        thePlayer.motionZ = (-climbCount);
      }
      if (thePlayer.motionZ > climbCount) {
        thePlayer.motionZ = climbCount;
      }
      thePlayer.fallDistance = 0.0F;
      if (thePlayer.motionY < -0.15D) {
        thePlayer.motionY = -0.15D;
      }
    }
  }
}
