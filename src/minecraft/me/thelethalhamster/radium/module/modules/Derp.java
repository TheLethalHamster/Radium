package me.thelethalhamster.radium.module.modules;

import java.util.Random;

import me.thelethalhamster.radium.module.Category;
import me.thelethalhamster.radium.module.Module;

public class Derp extends Module
{
  public Derp()
  {
    super("Derp", 0, Category.MISC, true);
  }
  
  private final Random random = new Random();
  
  public void onMotionUpdate()
  {
    float yaw = this.random.nextBoolean() ? this.random.nextInt(180) : 
      -this.random.nextInt(180);
    float pitch = this.random.nextBoolean() ? this.random.nextInt(90) : 
      -this.random.nextInt(90);
    this.getMinecraft().thePlayer.rotationYaw = yaw;
    this.getMinecraft().thePlayer.rotationPitch = pitch;
    if ((!this.getMinecraft().thePlayer.isSprinting()) && (
      (!this.getMinecraft().thePlayer.isUsingItem()) || (!this.getMinecraft().thePlayer.isSwingInProgress) || (this.getMinecraft().thePlayer.swingProgress < 0.0F)))
    {
      this.getMinecraft().thePlayer.rotationYawHead = yaw;
      if ((!this.getMinecraft().thePlayer.isSwingInProgress) && 
        (this.random.nextBoolean())) {
        this.getMinecraft().thePlayer.swingItem();
      }
    }
  }
}
