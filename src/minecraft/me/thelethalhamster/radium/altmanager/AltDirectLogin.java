package me.thelethalhamster.radium.altmanager;

import me.thelethalhamster.radium.override.GuiRadiumButton;
import me.thelethalhamster.radium.ttf.TTFRenderer;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.Session;

import org.lwjgl.input.Keyboard;


public class AltDirectLogin extends GuiScreen {

	private GuiScreen parentScreen;
	private GuiTextField usernameTextField;
	private AltPassField passwordTextField;
	private String error;
	FontRenderer fontRendererObj = new TTFRenderer("Arial Bold", 17);


	public AltDirectLogin(GuiScreen guiscreen) {
		parentScreen = guiscreen;
	}

	public void updateScreen() {
		usernameTextField.updateCursorCounter();
		passwordTextField.updateCursorCounter();
	}

	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
	}

	protected void actionPerformed(GuiButton guibutton) {
		if (!guibutton.enabled) {
			return;
		}
		if (guibutton.id == 1) {
			mc.displayGuiScreen(parentScreen);
		}
		else if (guibutton.id == 0) {
			if (passwordTextField.getText().length() > 0) {
				String s = usernameTextField.getText();
				String s1 = passwordTextField.getText();
				
				try {
					YggdrasilAuthenticator Auth = new YggdrasilAuthenticator (s, s1);
					mc.displayGuiScreen(parentScreen);
				}catch (Exception e) {
					e.printStackTrace();
				}
			}else {
				mc.session = new Session(usernameTextField.getText(), "", "");
			}

			mc.displayGuiScreen(parentScreen);
		}
	}

	protected void keyTyped(char c, int i) {
		usernameTextField.textboxKeyTyped(c, i);
		passwordTextField.textboxKeyTyped(c, i);
		if (c == '\t') {
			if (usernameTextField.field_146213_o) {
				usernameTextField.field_146213_o = false;
				passwordTextField.field_146213_o = true;
			}
			else {
				usernameTextField.field_146213_o = true;
				passwordTextField.field_146213_o = false;
			}
		}
		if (c == '\r') {
			actionPerformed((GuiButton) buttonList.get(0));
		}
	}

	protected void mouseClicked(int i, int j, int k) {
		super.mouseClicked(i, j, k);
		usernameTextField.mouseClicked(i, j, k);
		passwordTextField.mouseClicked(i, j, k);
	}

	public void initGui() {
		Keyboard.enableRepeatEvents(true);
		buttonList.clear();
		buttonList.add(new GuiRadiumButton(0, width / 2 - 100, height / 4 + 96 + 12, "Login"));
		buttonList.add(new GuiRadiumButton(1, width / 2 - 100, height / 4 + 120 + 12, "Cancel"));
		usernameTextField = new GuiTextField(fontRendererObj, width / 2 - 100, 76, 200, 20);
		passwordTextField = new AltPassField(fontRendererObj, width / 2 - 100, 116, 200, 20);
		usernameTextField.func_146203_f(512);
	}

	public void drawScreen(int i, int j, float f) {
		drawDefaultBackground();
		drawCenteredString(fontRendererObj, "Change Username", width / 2, (height / 4 - 60) + 20, 0xffffff);
		drawString(fontRendererObj, "Username", width / 2 - 100, 63, 0xa0a0a0);
		drawString(fontRendererObj, "Password", width / 2 - 100, 104, 0xa0a0a0);
		usernameTextField.drawTextBox();
		passwordTextField.drawTextBox();
		if (error != null) {
			drawCenteredString(fontRendererObj, (new StringBuilder("\247c Login Failed:")).append(error).toString(), width / 2, height / 4 + 72 + 12, 0xffffff);
		}
		super.drawScreen(i, j, f);
	}
}