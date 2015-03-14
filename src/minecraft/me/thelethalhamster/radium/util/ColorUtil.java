package me.thelethalhamster.radium.util;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

public class ColorUtil
{
  public static int primaryColor = -1;
  public static int secondaryColor = -11141291;
  public static int chestESPColor = -1;
  public static int aggressiveESPColor = -1;
  public static int passiveESPColor = -1;
  public static int playerESPColor = -5635926;
  public static List<Integer> mcColors = Arrays.asList(
    new Integer[] {
    Integer.valueOf(-1), 
    Integer.valueOf(-11141291), 
    Integer.valueOf(-11184641), 
    Integer.valueOf(-16777216), 
    Integer.valueOf(-16777046), 
    Integer.valueOf(-16733696), 
    Integer.valueOf(-16733526), 
    Integer.valueOf(-5636096), 
    Integer.valueOf(-5635926), 
    Integer.valueOf(-22016), 
    Integer.valueOf(-5592406), 
    Integer.valueOf(-11184811), 
    Integer.valueOf(-11141121), 
    Integer.valueOf(-43521), 
    Integer.valueOf(-171), 
    Integer.valueOf(-43691) });
  public static List<Color> mcRGB = Arrays.asList(
    new Color[] {
    new Color(0, 0, 0), 
    new Color(0, 0, 42), 
    new Color(0, 42, 0), 
    new Color(0, 42, 42), 
    new Color(42, 0, 0), 
    new Color(42, 0, 42), 
    new Color(42, 42, 0), 
    new Color(42, 42, 42), 
    new Color(21, 21, 21), 
    new Color(21, 21, 63), 
    new Color(21, 63, 21), 
    new Color(21, 63, 63), 
    new Color(63, 21, 21), 
    new Color(63, 21, 63), 
    new Color(63, 63, 21), 
    new Color(63, 63, 63) });
  
  public static void toggleColor(int index)
  {
    int currentIndex = mcColors.indexOf(Integer.valueOf(getIndex(index)));
    if (currentIndex != mcColors.size()) {
      if (index == 1)
      {
        if (primaryColor == -43691)
        {
          primaryColor = -1;
          return;
        }
        primaryColor = ((Integer)mcColors.get(currentIndex + 1)).intValue();
      }
      else if (index == 2)
      {
        if (secondaryColor == -43691)
        {
          secondaryColor = -1;
          return;
        }
        secondaryColor = ((Integer)mcColors.get(currentIndex + 1)).intValue();
      }
      else if (index == 3)
      {
        if (chestESPColor == -1)
        {
          chestESPColor = -16777216;
          return;
        }
        if (chestESPColor == -22016)
        {
          chestESPColor = -1;
          return;
        }
        chestESPColor = ((Integer)mcColors.get(currentIndex + 1)).intValue();
      }
      else if (index == 4)
      {
        if (aggressiveESPColor == -1)
        {
          aggressiveESPColor = -16777216;
          return;
        }
        if (aggressiveESPColor == -16777216)
        {
          aggressiveESPColor = -16733696;
          return;
        }
        if (aggressiveESPColor == -16733696)
        {
          aggressiveESPColor = -5635926;
          return;
        }
        if (aggressiveESPColor == -5635926)
        {
          aggressiveESPColor = -1;
          return;
        }
        aggressiveESPColor = ((Integer)mcColors.get(currentIndex + 1)).intValue();
      }
      else if (index == 5)
      {
        if (passiveESPColor == -1)
        {
          passiveESPColor = -16777216;
          return;
        }
        if (passiveESPColor == -16777216)
        {
          passiveESPColor = -16733696;
          return;
        }
        if (passiveESPColor == -16733696)
        {
          passiveESPColor = -5635926;
          return;
        }
        if (passiveESPColor == -5635926)
        {
          passiveESPColor = -1;
          return;
        }
        passiveESPColor = ((Integer)mcColors.get(currentIndex + 1)).intValue();
      }
      else if (index == 6)
      {
        if (playerESPColor == -1)
        {
          playerESPColor = -16777216;
          return;
        }
        if (playerESPColor == -16777216)
        {
          playerESPColor = -16733696;
          return;
        }
        if (playerESPColor == -16733696)
        {
          playerESPColor = -5635926;
          return;
        }
        if (playerESPColor == -5635926)
        {
          playerESPColor = -1;
          return;
        }
        playerESPColor = ((Integer)mcColors.get(currentIndex + 1)).intValue();
      }
    }
  }
  
  private static int getIndex(int index)
  {
    if (index == 1) {
      return primaryColor;
    }
    if (index == 2) {
      return secondaryColor;
    }
    if (index == 3) {
      return chestESPColor;
    }
    if (index == 4) {
      return aggressiveESPColor;
    }
    if (index == 5) {
      return passiveESPColor;
    }
    if (index == 6) {
      return playerESPColor;
    }
    return 0;
  }
  
  public static Color hexToRGBA(int hexCode)
  {
    float aValue = (hexCode >> 24 & 0xFF) / 255.0F;
    float rValue = (hexCode >> 16 & 0xFF) / 255.0F;
    float gValue = (hexCode >> 8 & 0xFF) / 255.0F;
    float bValue = (hexCode & 0xFF) / 255.0F;
    
    return new Color(rValue, gValue, bValue, aValue);
  }
}
