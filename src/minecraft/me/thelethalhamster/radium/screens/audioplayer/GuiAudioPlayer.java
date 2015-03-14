package me.thelethalhamster.radium.screens.audioplayer;

import jaco.mp3.player.MP3Player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import me.thelethalhamster.radium.screens.audioplayer.components.Slider;
import me.thelethalhamster.radium.util.RenderUtil;
import me.thelethalhamster.radium.values.Value;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

public class GuiAudioPlayer extends GuiScreen {

	public int x, y, dragX, dragY = 0;
	private boolean dragging = false;
	public boolean isExtended, isPinned = false;
	public int w,h = 0;
	private MP3Player player = new MP3Player();
	public Slider volume = new Slider("Volume", new Value("", 50, 0, 100, null), this);

	public void drawScreen(int par1, int par2, float par3) {
		w =  x + 200;
		if (dragging) {
			windowDragged(par1, par2);
		}
		Minecraft.getMinecraft().entityRenderer.updateCameraAndRenderBackwards(Minecraft.getMinecraft().timer.renderPartialTicks);
		drawAudioPlayer(par1, par2);
	}

	public void drawAudioPlayer(int x, int y) {
		if (!isExtended) {
			RenderUtil.drawBorderedRect(this.x, 2 + this.y, 150 + this.x, 14 + this.y, 0xff000000, 0x70070707);
			volume.drawSlider(this.x, this.y + 20, x, y);
		} else {

		}
	}

	public void windowDragged(int x, int y) {
		this.x = x - dragX;
		this.y = y - dragY;
	}

	protected void mouseClicked(int par1, int par2, int par3) {
		if(par1 >= x && par2 >= 2 + y && par1 <= 150 + x && par2 <= 14 + y)
		{
			dragging = true;
			dragX = par1 - x;
			dragY = par2 - y;
		}
		if (!isExtended) {
			
		}
	}

	protected void mouseMovedOrUp(int par1, int par2, int par3) {
		super.mouseMovedOrUp(par1, par2, par3);
		if (par3 == 0) {
			dragging = false;
		}
	}

	public void addSongsFromLocal(final MP3Player player) {
		final JFileChooser chooser = new JFileChooser();
		chooser.setVisible(true);
		chooser.setSize(500, 400);
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setFileFilter(new FileNameExtensionFilter("MP3 File",
				new String[] { "mp3" }));
		chooser.setMultiSelectionEnabled(true);
		final JFrame frame = new JFrame("Select a file");
		chooser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((e.getActionCommand().equals("ApproveSelection"))) {
					if ((chooser.getCurrentDirectory() != null)
							&& (chooser.getSelectedFile() == null || chooser
									.getSelectedFiles() == null)) {
						File dir = chooser.getCurrentDirectory();
						File[] files = dir.listFiles(new FilenameFilter() {
							@Override
							public boolean accept(File dir, String name) {
								return name.endsWith(".mp3");
							}
						});

						for (File mp3Files : files) {
							player.add(mp3Files);
						}
						frame.setVisible(false);
						frame.dispose();
					} else {
						for (File mp3Files : chooser.getSelectedFiles()) {
							player.add(mp3Files);
						}
						frame.setVisible(false);
						frame.dispose();
					}
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
}
