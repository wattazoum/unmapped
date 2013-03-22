package patrickwong.unmapped.mainmenu;

import patrickwong.unmapped.InterfaceState;
import patrickwong.unmapped.UnmappedMain;

import com.googlecode.lanterna.gui.Action;

public class MainMenuAction implements Action {
	@Override
	public void doAction() {
		InterfaceState.nextWindow = new MainMenuWindow();
		UnmappedMain.closeCurrent();
	}
}
