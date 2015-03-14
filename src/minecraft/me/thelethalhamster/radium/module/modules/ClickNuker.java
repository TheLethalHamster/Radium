package me.thelethalhamster.radium.module.modules;

import me.thelethalhamster.radium.module.Category;
import me.thelethalhamster.radium.module.Module;
import net.minecraft.block.Block;
import net.minecraft.network.play.client.C07PacketPlayerDigging;

public class ClickNuker extends Module{

	public ClickNuker() {
		super("ClickNuker", 0, Category.WORLD, true);
	}
	
	@Override
	public void onClickBlock(int i, int j, int k, int l){
		for (int y = (int) 3; y >= (int) -3; y--)
		{
			for (int z = (int) -3; z <= 3; z++)
			{
				for (int x = (int) -3; x <= 3; x++)
				{
					int xPos = (int)Math.floor(getPlayer().posX + x);
					int yPos = (int)Math.floor(getPlayer().posY + y);
					int zPos = (int)Math.floor(getPlayer().posZ + z);
					Block block = getWorld().getBlock(xPos, yPos, zPos);
					if (Block.getIdFromBlock(block) != 0)
					{
						getPlayer().sendQueue.addToSendQueue(new C07PacketPlayerDigging(0, xPos, yPos, zPos, 1));
						if (!getPlayer().capabilities.isCreativeMode)
							getPlayer().sendQueue.addToSendQueue(new C07PacketPlayerDigging(2, xPos, yPos, zPos, 1));
					}
				}
			}
		}
	}
}
