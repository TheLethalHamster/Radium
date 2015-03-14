package me.thelethalhamster.radium.module.modules;

import me.thelethalhamster.radium.module.Category;
import me.thelethalhamster.radium.module.Module;
import me.thelethalhamster.radium.wrapper.RadiumWrapper;
import net.minecraft.potion.PotionEffect;

public class FullBright extends Module{

	public float fakeGamma = RadiumWrapper.getInstance().getGameSettings().gammaSetting;
	private boolean isFading = false;
	private static final float FADE_INTERVAL = 0.5F;
	
	public FullBright() {
		super("FullBrightness", 0, Category.WORLD, true);
	}
	
	@Override
	public void onEnable(){
		RadiumWrapper.getInstance().getMinecraft().thePlayer.removePotionEffect(16);
	}
	
	@Override
	public void onMotionUpdate(){
		RadiumWrapper.getInstance().getMinecraft().thePlayer.addPotionEffect( new PotionEffect( 16, 999999999, 2  ) );   
	}

	@Override
	public void onDisable(){
		RadiumWrapper.getInstance().getMinecraft().thePlayer.removePotionEffect(16); 
	}
}
