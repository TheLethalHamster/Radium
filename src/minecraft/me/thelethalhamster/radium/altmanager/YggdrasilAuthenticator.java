package me.thelethalhamster.radium.altmanager;

import me.thelethalhamster.lib.Wrapper;
import net.minecraft.util.Session;

/*
* Author: Opim10
* Version: 1
* Use: Login to Minecraft with Yggdrasil;
*/

public class YggdrasilAuthenticator {
	
	public YggdrasilPayload Payload = new YggdrasilPayload();
	public String Username;
	public String Password;
	
	public YggdrasilAuthenticator(String Username, String Password)
	{
		this.Username = Username;
		this.Password = Password;
		
		if(Password != "" && Password != null)
		{
			Session AuthResponse = Payload.loginPassword(this.Username, this.Password);
			Wrapper.getWrapper().getMinecraft().setSession(AuthResponse);
		}else
		{
			Session AuthResponseCrack = Payload.loginCrack(this.Username);
			Wrapper.getWrapper().getMinecraft().setSession(AuthResponseCrack);
		}
	}
	
}
