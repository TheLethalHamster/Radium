package me.thelethalhamster.radium.module.modules;

import java.util.Iterator;
import java.util.List;

import me.thelethalhamster.radium.module.Category;
import me.thelethalhamster.radium.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StringUtils;

public class BowAimbot extends Module
{
  private float targetPitch;
  private float targetYaw;
  private EntityLivingBase targetEntity;
  
  public BowAimbot()
  {
    super("BowAimBot", 0, Category.COMBAT, true);
  }
  
  public void PlayerMotionUpdate()
  {
    if (!isState(true)) {
      return;
    }
    if (getMinecraft().thePlayer.getCurrentEquippedItem() != null) {
      if ((getMinecraft().thePlayer.getCurrentEquippedItem().getItem() instanceof ItemBow))
      {
        this.targetEntity = getCursorEntity();
        if (this.targetEntity == null) {
          return;
        }
        this.targetPitch = getMinecraft().thePlayer.rotationPitch;
        this.targetYaw = getMinecraft().thePlayer.rotationYaw;
        silentAim(this.targetEntity);
      }
    }
  }
  
  public void PlayerPostMotionUpdate()
  {
    if ((this.targetEntity != null) && (getMinecraft().thePlayer.getCurrentEquippedItem() != null) && ((getMinecraft().thePlayer.getCurrentEquippedItem().getItem() instanceof ItemBow)))
    {
      getMinecraft().thePlayer.rotationPitch = this.targetPitch;
      getMinecraft().thePlayer.rotationYaw = this.targetYaw;
    }
  }
  
  public void silentAim(EntityLivingBase targetEntity)
  {
    int bowCurrentCharge = getMinecraft().thePlayer.getItemInUseDuration();
    float bowVelocity = bowCurrentCharge / 20.0F;
    bowVelocity = (bowVelocity * bowVelocity + bowVelocity * 2.0F) / 3.0F;
    if (bowVelocity < 0.1D) {
      return;
    }
    if (bowVelocity > 1.0F) {
      bowVelocity = 1.0F;
    }
    double xDistance = targetEntity.posX - getMinecraft().thePlayer.posX;
    double zDistance = targetEntity.posZ - getMinecraft().thePlayer.posZ;
    double eyeDistance = targetEntity.posY + targetEntity.getEyeHeight() - (getMinecraft().thePlayer.posY + getMinecraft().thePlayer.getEyeHeight());
    double trajectoryXZ = Math.sqrt(xDistance * xDistance + zDistance * zDistance);
    double eyeTrajectoryXZ = Math.sqrt(trajectoryXZ * trajectoryXZ + eyeDistance * eyeDistance);
    float trajectoryTheta90 = (float)(Math.atan2(zDistance, xDistance) * 180.0D / 3.141592653589793D) - 90.0F;
    
    float bowTrajectory = -getTrajectoryAngleSolutionLow((float)trajectoryXZ, (float)eyeDistance, bowVelocity);
    
    getMinecraft().thePlayer.rotationPitch = bowTrajectory;
    getMinecraft().thePlayer.rotationYaw = trajectoryTheta90;
  }
  
  public EntityLivingBase getCursorEntity()
  {
    EntityLivingBase poorEntity = null;
    double distanceToEntity = 1000.0D;
    for (Iterator entityIterator = getMinecraft().theWorld.loadedEntityList.iterator(); entityIterator.hasNext();)
    {
      Object currentObject = entityIterator.next();
      if ((currentObject instanceof Entity))
      {
        Entity targetEntity = (Entity)currentObject;
        if (((targetEntity instanceof EntityLivingBase)) && (targetEntity != getMinecraft().thePlayer)) {
          if ((targetEntity.getDistanceToEntity(getMinecraft().thePlayer) <= 140.0F) && (getMinecraft().thePlayer.canEntityBeSeen(targetEntity)) && (((EntityLivingBase)targetEntity).deathTime <= 0))
          {
            if (poorEntity == null) {
              poorEntity = (EntityLivingBase)targetEntity;
            }
            double xDistance = targetEntity.posX - getMinecraft().thePlayer.posX;
            double zDistance = targetEntity.posZ - getMinecraft().thePlayer.posZ;
            double eyeDistance = getMinecraft().thePlayer.posY + getMinecraft().thePlayer.getEyeHeight() - (targetEntity.posY + targetEntity.getEyeHeight());
            double trajectoryXZ = Math.sqrt(xDistance * xDistance + zDistance * zDistance);
            float trajectoryTheta90 = (float)(Math.atan2(zDistance, xDistance) * 180.0D / 3.141592653589793D) - 90.0F;
            float trajectoryTheta = (float)(Math.atan2(eyeDistance, trajectoryXZ) * 180.0D / 3.141592653589793D);
            
            double xAngleDistance = getDistanceBetweenAngles(trajectoryTheta90, getMinecraft().thePlayer.rotationYaw % 360.0F);
            double yAngleDistance = getDistanceBetweenAngles(trajectoryTheta, getMinecraft().thePlayer.rotationPitch % 360.0F);
            
            double entityDistance = Math.sqrt(xAngleDistance * xAngleDistance + yAngleDistance * yAngleDistance);
            if (entityDistance < distanceToEntity)
            {
              poorEntity = (EntityLivingBase)targetEntity;
              distanceToEntity = entityDistance;
            }
          }
        }
      }
    }
    return poorEntity;
  }
  
  private float getTrajectoryAngleSolutionLow(float angleX, float angleY, float bowVelocity)
  {
    float velocityIncrement = 0.006F;
    float squareRootBow = bowVelocity * bowVelocity * bowVelocity * bowVelocity - velocityIncrement * (velocityIncrement * (angleX * angleX) + 2.0F * angleY * (bowVelocity * bowVelocity));
    return (float)Math.toDegrees(Math.atan((bowVelocity * bowVelocity - Math.sqrt(squareRootBow)) / (velocityIncrement * angleX)));
  }
  
  private float getDistanceBetweenAngles(float angle1, float angle2)
  {
    float angleToEntity = Math.abs(angle1 - angle2) % 360.0F;
    if (angleToEntity > 180.0F) {
      angleToEntity = 360.0F - angleToEntity;
    }
    return angleToEntity;
  }
}
