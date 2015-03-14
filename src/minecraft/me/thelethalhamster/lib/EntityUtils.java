package me.thelethalhamster.lib;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

public class EntityUtils {
	
	private Minecraft mc = Minecraft.getMinecraft();
	
	public EntityPlayer getClosestPlayer() {
		double distance = 5000;
		EntityPlayer target = null;
		for (Object entity : mc.theWorld.playerEntities) {
			EntityPlayer e = (EntityPlayer) entity;
			if (!(e instanceof EntityPlayer) || e == mc.thePlayer) {
				continue;
			}

			if (getDistance(e.posX, e.posY, e.posZ) < distance) {
				distance = getDistance(e.posX, e.posY, e.posZ);
				target = e;
			}
		}
		return target;
	}

	public double getDistance(double X, double Y, double Z) {
		double posX = X - mc.thePlayer.posX;
		double posY = Y - mc.thePlayer.posY;
		double posZ = Z - mc.thePlayer.posZ;
		return Math.abs(Math.sqrt(posX * posX + posY * posY + posZ * posZ));
	}
	
	public void faceEntity(Entity entity){
		double x = entity.posX - mc.thePlayer.posX;
		double z = entity.posZ - mc.thePlayer.posZ;
		double y = entity.posY + (entity.getEyeHeight()/1.4D) - mc.thePlayer.posY + (mc.thePlayer.getEyeHeight()/1.4D);
		double helper = MathHelper.sqrt_double(x * x + z * z);

		float newYaw = (float)((Math.toDegrees(-Math.atan(x / z))));
		float newPitch = (float)-Math.toDegrees(Math.atan(y / helper));

		if(z < 0 && x < 0) { newYaw = (float)(90D + Math.toDegrees(Math.atan(z / x))); }
		else if(z < 0 && x > 0) { newYaw = (float)(-90D + Math.toDegrees(Math.atan(z / x))); }

		mc.thePlayer.rotationYaw = newYaw; 
		mc.thePlayer.rotationPitch = newPitch;
		mc.thePlayer.rotationYawHead = newPitch;
	}
	
	public EntityLivingBase getClosestEntity(float range) {
        EntityLivingBase returnVal = null;
        float distance = range;
        for(Entity ent :(List<Entity>) mc.theWorld.loadedEntityList) {
                if(!canAttack(ent, range)) continue;

                float curDistance = mc.thePlayer.getDistanceToEntity(ent);
                if(curDistance < distance) {
                        distance = curDistance;
                        returnVal = (EntityLivingBase) ent;
                }
        }

        return returnVal;
	}

	public boolean canAttack(Entity e, float distance) {
        	return e != mc.thePlayer &&
        			e instanceof EntityLivingBase &&
        			!e.isInvisible() &&
        			mc.thePlayer.getDistanceToEntity(e) <= distance &&
        			e.isEntityAlive() &&
        			(canBeSeen(e) || mc.thePlayer.canEntityBeSeen(e));
	}

	public void attackEntity(Entity entity) {
        mc.thePlayer.swingItem();
        mc.playerController.attackEntity(mc.thePlayer, entity);
	}

	public float[] faceEntity(Entity entity, boolean flag) {
		double x = entity.posX - mc.thePlayer.posX;
        double z = entity.posZ - mc.thePlayer.posZ;
        double y;
        if(entity instanceof EntityEnderman) {
                y = entity.posY - mc.thePlayer.posY;
        } else {
                y = entity.posY + (entity.getEyeHeight() / 1.4D) - mc.thePlayer.posY + (mc.thePlayer.getEyeHeight() / 1.4D);
        }
        double helper = MathHelper.sqrt_double(x * x + z * z);

        float newYaw =(float)((Math.toDegrees(-Math.atan(x / z))));
        float newPitch =(float)-Math.toDegrees(Math.atan(y / helper));

        if(z < 0 && x < 0) {
                newYaw =(float)(90D + Math.toDegrees(Math.atan(z / x)));
        } else if(z < 0 && x > 0) {
                newYaw =(float)(-90D + Math.toDegrees(Math.atan(z / x)));
        }

        if(flag) {
                mc.thePlayer.rotationYaw = newYaw;
                mc.thePlayer.rotationPitch = newPitch;
        }

        return new float[] { newYaw, newPitch };
	}

	public float[] faceBlock(float blockX, float blockY, float blockZ, boolean flag) {
		double x = blockX + 0.5 - mc.thePlayer.posX;
        double z = blockZ + 0.5 - mc.thePlayer.posZ;
        double y = blockY + 0.5 - mc.thePlayer.posY;
        double helper = MathHelper.sqrt_double(x * x + z * z);

        float newYaw = (float)((Math.toDegrees(-Math.atan(x / z))));
        float newPitch = (float)-Math.toDegrees(Math.atan(y / helper));

        if(z < 0 && x < 0) {
                newYaw = (float)(90D + Math.toDegrees(Math.atan(z / x)));
        } else if(z < 0 && x > 0) {
                newYaw = (float)(-90D + Math.toDegrees(Math.atan(z / x)));
        }

        if(flag) {
                mc.thePlayer.rotationYaw += MathHelper.wrapAngleTo180_float(newYaw - mc.thePlayer.rotationYaw);
                mc.thePlayer.rotationPitch += MathHelper.wrapAngleTo180_float(newPitch - mc.thePlayer.rotationPitch);
        }
       
        return new float[] { newYaw, newPitch };
	}

	public boolean canBeSeen(Entity par1) {
        return rayTrace(mc.theWorld.getWorldVec3Pool().getVecFromPool(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ),
                        mc.theWorld.getWorldVec3Pool().getVecFromPool(par1.posX, par1.posY, par1.posZ)) == null;
	}

