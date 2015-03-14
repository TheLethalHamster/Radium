package org.darkstorm.minecraft.gui.theme.green;

import static org.lwjgl.opengl.GL11.*;

import java.awt.*;

import me.thelethalhamster.radium.util.GuiUtil;

import org.lwjgl.input.Mouse;
import org.darkstorm.minecraft.gui.component.Button;
import org.darkstorm.minecraft.gui.component.Component;
import org.darkstorm.minecraft.gui.theme.AbstractComponentUI;
import org.darkstorm.minecraft.gui.util.RenderUtil;

public class GreenButtonUI extends AbstractComponentUI<Button> {
	private final GreenTheme theme;

	GreenButtonUI(GreenTheme theme) {
		super(Button.class);
		this.theme = theme;

		foreground = Color.WHITE;
		background = new Color(128, 128, 128, 128 + 128 / 2);
	}

	@Override
	protected void renderComponent(Button button) {
		translateComponent(button, false);
		Rectangle area = button.getArea();
		if(!button.isModuleEnabled()){
			GuiUtil.getInstance().drawBorderedRect(0, 0, area.width, area.height, 0x90000000, 0x90000000);
		}else{
			GuiUtil.getInstance().drawBorderedRect(0, 0, area.width, area.height, 0x90006600, 0x90006600);
		}
		Point mouse = RenderUtil.calculateMouseLocation();
		Component parent = button.getParent();
		while(parent != null) {
			mouse.x -= parent.getX();
			mouse.y -= parent.getY();
			parent = parent.getParent();
		}
		if(area.contains(mouse)) {
			GuiUtil.getInstance().drawBorderedRect(0, 0, area.width, area.height, 0x90197519, 0x90197519);
		}
		glEnable(GL_TEXTURE_2D);

		String text = button.getText();
		theme.getFontRenderer().drawString(
				text,
				area.width / 2 - theme.getFontRenderer().getStringWidth(text)
						/ 2,
				area.height / 2 - theme.getFontRenderer().FONT_HEIGHT / 2,
				RenderUtil.toRGBA(button.getForegroundColor()));

		glEnable(GL_CULL_FACE);
		glDisable(GL_BLEND);
		translateComponent(button, true);
	}

	@Override
	protected Dimension getDefaultComponentSize(Button component) {
		return new Dimension(theme.getFontRenderer().getStringWidth(
				component.getText()) + 4,
				theme.getFontRenderer().FONT_HEIGHT + 4);
	}

	@Override
	protected Rectangle[] getInteractableComponentRegions(Button component) {
		return new Rectangle[] { new Rectangle(0, 0, component.getWidth(),
				component.getHeight()) };
	}

	@Override
	protected void handleComponentInteraction(Button component, Point location,
			int button) {
		if(location.x <= component.getWidth()
				&& location.y <= component.getHeight() && button == 0)
			component.press();
	}
}