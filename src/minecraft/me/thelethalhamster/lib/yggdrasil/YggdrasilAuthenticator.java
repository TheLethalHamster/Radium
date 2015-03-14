package me.thelethalhamster.lib.yggdrasil;

import me.thelethalhamster.lib.Wrapper;
import net.minecraft.util.Session;

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
			Wrapper.getWrapper().setSession(AuthResponse);
		}else
		{
			Session AuthResponseCrack = Payload.loginCrack(this.Username);
			Wrapper.getWrapper().setSession(AuthResponseCrack);
		}
	}
	
}