	public MovingObjectPosition rayTrace(Vec3 par1, Vec3 par2) {
		return mc.theWorld.rayTraceBlocks(par1, par2);
	}

	public double[] getRenderPosition(Entity ent) {
		return new double[] {
				ent.lastTickPosX + (ent.posX - ent.lastTickPosX) * (double) mc.timer.renderPartialTicks - RenderManager.renderPosX,
                ent.lastTickPosY + (ent.posY - ent.lastTickPosY) * (double) mc.timer.renderPartialTicks - RenderManager.renderPosY,
                ent.lastTickPosZ + (ent.posZ - ent.lastTickPosZ) * (double) mc.timer.renderPartialTicks - RenderManager.renderPosZ
        };
	}

	public float getYawDistanceFromEntity(Entity e) {
        double xDistance = e.posX - mc.thePlayer.posX;
        double zDistance = e.posZ - mc.thePlayer.posZ;
       
        return (float)((Math.atan2(zDistance, xDistance) * 180D) / Math.PI) - 90F;
	}

	public float getPitchDistanceFromEntity(Entity e) {
        double xDistance = e.posX - mc.thePlayer.posX;
        double yDistance = mc.thePlayer.posY + (double) mc.thePlayer.getEyeHeight() - (e.posY + (double)e.getEyeHeight());
        double zDistance = e.posZ - mc.thePlayer.posZ;
        double sqrt = (double)MathHelper.sqrt_double(xDistance * xDistance + zDistance * zDistance);
       
        return (float)-(-(Math.atan2(yDistance, sqrt) * 180.0D / Math.PI));
	}

	public boolean isDead(Entity e) {
        return !isAlive(e);
	}

	public boolean isAlive(Entity e) {
		return e != null && !e.isDead && (e instanceof EntityLivingBase ? (((EntityLivingBase) e).getHealth() >= 1) : true);
	}
	
	public Entity getMouseOverEntity(double distance)
    {
		Entity pointedEntity = null;
        if (this.mc.renderViewEntity != null)
        {
            if (this.mc.theWorld != null)
            {
                double var2 = distance;
                MovingObjectPosition object = this.mc.renderViewEntity.rayTrace(var2, 1);
                double var4 = var2;
                Vec3 var6 = this.mc.renderViewEntity.getPosition(1);

                if (object != null)
                {
                    var4 = object.hitVec.distanceTo(var6);
                }

                Vec3 var7 = this.mc.renderViewEntity.getLook(1);
                Vec3 var8 = var6.addVector(var7.xCoord * var2, var7.yCoord * var2, var7.zCoord * var2);
                pointedEntity = null;
                float var10 = 1.0F;
                List var11 = this.mc.theWorld.getEntitiesWithinAABBExcludingEntity(this.mc.renderViewEntity, this.mc.renderViewEntity.boundingBox.addCoord(var7.xCoord * var2, var7.yCoord * var2, var7.zCoord * var2).expand((double)var10, (double)var10, (double)var10));
                double var12 = var4;

                for (int var14 = 0; var14 < var11.size(); ++var14)
                {
                    Entity var15 = (Entity)var11.get(var14);

                    if (var15.canBeCollidedWith())
                    {
                        float var16 = var15.getCollisionBorderSize();
                        AxisAlignedBB var17 = var15.boundingBox.expand((double)var16, (double)var16, (double)var16);
                        MovingObjectPosition var18 = var17.calculateIntercept(var6, var8);

                        if (var17.isVecInside(var6))
                        {
                            if (0.0D < var12 || var12 == 0.0D)
                            {
                                pointedEntity = var15;
                                var12 = 0.0D;
                            }
                        }
                        else if (var18 != null)
                        {
                            double var19 = var6.distanceTo(var18.hitVec);

                            if (var19 < var12 || var12 == 0.0D)
                            {
                                if (var15 == this.mc.renderViewEntity.ridingEntity)
                                {
                                    if (var12 == 0.0D)
                                    {
                                        pointedEntity = var15;
                                    }
                                }
                                else
                                {
                                    pointedEntity = var15;
                                    var12 = var19;
                                }
                            }
                        }
                    }
                }

                if (pointedEntity != null && (var12 < var4))
                {
                    if (pointedEntity instanceof EntityLivingBase || pointedEntity instanceof EntityItemFrame)
                    {
                        return pointedEntity;
                    }
                }
            }
        }
        return pointedEntity;
    }
	
	public void faceBlock(double X, double Y, double Z)
    {
		double x = X - mc.thePlayer.posX;
		double z = Z - mc.thePlayer.posZ;
		double y = Y + 1 - mc.thePlayer.posY + (mc.thePlayer.getEyeHeight()/1.4D);
		double helper = MathHelper.sqrt_double(x * x + z * z);

		float newYaw = (float)((Math.toDegrees(-Math.atan(x / z))));
		float newPitch = (float)-Math.toDegrees(Math.atan(y / helper));

		if(z < 0 && x < 0) { newYaw = (float)(90D + Math.toDegrees(Math.atan(z / x))); }
		else if(z < 0 && x > 0) { newYaw = (float)(-90D + Math.toDegrees(Math.atan(z / x))); }

		mc.thePlayer.rotationYaw = newYaw; 
		mc.thePlayer.rotationPitch = newPitch;
		mc.thePlayer.rotationYawHead = newPitch;
    }
	
	public MovingObjectPosition canBlockBeSeen(double x, double y, double z){
		return mc.theWorld.rayTraceBlocks(mc.thePlayer.worldObj.getWorldVec3Pool().getVecFromPool(mc.thePlayer.posX, mc.thePlayer.posY + (double)mc.thePlayer.getEyeHeight(), mc.thePlayer.posZ), mc.thePlayer.worldObj.getWorldVec3Pool().getVecFromPool (x, y, z));
	}
}
