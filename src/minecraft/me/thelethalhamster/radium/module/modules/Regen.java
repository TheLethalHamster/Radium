package me.thelethalhamster.radium.module.modules;

import me.thelethalhamster.radium.module.Category;
import me.thelethalhamster.radium.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C03PacketPlayer;

public class Regen extends Module
{
  private boolean shouldHeal = true;
  
  public Regen()
  {
    super("Regeneration", 0, Category.COMBAT, true);
  }
  
  public void onMotionUpdate()
  {
    if ((getPlayer().getFoodStats().getFoodLevel() > 17) && (!Minecraft.getMinecraft().playerController.isInCreativeMode()) && (getPlayer().getHealth() < 19.0F) && (getPlayer().onGround) && (this.shouldHeal))
    {
      this.shouldHeal = false;
      new Thread()
      {
        public void run()
        {
          for (short s = 0; s < 3100; s = (short)(s + 1)) {
            Regen.getInstance().getRadiumUtil().sendPacket(new C03PacketPlayer());
          }
          Regen.this.shouldHeal = true;
        }
      }.start();
    }
  }
}
