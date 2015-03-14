package me.thelethalhamster.radium.module.modules;

import me.thelethalhamster.radium.module.Category;
import me.thelethalhamster.radium.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.FoodStats;

public class AutoEat extends Module
{
  private boolean eatingFood = false;
  private int previousSlot = -1;
  private boolean finishedEating = false;
  
  public AutoEat()
  {
    super("AutoEat", 0, Category.PLAYER, true);
  }
  
  public void preMotionUpdate()
  {
    EntityPlayer thePlayer = getPlayer();
    InventoryPlayer theInventory = thePlayer.inventory;
    if ((this.previousSlot != -1) && (this.finishedEating) && (this.eatingFood))
    {
      thePlayer.inventory.currentItem = this.previousSlot;
      
      getMinecraft().gameSettings.keyBindUseItem.pressed = false;
      
      this.eatingFood = false;
    }
    if (thePlayer.getFoodStats().getFoodLevel() < 18) {
      for (int inventorySlot = 0; inventorySlot < 9; inventorySlot++)
      {
        ItemStack currentItem = theInventory.getStackInSlot(inventorySlot);
        if ((currentItem != null) && ((currentItem.getItem() instanceof ItemFood)))
        {
          this.previousSlot = theInventory.currentItem;
          
          theInventory.currentItem = inventorySlot;
          
          getMinecraft().gameSettings.keyBindUseItem.pressed = true;
          if (thePlayer.getFoodStats().getFoodLevel() > 16)
          {
            this.eatingFood = true;
            
            this.finishedEating = true;
          }
        }
      }
    }
  }
}
