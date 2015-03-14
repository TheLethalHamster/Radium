package me.thelethalhamster.radium.screens;

import jaco.mp3.player.MP3Player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import me.thelethalhamster.radium.util.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;

public class GuiMP3Player extends GuiScreen{
	public MP3Player player = new MP3Player();
	private int dragX = 0, dragY = 0;
	public int lastDragX;
	public int lastDragY;
	private boolean dragging = false;
	private boolean isExtended = false;
	public boolean isPinned = false;
	
	 public void drawScreen(int par1, int par2, float par3){
		 this.drawDefaultBackground();
		 if(dragging)
			{
				windowDragged(par1, par2);
			}
		 RenderUtil.drawBorderedRect(2, 2, 50, 14, 0xff000000, 0x70070707);
		 fontRendererObj.drawString("Browse",  (50 / 2) - (fontRendererObj.getStringWidth("Browse") / 2),4, 0xffffffff);
		 drawHub();
		 super.drawScreen(par1, par2, par3);
	 }
	 
	 public void drawHub(){
		 RenderUtil.drawBorderedRect(54 + dragX, 2 + dragY, 150 + dragX, 14 + dragY, 0xff000000, 0x70070707);
		 Minecraft.getMinecraft().fontRenderer.drawString("MP3 Hub", 56 + dragX, 4 + dragY, 0xffffffff);
		 RenderUtil.drawBorderedRect(152 + dragX, 2 + dragY, 164 + dragX, 14 + dragY, 0xff000000, isPinned ? 0x70323232 : 0x70070707);
		 RenderUtil.drawBorderedRect(166 + dragX, 2 + dragY, 178 + dragX, 14 + dragY, 0xff000000, isExtended ? 0x70323232 : 0x70070707);
		 if(isExtended){
			 RenderUtil.drawBorderedRect(54 + dragX, 16 + dragY, 178 + dragX, 66 + dragY, 0xff000000, 0x70070707);
			 Minecraft.getMinecraft().fontRenderer.drawString(!player.isStopped() && !player.isPaused()? "Playing" : player.isPaused() ? "Paused" : "Stopped", 56 + dragX, 18 + dragY, 0xffffffff);
			 
			 RenderUtil.drawBorderedRect(56 + dragX, 42 + dragY, 95 + dragX, 52 + dragY, 0xff000000, !player.isPaused() && !player.isStopped() ? 0x70323232 : 0x70070707);
			 Minecraft.getMinecraft().fontRenderer.drawString("Play", 67 + dragX, 43 + dragY, 0xffffffff);
			 RenderUtil.drawBorderedRect(56 + dragX, 54 + dragY, 95 + dragX, 64 + dragY, 0xff000000, player.isPaused() ? 0x70323232 : 0x70070707);
			 Minecraft.getMinecraft().fontRenderer.drawString("Pause", 64 + dragX, 55 + dragY, 0xffffffff);
			 RenderUtil.drawBorderedRect(97 + dragX, 54 + dragY, 136 + dragX, 64 + dragY, 0xff000000, player.isStopped() ? 0x70323232 : 0x70070707);
			 Minecraft.getMinecraft().fontRenderer.drawString("Stop", 106 + dragX, 55 + dragY, 0xffffffff);
			 RenderUtil.drawBorderedRect(138 + dragX, 54 + dragY, 176 + dragX, 64 + dragY, 0xff000000, 0x70070707);
			 Minecraft.getMinecraft().fontRenderer.drawString("Skip >", 142 + dragX, 55 + dragY, 0xffffffff);
			 RenderUtil.drawBorderedRect(138 + dragX, 42 + dragY, 176 + dragX, 52 + dragY, 0xff000000, 0x70070707);
			 Minecraft.getMinecraft().fontRenderer.drawString("Skip <", 142 + dragX, 43 + dragY, 0xffffffff);
			 RenderUtil.drawBorderedRect(97 + dragX, 42 + dragY, 136 + dragX, 52 + dragY, 0xff000000, player.isRepeat() ? 0x70323232 : 0x70070707);
			 Minecraft.getMinecraft().fontRenderer.drawString("Repeat", 102 + dragX, 43 + dragY, 0xffffffff);
			 RenderUtil.drawBorderedRect(56 + dragX, 30 + dragY, 176 + dragX, 40 + dragY, 0xff000000, player.isShuffle() ? 0x70323232 : 0x70070707);
			 Minecraft.getMinecraft().fontRenderer.drawString("Shuffle", 102 + dragX, 31 + dragY, 0xffffffff);
		 }
	 }
	 
