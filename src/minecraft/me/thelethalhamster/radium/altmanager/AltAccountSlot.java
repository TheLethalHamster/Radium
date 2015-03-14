package me.thelethalhamster.radium.altmanager;

import me.thelethalhamster.radium.ttf.TTFRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.renderer.Tessellator;

public class AltAccountSlot extends GuiSlot {
    final AltAccountSwitch parentScreen;

    public AltAccountSlot(AltAccountSwitch PAS) {
        super(Minecraft.getMinecraft(), PAS.width, PAS.height, 32, PAS.height - 64, 

24);
        parentScreen = PAS;
    }

    protected int getSize() {
        return parentScreen.getAccountList().size();
    }

    protected void elementClicked(int var1, boolean var2, int var3, int var4) {
        parentScreen.setSelectedAccount(var1);
        boolean flag2 = parentScreen.getSelectedAccount() >= 0 && 

parentScreen.getSelectedAccount() < getSize();
        parentScreen.getButtonSelect().enabled = flag2;
        parentScreen.getButtonEdit().enabled = flag2;
        parentScreen.getButtonDelete().enabled = flag2;
        if (var2 && flag2) {
            parentScreen.login(var1);
        }
    }

    protected boolean isSelected(int var1) {
        return var1 == parentScreen.getSelectedAccount();
    }

    protected int getContentHeight() {
        return parentScreen.getAccountList().size() * 24;
    }

    protected void drawBackground() {
        parentScreen.drawDefaultBackground();
    }

    protected void drawSlot(int var1, int var2, int var3, int var4,
			Tessellator var5, int var6, int var7) {
        String[] account = parentScreen.getAccountList().get(var1).split(":");
        String name = account[0];
        //String password = account[1] == null? "Non-Premium" : account[1].replaceAll(".", "*");
        try{
        	FontRenderer f = new TTFRenderer("Arial Bold", 17);
            f.drawString(name, field_148155_a / 2 - (f.getStringWidth(name) / 2), var3 + 1, 0xffffff);
            f.drawString(account[1].replaceAll(".", "*"), field_148155_a / 2 - (f.getStringWidth(account[1].replaceAll(".", "*")) / 2), var3 + 12, 0xffffffff);
        }catch(Exception e){}
        //boolean cracked = account.length == 1;
        
    }
}