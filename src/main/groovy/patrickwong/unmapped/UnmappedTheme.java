package patrickwong.unmapped;

import com.googlecode.lanterna.gui.Theme;
import com.googlecode.lanterna.terminal.Terminal.Color;

public class UnmappedTheme extends Theme {
	UnmappedTheme() {
		super();
		setDefinition(Category.DIALOG_AREA, new Definition(Color.WHITE, Color.BLACK));
		setDefinition(Category.SCREEN_BACKGROUND, new Definition(Color.BLACK, Color.BLACK));
		setDefinition(Category.SHADOW, new Definition(Color.BLACK, Color.BLACK));
		setDefinition(Category.BORDER, new Definition(Color.BLUE, Color.BLACK));
		setDefinition(Category.RAISED_BORDER, new Definition(Color.BLUE, Color.BLACK));
		setDefinition(Category.BUTTON_ACTIVE, new Definition(Color.WHITE, Color.BLUE));
		setDefinition(Category.BUTTON_INACTIVE, new Definition(Color.WHITE, Color.BLACK));
		setDefinition(Category.BUTTON_LABEL_ACTIVE, new Definition(Color.WHITE, Color.BLUE));
		setDefinition(Category.BUTTON_LABEL_INACTIVE, new Definition(Color.WHITE, Color.BLACK));
		setDefinition(Category.LIST_ITEM, new Definition(Color.WHITE, Color.BLACK));
		setDefinition(Category.LIST_ITEM_SELECTED, new Definition(Color.BLUE, Color.BLACK));
		setDefinition(Category.CHECKBOX, new Definition(Color.WHITE, Color.BLACK));
		setDefinition(Category.CHECKBOX_SELECTED, new Definition(Color.WHITE, Color.BLUE));
		setDefinition(Category.TEXTBOX, new Definition(Color.WHITE, Color.BLACK));
		setDefinition(Category.TEXTBOX_FOCUSED, new Definition(Color.WHITE, Color.BLUE));
	}
}
