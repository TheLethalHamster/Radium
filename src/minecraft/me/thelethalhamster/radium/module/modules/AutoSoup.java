package me.thelethalhamster.radium.module.modules;

import me.thelethalhamster.radium.module.Category;
import me.thelethalhamster.radium.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class AutoSoup extends Module
{
  int soupCount = 0;
  int previousItem = -1;
  
  public AutoSoup()
  {
    super("AutoSoup", 0, Category.COMBAT, true);
  }
  
  public void PlayerMotionUpdate()
  {
    EntityPlayer thePlayer = getPlayer();
    
    this.soupCount = updateSoupCount();
    
    float playerHealth = thePlayer.getHealth();
    if ((playerHealth <= 14.0F) && (this.soupCount > 0))
    {
      this.previousItem = -1;
      int soupSlot = -1;
      for (int inventorySlot = 0; inventorySlot < 9; inventorySlot++)
      {
        ItemStack currentItem = thePlayer.inventory.getStackInSlot(inventorySlot);
        if ((currentItem != null) && (currentItem.getItem() == Item.getItemById(282))) {
          soupSlot = inventorySlot;
        }
      }
      if (soupSlot == -1)
      {
        for (int inventorySlot = 9; inventorySlot < 36; inventorySlot++) {
          if (thePlayer.inventoryContainer.getSlot(inventorySlot).getHasStack())
          {
            ItemStack currentItem = thePlayer.inventoryContainer.getSlot(inventorySlot).getStack();
            if ((currentItem != null) && (currentItem.getItem() == Item.getItemById(282))) {
              soupSlot = inventorySlot;
            }
          }
        }
        if (soupSlot == -1)
        {
          this.soupCount = 0;
          return;
        }
        cleanUp();
        getMinecraft().playerController.windowClick(thePlayer.inventoryContainer.windowId, soupSlot, 0, 1, getMinecraft().thePlayer);
        return;
      }
      this.previousItem = thePlayer.inventory.currentItem;
      thePlayer.inventory.currentItem = soupSlot;
      getMinecraft().playerController.sendUseItem(thePlayer, getMinecraft().theWorld, thePlayer.inventory.getStackInSlot(soupSlot));
    }
  }
  
  public void PlayerPostMotionUpdate()
  {
    if (this.previousItem != -1)
    {
      getMinecraft().playerController.onStoppedUsingItem(getMinecraft().thePlayer);
      getMinecraft().thePlayer.inventory.currentItem = this.previousItem;
      this.previousItem = -1;
      cleanUp();
    }
  }
  
  public void cleanUp()
  {
    boolean hasRoom = false;
    for (int inventorySlot = 9; inventorySlot < 36; inventorySlot++)
    {
      ItemStack currentItem = getMinecraft().thePlayer.inventoryContainer.getSlot(inventorySlot).getStack();
      if ((currentItem == null) || ((currentItem.getItem() == Item.getItemById(281)) && (currentItem.stackSize < 65))) {
        hasRoom = true;
      }
    }
    for (int inventorySlot = 36; inventorySlot < 45; inventorySlot++) {
      if (getMinecraft().thePlayer.inventoryContainer.getSlot(inventorySlot).getHasStack())
      {
        ItemStack currentItem = getMinecraft().thePlayer.inventoryContainer.getSlot(inventorySlot).getStack();
        if ((currentItem != null) && (currentItem.getItem() == Item.getItemById(281)))
        {
          if (hasRoom)
          {
            getMinecraft().playerController.windowClick(getMinecraft().thePlayer.inventoryContainer.windowId, inventorySlot, 0, 1, getMinecraft().thePlayer);
            break;
          }
          getMinecraft().playerController.windowClick(getMinecraft().thePlayer.inventoryContainer.windowId, inventorySlot, 0, 0, getMinecraft().thePlayer);
          getMinecraft().playerController.windowClick(getMinecraft().thePlayer.inventoryContainer.windowId, -999, 0, 0, getMinecraft().thePlayer);
          break;
        }
      }
    }
  }
  
  public int updateSoupCount()
  {
    int soupCount = 0;
    for (int inventorySlot = 0; inventorySlot < 45; inventorySlot++)
    {
      ItemStack currentItem = getMinecraft().thePlayer.inventoryContainer.getSlot(inventorySlot).getStack();
      if ((currentItem != null) && (currentItem.getItem() == Item.getItemById(282))) {
        soupCount++;
      }
    }
    return soupCount;
  }
}
