package patrickwong.unmapped.mainmenu;

import patrickwong.unmapped.UnmappedMain;

import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.GUIScreen;

public class MainMenuAction implements Action {
	@Override
	public void doAction() {
		UnmappedMain.getGUI().getActiveWindow().close();
		UnmappedMain.getGUI().showWindow(new MainMenuWindow(), GUIScreen.Position.CENTER);
	}
}
