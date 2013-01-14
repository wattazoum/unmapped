package patrickwong.unmapped.mainmenu;

import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.Label;

public class LoadGameWindow extends Window {
	public LoadGameWindow() {
		super("Load Game");
		addComponent(new Label("Not implemented"));
		addComponent(new Button("Cancel", new MainMenuAction()));
	}
}
