package me.thelethalhamster.radium.altmanager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import me.thelethalhamster.radium.override.GuiRadiumButton;
import me.thelethalhamster.radium.ttf.TTFRenderer;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.util.Session;

public class AltAccountSwitch extends GuiScreen {

	private GuiScreen parentScreen;
	private AltAccountSlot accountSlotContainer;
	private GuiButton buttonEdit;
	private GuiButton buttonDelete;
	private GuiButton buttonSelect;
	private List<String> accountList = new ArrayList<String>();
	private int selectedAccount = -1;
	private File altsFile = new File("Radium", "accounts.txt");
	FontRenderer fontRendererObj = new TTFRenderer("Arial Bold", 17);

	public AltAccountSwitch(GuiScreen derp) {
		parentScreen = derp;
	}

	public void initGui() {
		loadAccountList();
		accountSlotContainer = new AltAccountSlot(this);
		buttonList.clear();
		buttonList.add(new GuiRadiumButton(0, width / 2 + 4 + 76, height - 28, 70, 20, "Back"));
		buttonList.add(buttonSelect = new GuiRadiumButton(1, width / 2 - 154, height - 52, 70, 20, "Login"));
		buttonList.add(buttonDelete = new GuiRadiumButton(2, width / 2 - 74, height - 28, 70, 20, "Delete"));
		buttonList.add(new GuiRadiumButton(3, width / 2 + 4 + 76, height - 52, 70, 20, "Add"));
		buttonList.add(new GuiRadiumButton(4, width / 2 - 74, height - 52, 70, 20, "Direct Login"));
		buttonList.add(buttonEdit = new GuiRadiumButton(5, width / 2 - 154, height - 28, 70, 20, "Edit"));
		buttonList.add(new GuiRadiumButton(6, width / 2 + 4, height - 28, 70, 20, "Refresh"));
		buttonList.add(new GuiRadiumButton(7, width / 2 + 4, height - 52, 70, 20, "Import Alts"));
		boolean flag = selectedAccount >= 0 && selectedAccount < accountSlotContainer.getSize();
		buttonSelect.enabled = flag;
		buttonEdit.enabled = flag;
		buttonDelete.enabled = flag;
	}

	public void drawScreen(int i, int j, float f) {
		drawDefaultBackground();
		accountSlotContainer.func_148128_a(i, j, f);
		drawCenteredString(fontRendererObj, "Account Manager", width / 2, 20, 0xffffff);
		drawCenteredString(fontRendererObj, "Current Account: " + mc.session.getUsername(), width / 2, 4, 0xffffff);
		super.drawScreen(i, j, f);
	}

	public void importAlts() {
		final JFileChooser chooser = new JFileChooser();
		chooser.setVisible(true);
		chooser.setSize(500, 400);
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setFileFilter(new FileNameExtensionFilter("Text File",
				new String[] { "txt" }));
		final JFrame frame = new JFrame("Select a file");
		chooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((e.getActionCommand().equals("ApproveSelection"))
						&& (chooser.getSelectedFile() != null)) {
					
					try {
						Scanner scanner;
						scanner = new Scanner(new FileReader(chooser.getSelectedFile()));
						scanner.useDelimiter("\n");
						while (scanner.hasNext())
							accountList.add(scanner.next().trim());
						scanner.close();
						
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
					try {
						StringBuilder data = new StringBuilder();
						for (String s : accountList){
							data.append(s + "\n");
						}
						BufferedWriter writer = new BufferedWriter(new FileWriter(altsFile));
						writer.write(data.toString());
						writer.close();
					} catch (Exception e1) {
					}
					frame.setVisible(false);
					frame.dispose();
				}

				if (e.getActionCommand().equals("CancelSelection")) {
					frame.setVisible(false);
					frame.dispose();
				}
			}
		});
		frame.add(chooser);
		frame.setVisible(true);
		frame.setSize(500, 400);
	}

	@Override
	protected void actionPerformed(GuiButton guibutton) {
		if (guibutton.enabled) {
			if (guibutton.id == 0) {
				mc.displayGuiScreen(parentScreen);
			}
			if (guibutton.id == 1) {
				login(selectedAccount);
			}
			if (guibutton.id == 2) {
				 String name = accountList.get(selectedAccount).split(":")[0];
				 String s1 = "Are you sure you want to remove this account?";
				 String s2 = "Account: " + name;
				 GuiYesNo fack = new GuiYesNo(this, s1, s2, "Delete",
				 "Cancel", selectedAccount);
				 mc.displayGuiScreen(fack);
			}
			if (guibutton.id == 3) {
				mc.displayGuiScreen(new AltAccountEdit(this, -1));
			}
			if (guibutton.id == 4) {
				mc.displayGuiScreen(new AltDirectLogin(this));
			}
			if (guibutton.id == 5) {
				mc.displayGuiScreen(new AltAccountEdit(this,
						selectedAccount));
			}
			if (guibutton.id == 6) {
				mc.displayGuiScreen(this);
			}
			if (guibutton.id == 7) {
				this.importAlts();
				//mc.displayGuiScreen(new AltAccountInfo(this,
					//	selectedAccount));
			}
		}
	}

	public void login(int i) {
		String[] info = accountList.get(i).split(":");
		boolean cracked = info.length == 1;
		if (info.length == 1) {
			mc.session = new Session(info[0], "", "");
		} else {
			try {
				YggdrasilAuthenticator Auth = new YggdrasilAuthenticator (info[0], info[1]);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		mc.displayGuiScreen(parentScreen);
	}

	public void confirmClicked(boolean flag, int i) {
		if (flag && i >= 0) {
			accountList.remove(i);
			saveAccountList();
		}
		mc.displayGuiScreen(this);
	}

	private void loadAccountList() {
		try {
			if (!altsFile.exists())
				altsFile.createNewFile();
			accountList.clear();
			Scanner scanner = new Scanner(new FileReader(altsFile));
			scanner.useDelimiter("\n");
			while (scanner.hasNext())
				accountList.add(scanner.next().trim());
			scanner.close();
		} catch (Exception e) {
		}
	}

	private void saveAccountList() {
		try {
			StringBuilder data = new StringBuilder();
			for (String s : accountList){
				data.append(s + "\n");
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(altsFile));
			writer.write(data.toString());
			writer.close();
		} catch (Exception e) {
		}
	}

	public void editAccount(String account, int i) {
		if (i < 0)
			accountList.add(account);
		else
			accountList.set(i, account);
		saveAccountList();
	}

	public List<String> getAccountList() {
		return accountList;
	}

	public int setSelectedAccount(int var0) {
		return selectedAccount = var0;
	}

	public int getSelectedAccount() {
		return selectedAccount;
	}

	public GuiButton getButtonSelect() {
		return buttonSelect;
	}

	public GuiButton getButtonEdit() {
		return buttonEdit;
	}
	
	public GuiButton getButtonDelete() {
		return buttonDelete;
	}
}