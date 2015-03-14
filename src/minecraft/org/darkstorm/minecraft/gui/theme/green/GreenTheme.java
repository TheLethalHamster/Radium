package org.darkstorm.minecraft.gui.theme.green;
import me.thelethalhamster.radium.ttf.TTFRenderer;
import net.minecraft.client.gui.FontRenderer;

import org.darkstorm.minecraft.gui.theme.AbstractTheme;

public class GreenTheme extends AbstractTheme {
	private final FontRenderer fontRenderer;

	public GreenTheme() {
		fontRenderer = new TTFRenderer("Arial Bold", 15);

		installUI(new GreenFrameUI(this));
		installUI(new GreenPanelUI(this));
		installUI(new GreenLabelUI(this));
		installUI(new GreenButtonUI(this));
		installUI(new GreenCheckButtonUI(this));
		installUI(new GreenComboBoxUI(this));
		installUI(new GreenSliderUI(this));
		installUI(new GreenProgressBarUI(this));
	}

	public FontRenderer getFontRenderer() {
		return fontRenderer;
	}
}