	 public void windowDragged(int x, int y) {
			dragX = x - lastDragX;
			dragY = y - lastDragY;
		}
	 
	 protected void mouseClicked(int par1, int par2, int par3)
	 {
		 if(2 < par1 && 50 > par1 && 2 < par2 && 16 > par2){
			 importSong();
		 }
		 if(par1 >= 54 + dragX && par2 >= 2 + dragY && par1 <= 150 + dragX && par2 <= 14 + dragY)
			{
				dragging = true;
				lastDragX = par1 - dragX;
				lastDragY = par2 - dragY;
			}
		 
		 if(par1 >= 152 + dragX && par2 >= 2 + dragY && par1 <= 164 + dragX && par2 <= 14 + dragY)
			{
				isPinned = !isPinned;
			}
		 if(par1 >= 166 + dragX && par2 >= 2 + dragY && par1 <= 178 + dragX && par2 <= 14 + dragY)
			{
				isExtended = !isExtended;
			}
		 if(par1 >= 56 + dragX && par2 >= 42 + dragY && par1 <= 95 + dragX && par2 <= 52 + dragY){
			 if(isExtended){
				 if(player.isPaused() || player.isStopped()){
					 player.play();
				 }
			 }
		 }
		 if(par1 >= 56 + dragX && par2 >= 54 + dragY && par1 <= 95 + dragX && par2 <= 64 + dragY){
			 if(isExtended){
				 if(!player.isPaused() || !player.isStopped()){
					 player.pause();
				 }
			 }
		 }
		 if(par1 >= 97 + dragX && par2 >= 42 + dragY && par1 <= 136 + dragX && par2 <= 52 + dragY){
			 if(isExtended){
				 if(!player.isRepeat()){
					 player.setRepeat(true);
				 }else{
					 player.setRepeat(false);
				 }
				 
			 }
		 }
		 if(par1 >= 97 + dragX && par2 >= 54 + dragY && par1 <= 136 + dragX && par2 <= 64 + dragY){
			 if(isExtended){
				 if(!player.isStopped()){
					 player.stop();
				 }
			 }
		 }
		 if(par1 >= 138 + dragX && par2 >= 42 + dragY && par1 <= 176 + dragX && par2 <= 52 + dragY){
			 if(isExtended){
				 player.skipBackward();
				 
			 }
		 }
		 if(par1 >= 138 + dragX && par2 >= 54 + dragY && par1 <= 176 + dragX && par2 <= 64 + dragY){
			 if(isExtended){
				 player.skipForward();
				 
			 }
		 }
		 if(par1 >= 56 + dragX && par2 >= 30 + dragY && par1 <= 176 + dragX && par2 <= 40 + dragY){
			 if(isExtended){
				 if(!player.isShuffle()){
					 player.setShuffle(true);
				 }else{
					 player.setShuffle(false);
				 }
				 
			 }
		 }
	 }
	 public void importSong() {
		    final JFileChooser chooser = new JFileChooser();
		    chooser.setVisible(true);
		    chooser.setSize(500, 400);
		    chooser.setAcceptAllFileFilterUsed(false);
		    chooser.setFileFilter(new FileNameExtensionFilter("MP3 File", new String[] { "mp3" }));
		    chooser.setMultiSelectionEnabled(true);
		    final JFrame frame = new JFrame("Select a file");
		    chooser.addActionListener(new ActionListener()
		    {
		      public void actionPerformed(ActionEvent e) {
		        if ((e.getActionCommand().equals("ApproveSelection")) && 
		          (chooser.getSelectedFile() != null)) {     
		        	player.add(chooser.getSelectedFile());
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
	 
	 protected void mouseMovedOrUp(int par1, int par2, int par3)
	 {
		 if(par3 == 0){
			 dragging = false;
		 }
	 }
}
