package patrickwong.unmapped.town;

import patrickwong.unmapped.InterfaceState;
import patrickwong.unmapped.UnmappedMain;

import com.googlecode.lanterna.gui.Action;

public class GuildStreetAction implements Action {

	@Override
	public void doAction() {
		InterfaceState.nextWindow = new GuildStreetWindow();
		UnmappedMain.closeCurrent();
	}

}
