package org.darkstorm.minecraft.gui.theme.radium;
import me.thelethalhamster.radium.ttf.TTFRenderer;
import net.minecraft.client.gui.FontRenderer;

import org.darkstorm.minecraft.gui.theme.AbstractTheme;

public class RadiumTheme extends AbstractTheme {
	private final FontRenderer fontRenderer;

	public RadiumTheme() {
		fontRenderer = new TTFRenderer("Segoe UI Bold", 15);

		installUI(new RadiumFrameUI(this));
		installUI(new RadiumPanelUI(this));
		installUI(new RadiumLabelUI(this));
		installUI(new RadiumButtonUI(this));
		installUI(new RadiumCheckButtonUI(this));
		installUI(new RadiumComboBoxUI(this));
		installUI(new RadiumSliderUI(this));
		installUI(new RadiumProgressBarUI(this));
	}

	public FontRenderer getFontRenderer() {
		return fontRenderer;
	}
}
