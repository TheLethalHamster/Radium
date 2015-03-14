package me.thelethalhamster.radium.module.modules;

import net.minecraft.client.Minecraft;
import me.thelethalhamster.radium.module.Category;
import me.thelethalhamster.radium.module.Module;
import me.thelethalhamster.radium.module.ModuleManager;
import me.thelethalhamster.radium.util.RadiumUtil;

public class Glide extends Module
{
  public Glide()
  {
    super("Glide", 0, Category.MOVEMENT, true);
  }
  
  public void onMotionUpdate()
  {
    if ((RadiumUtil.getMotionY() <= -0.15D) && (!getPlayer().isInWater()) && (!Minecraft.getMinecraft().thePlayer.onGround) && (!getPlayer().isOnLadder()) && (!ModuleManager.getInstance().getModule(Flight.class).isState(true)))
    {
      RadiumUtil.setMotionY(-0.15D);
      RadiumUtil.setOnGround(true);
    }
  }
}
