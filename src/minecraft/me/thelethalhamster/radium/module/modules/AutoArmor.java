package me.thelethalhamster.radium.module.modules;

import java.util.Arrays;
import java.util.List;

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

public class AutoArmor extends Module
{
  public AutoArmor()
  {
    super("AutoArmor", 0, Category.PLAYER, true);
  }
  
  public void preMotionUpdate()
  {
    ItemStack playerHelmet = getPlayer().inventory.armorItemInSlot(3);
    ItemStack playerChestplate = getPlayer().inventory.armorItemInSlot(2);
    ItemStack playerLeggings = getPlayer().inventory.armorItemInSlot(1);
    ItemStack playerBoots = getPlayer().inventory.armorItemInSlot(0);
    if (playerHelmet == null) {
      wearHelmet();
    } else {
      compareItem(playerHelmet, this.helmetPriority, 3);
    }
    if (playerChestplate == null) {
      wearChestplate();
    } else {
      compareItem(playerChestplate, this.chestPriority, 2);
    }
    if (playerLeggings == null) {
      wearLeggings();
    } else {
      compareItem(playerLeggings, this.legsPriority, 1);
    }
    if (playerBoots == null) {
      wearBoots();
    } else {
      compareItem(playerBoots, this.bootsPriority, 0);
    }
  }
  
  private void removeArmor(int armorPiece)
  {
    getMinecraft().playerController.windowClick(0, 8 - armorPiece, 0, 1, getMinecraft().thePlayer);
  }
  
  private void compareItem(ItemStack currentItem, Integer[] itemPriority, int armorPiece)
  {
    for (int itemSlot = 44; itemSlot >= 9; itemSlot--)
    {
      ItemStack itemStack = getMinecraft().thePlayer.inventoryContainer.getSlot(itemSlot).getStack();
      if (itemStack != null) {
        if (Arrays.asList(itemPriority).indexOf(Integer.valueOf(Item.getIdFromItem(itemStack.getItem()))) > Arrays.asList(itemPriority).indexOf(Integer.valueOf(Item.getIdFromItem(currentItem.getItem())))) {
          if ((itemSlot >= 36) && (itemSlot <= 44))
          {
            getMinecraft().thePlayer.inventory.currentItem = (itemSlot - 36);
            removeArmor(armorPiece);
            getMinecraft().playerController.windowClick(0, itemSlot, 0, 1, getMinecraft().thePlayer);
          }
          else
          {
            removeArmor(armorPiece);
            getMinecraft().playerController.windowClick(0, itemSlot, 0, 1, getMinecraft().thePlayer);
          }
        }
      }
    }
  }
  
  private void wearHelmet()
  {
    for (int itemSlot = 44; itemSlot >= 9; itemSlot--)
    {
      ItemStack itemStack = getMinecraft().thePlayer.inventoryContainer.getSlot(itemSlot).getStack();
      if (itemStack != null) {
        if ((itemSlot >= 36) && (itemSlot <= 44))
        {
          if (Arrays.asList(this.helmetPriority).contains(Integer.valueOf(Item.getIdFromItem(itemStack.getItem()))))
          {
            getMinecraft().thePlayer.inventory.currentItem = (itemSlot - 36);
            getMinecraft().playerController.windowClick(0, itemSlot, 0, 1, getMinecraft().thePlayer);
          }
        }
        else if (Arrays.asList(this.helmetPriority).contains(Integer.valueOf(Item.getIdFromItem(itemStack.getItem())))) {
          getMinecraft().playerController.windowClick(0, itemSlot, 0, 1, getMinecraft().thePlayer);
        }
      }
    }
  }
  
  private void wearChestplate()
  {
    for (int itemSlot = 44; itemSlot >= 9; itemSlot--)
    {
      ItemStack itemStack = getMinecraft().thePlayer.inventoryContainer.getSlot(itemSlot).getStack();
      if (itemStack != null) {
        if ((itemSlot >= 36) && (itemSlot <= 44))
        {
          if (Arrays.asList(this.chestPriority).contains(Integer.valueOf(Item.getIdFromItem(itemStack.getItem()))))
          {
            getMinecraft().thePlayer.inventory.currentItem = (itemSlot - 36);
            getMinecraft().playerController.windowClick(0, itemSlot, 0, 1, getMinecraft().thePlayer);
          }
        }
        else if (Arrays.asList(this.chestPriority).contains(Integer.valueOf(Item.getIdFromItem(itemStack.getItem())))) {
          getMinecraft().playerController.windowClick(0, itemSlot, 0, 1, getMinecraft().thePlayer);
        }
      }
    }
  }
  
  private void wearLeggings()
  {
    for (int itemSlot = 44; itemSlot >= 9; itemSlot--)
    {
      ItemStack itemStack = getMinecraft().thePlayer.inventoryContainer.getSlot(itemSlot).getStack();
      if (itemStack != null) {
        if ((itemSlot >= 36) && (itemSlot <= 44))
        {
          if (Arrays.asList(this.legsPriority).contains(Integer.valueOf(Item.getIdFromItem(itemStack.getItem()))))
          {
            getMinecraft().thePlayer.inventory.currentItem = (itemSlot - 36);
            getMinecraft().playerController.windowClick(0, itemSlot, 0, 1, getMinecraft().thePlayer);
          }
        }
        else if (Arrays.asList(this.legsPriority).contains(Integer.valueOf(Item.getIdFromItem(itemStack.getItem())))) {
          getMinecraft().playerController.windowClick(0, itemSlot, 0, 1, getMinecraft().thePlayer);
        }
      }
    }
  }
  
  private void wearBoots()
  {
    for (int itemSlot = 44; itemSlot >= 9; itemSlot--)
    {
      ItemStack itemStack = getMinecraft().thePlayer.inventoryContainer.getSlot(itemSlot).getStack();
      if (itemStack != null) {
        if ((itemSlot >= 36) && (itemSlot <= 44))
        {
          if (Arrays.asList(this.bootsPriority).contains(Integer.valueOf(Item.getIdFromItem(itemStack.getItem()))))
          {
            getMinecraft().thePlayer.inventory.currentItem = (itemSlot - 36);
            getMinecraft().playerController.windowClick(0, itemSlot, 0, 1, getMinecraft().thePlayer);
          }
        }
        else if (Arrays.asList(this.bootsPriority).contains(Integer.valueOf(Item.getIdFromItem(itemStack.getItem())))) {
          getMinecraft().playerController.windowClick(0, itemSlot, 0, 1, getMinecraft().thePlayer);
        }
      }
    }
  }
  
  private final Integer[] helmetPriority = {
    Integer.valueOf(298), Integer.valueOf(314), Integer.valueOf(302), Integer.valueOf(306), Integer.valueOf(310) };
  private final Integer[] chestPriority = {
    Integer.valueOf(299), Integer.valueOf(315), Integer.valueOf(303), Integer.valueOf(307), Integer.valueOf(311) };
  private final Integer[] legsPriority = {
    Integer.valueOf(300), Integer.valueOf(316), Integer.valueOf(304), Integer.valueOf(308), Integer.valueOf(312) };
  private final Integer[] bootsPriority = {
    Integer.valueOf(301), Integer.valueOf(317), Integer.valueOf(305), Integer.valueOf(309), Integer.valueOf(313) };
}
