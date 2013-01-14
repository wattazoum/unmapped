package patrickwong.unmapped.town;

import patrickwong.unmapped.UnmappedMain;

import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.GUIScreen;

public class MarketStreetAction implements Action {

	@Override
	public void doAction() {
		UnmappedMain.getGUI().getActiveWindow().close();
		UnmappedMain.getGUI().showWindow(new MarketStreetWindow(), GUIScreen.Position.CENTER);
	}

}
