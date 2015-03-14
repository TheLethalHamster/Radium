package me.thelethalhamster.radium.module.modules;

import me.thelethalhamster.radium.module.Category;
import me.thelethalhamster.radium.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.potion.PotionEffect;

public class SpeedMine extends Module
{
  public SpeedMine()
  {
    super("SpeedMine", 0, Category.MISC, true);
  }
  
  public void onEnable()
  {
    Minecraft.getMinecraft().thePlayer.addPotionEffect(new PotionEffect(3, 999999999, 1));
  }
  
  public void onDisable()
  {
    Minecraft.getMinecraft().thePlayer.removePotionEffect(3);
  }
}
