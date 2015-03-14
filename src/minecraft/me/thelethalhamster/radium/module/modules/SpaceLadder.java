package me.thelethalhamster.radium.module.modules;

import me.thelethalhamster.radium.module.Category;
import me.thelethalhamster.radium.module.Module;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLadder;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.MathHelper;

public class SpaceLadder extends Module{

	public SpaceLadder() {
		super("SpaceLadder", 0, Category.MOVEMENT, true);
	}
	
	@Override
	public void preMotionUpdate(){
		 boolean forward = getGameSettings().keyBindForward.pressed;
         boolean back = getGameSettings().keyBindBack.pressed;
         boolean left = getGameSettings().keyBindLeft.pressed;
         boolean right = getGameSettings().keyBindRight.pressed;
         if((forward || back || left || right) && !getPlayer().isOnLadder()) {
            int var1 = MathHelper.floor_double(getPlayer().posX);
            int var2 = MathHelper.floor_double(getPlayer().boundingBox.minY);
            int var3 = MathHelper.floor_double(getPlayer().posZ);
            Block var4 = getWorld().getBlock(var1, var2, var3);
            if(!(var4 instanceof BlockLadder)) {
               var1 = MathHelper.floor_double(getPlayer().posX);
               var2 = MathHelper.floor_double(getPlayer().boundingBox.minY + 1.0D);
               var3 = MathHelper.floor_double(getPlayer().posZ);
               Block var5 = getWorld().getBlock(var1, var2, var3);
               if(var5 instanceof BlockLadder) {
                  getPlayer().motionY = 0.2D;
               }
            }
         }
	}

}
