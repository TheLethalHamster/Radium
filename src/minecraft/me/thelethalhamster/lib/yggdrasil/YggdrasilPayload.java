package me.thelethalhamster.lib.yggdrasil;

import java.net.Proxy;

import net.minecraft.util.Session;

import com.mojang.authlib.Agent;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;


public class YggdrasilPayload {
	
	public Session loginPassword(String username, String password)
    {
        if(username == null || username.length() <= 0 || password == null || password.length() <= 0)
            return null;

        YggdrasilAuthenticationService a = new YggdrasilAuthenticationService(Proxy.NO_PROXY, "");
        YggdrasilUserAuthentication b = (YggdrasilUserAuthentication)a.createUserAuthentication(Agent.MINECRAFT);
        b.setUsername(username);
        b.setPassword(password);
        try
        {
            b.logIn();
            return new Session(b.getSelectedProfile().getName(), b.getSelectedProfile().getId(), b.getAuthenticatedToken());
        } catch (AuthenticationException e)
        {
            e.printStackTrace();
        }
        return null;
    } 
	
	public Session loginCrack(String username)
    {
		return new Session(username, "", "");
    }
	
}
