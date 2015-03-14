package me.thelethalhamster.radium.altmanager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.IImageBuffer;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;

import org.lwjgl.opengl.GL11;

public class AltAccountInfo extends GuiScreen{
	private AltAccountSwitch parentScreen;
	private String user, pass;
	private boolean hasSkin, hasEmail;
    private String email;
    public ThreadDownloadImageData imagedata;
    
	public AltAccountInfo(AltAccountSwitch var1, int var2){
		this.parentScreen = var1;
		String[] info = parentScreen.getAccountList().get(var2).split(":");
		this.user = info[0];
		this.pass = info[1];
		checkSkin();
        if (hasSkin)
        	this.imagedata = getImage(location(this.getUsername()), this.getUsername());
	}
	
	public void drawScreen(int i, int j, float f) {
		super.drawScreen(i, j, f);
		drawDefaultBackground();
		drawCenteredString(fontRendererObj, "Change Username", width / 2, (height / 4 - 60) + 20, 0xffffff);
		drawString(fontRendererObj, "Username", width / 2 - 100, 63, 0xa0a0a0);
		drawString(fontRendererObj, "Password", width / 2 - 100, 104, 0xa0a0a0);
		ScaledResolution var3 = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0.0D, var3.getScaledWidth() / 4.0D, var3.getScaledHeight() / 4.0D, 0.0D, 1000.0D, 3000.0D);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
		GL11.glTranslatef(5.0F, 7, -2000.0F);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDepthMask(false);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		ResourceLocation loc = null;
    	
    	if (this.hasSkin())
    		loc = this.location(this.getUsername());
    	else
    		loc = new ResourceLocation("textures/entity/steve.png");

    	Minecraft.getMinecraft().getTextureManager().bindTexture(loc);
        zLevel = -90.0F;
        drawTexturedModalRect(8, 6, 8, 8, 8, 8);
        drawTexturedModalRect(8, 6 + 8, 20, 20, 8, 12);
        drawTexturedModalRect(8 - 4, 6 + 8, 44, 20, 4, 12);
        drawTexturedModalRect(8 + 8, 6 + 8, 44, 20, 4, 12);
        drawTexturedModalRect(8, 6 + 20, 4, 20, 4, 12);
        drawTexturedModalRect(8 + 4, 6 + 20, 4, 20, 4, 12);
        drawTexturedModalRect(8, 6, 40, 8, 8, 8);
        GL11.glOrtho(0.0D, var3.getScaledWidth(), var3.getScaledHeight(), 0.0D, 1000.0D, 3000.0D);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glPopMatrix();
	}
	
    public void checkSkin() {
    	(new Thread(new checkThread(this))).run();
    }
    public ThreadDownloadImageData getImage(ResourceLocation par0ResourceLocation, String par1Str)
    {
        return getImageFromUser(par0ResourceLocation, getUserSkin(par1Str), (ResourceLocation)null, (IImageBuffer)null);
    }
    public String getUserSkin(String par0Str)
    {
        return String.format("http://skins.minecraft.net/MinecraftSkins/%s.png", new Object[] {StringUtils.stripControlCodes(par0Str)});
    }
    private ThreadDownloadImageData getImageFromUser(ResourceLocation par0ResourceLocation, String par1Str, ResourceLocation par2ResourceLocation, IImageBuffer par3IImageBuffer)
    {
        TextureManager var4 = Minecraft.getMinecraft().getTextureManager();
        Object var5 = var4.getTexture(par0ResourceLocation);

        if (var5 == null)
        {
            var5 = new ThreadDownloadImageData(par1Str, par2ResourceLocation, par3IImageBuffer);
            var4.loadTexture(par0ResourceLocation, (ITextureObject)var5);
        }

        return (ThreadDownloadImageData)var5;
    }
    public ResourceLocation location(String par0Str)
    {
        return new ResourceLocation("skins/" + StringUtils.stripControlCodes(par0Str));
    }
    
    public String getUsername() {
    	if (this.user.equals("")) {
    		return this.getEmail();
    	}
    	return this.user;
    }
    public String getPassword() {
    	return this.pass;
    }
    public String getEmail() {
    	return this.email;
    }
    public boolean hasSkin() {
    	return this.hasSkin;
    }
    public boolean hasEmail() {
    	return this.hasEmail;
    }
    public void setUsername(String us) {
    	this.user = us;
    }
    public void setPassword(String pa) {
    	this.pass = pa;
    }
    public void setEmail(String em) {
    	this.email = em;
    	if (this.email != null && !this.email.equals(""))
    		this.hasEmail = true;
    }
    public void setSkin(boolean skin) {
    	this.hasSkin = skin;
    }
}

class checkThread implements Runnable {
	private AltAccountInfo alt;
	
	public checkThread(AltAccountInfo alt) {
		this.alt = alt;
	}
	
	@Override
	public void run() {
    	try {
    		String username = new YggdrasilPayload().loginPassword(alt.getUsername(), alt.getPassword()).getUsername();
    		if(alt.getUsername().contains("@")){
    			alt.setEmail(alt.getUsername());
    			System.out.println(alt.getEmail());
    			alt.setUsername(username);
    			System.out.println(alt.getUsername());
    		}
    		URL url = new URL(alt.getUserSkin(username));
    		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
    		
    		br.close();
    		this.alt.setSkin(true);
    	} catch (Exception x) {
    		this.alt.setSkin(false);
    	}
    	
    	if (this.alt.hasSkin()) {
    		this.alt.imagedata = this.alt.getImage(alt.location(alt.getUsername()), this.alt.getUsername());
    	}
    	
	}
}

