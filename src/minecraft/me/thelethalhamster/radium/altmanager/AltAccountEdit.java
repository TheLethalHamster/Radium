package me.thelethalhamster.radium.altmanager;

import me.thelethalhamster.radium.override.GuiRadiumButton;
import me.thelethalhamster.radium.ttf.TTFRenderer;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;

import org.lwjgl.input.Keyboard;

public class AltAccountEdit extends GuiScreen {
    private AltAccountSwitch parentScreen;
    private GuiTextField usernameField;
    private AltPassField passwordField;
    private GuiButton buttonConfirm;
    private int account;
    FontRenderer fontRendererObj = new TTFRenderer("Arial Bold", 17);

    public AltAccountEdit(AltAccountSwitch var1, int var2) {
        parentScreen = var1;
        account = var2;
    }

    public void updateScreen() {
        passwordField.updateCursorCounter();
        usernameField.updateCursorCounter();
    }

    public void initGui() {
        Keyboard.enableRepeatEvents(true);
        buttonList.clear();
        buttonList.add(buttonConfirm = new GuiRadiumButton(0, width / 2 - 100, height / 4 + 108, 200, 20,(account == -1) ? "Add" :  "Done"));
        buttonList.add(new GuiRadiumButton(1, width / 2 - 100, height / 4 + 132, 200, 20, "Cancel"));
        String user = "";
        String pass = "";
        if (account >= 0) {
            String[] info = parentScreen.getAccountList().get(account).split(":");
            user = info[0];
            if (info.length > 1)
                pass = info[1];
        }
        usernameField = new GuiTextField(fontRendererObj, width / 2 - 100, 76, 200, 20);
        passwordField = new AltPassField(fontRendererObj, width / 2 - 100, 116, 200, 20);
        usernameField.setText(user);
        passwordField.setText(pass);
        usernameField.func_146203_f(512);
        passwordField.func_146203_f(32);
        usernameField.field_146213_o = true;
    }

    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
    }

    @Override
    protected void actionPerformed(GuiButton var1) {
        if (var1.enabled) {
            if (var1.id == 0) {
                String s = usernameField.getText();
                if (passwordField.getText().length() > 0)
                    s += ":" + passwordField.getText();
                parentScreen.editAccount(s, account);//checking in edit becuase add uses this class
                mc.displayGuiScreen(parentScreen);
            }
            if (var1.id == 1) {
                mc.displayGuiScreen(parentScreen);
            }
        }
    }

    protected void keyTyped(char var1, int var2) {
        passwordField.textboxKeyTyped(var1, var2);
        usernameField.textboxKeyTyped(var1, var2);
        if (var1 == 9)
            if (passwordField.field_146213_o) {
                passwordField.field_146213_o = false;
                usernameField.field_146213_o = true;
            } else {
                passwordField.field_146213_o = true;
                usernameField.field_146213_o = false;
            }
        if (var1 == 13)
        	actionPerformed((GuiButton) buttonList.get(0));
    }

    protected void mouseClicked(int var1, int var2, int var3) {
        super.mouseClicked(var1, var2, var3);
        usernameField.mouseClicked(var1, var2, var3);
        passwordField.mouseClicked(var1, var2, var3);
    }

    public void drawScreen(int var1, int var2, float var3) {
    	boolean flag = usernameField.getText().length() >= 6;
        buttonConfirm.enabled = flag;
    	this.drawDefaultBackground();
        drawCenteredString(fontRendererObj, (account == -1)? "Add Account Info" : "Edit Account Info", width / 2, height / 4 - 40, 16777215);
        drawString(fontRendererObj, "Username", width / 2 - 100, 63, 10526880);
        drawString(fontRendererObj, "Password (optional)", width / 2 - 100, 104, 10526880);
        passwordField.drawTextBox();
        usernameField.drawTextBox();
        super.drawScreen(var1, var2, var3);
    }
}