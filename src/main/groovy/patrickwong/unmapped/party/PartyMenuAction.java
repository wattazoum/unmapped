package patrickwong.unmapped.party;

import patrickwong.unmapped.UnmappedMain;

import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.GUIScreen;

public class PartyMenuAction implements Action {

	@Override
	public void doAction() {
		UnmappedMain.getGUI().showWindow(new PartyMenuWindow(), GUIScreen.Position.CENTER);
	}

}
