package me.thelethalhamster.radium.module.modules;

import me.thelethalhamster.radium.module.Category;
import me.thelethalhamster.radium.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.util.MovementInput;

public class BunnyHop extends Module
{
  public BunnyHop()
  {
    super("BunnyHop", 0, Category.PLAYER, true);
  }
  
  public void onMotionUpdate()
  {
    if ((this.getMinecraft().thePlayer.movementInput.moveForward > 0.0F) && (this.getMinecraft().thePlayer.isCollidedVertically) && (this.getMinecraft().thePlayer.onGround))
    {
      this.getMinecraft().thePlayer.setSprinting(true);
      this.getMinecraft().thePlayer.motionY = 0.4000000059604645D;
    }
  }
}
