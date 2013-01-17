package patrickwong.unmapped.party.camp

import patrickwong.unmapped.InterfaceState
import patrickwong.unmapped.UnmappedMain

import com.googlecode.lanterna.gui.Action

public class CampResultsAction implements Action {
	private CampState state
	public CampResultsAction(CampState state) {
		this.state = state
	}
	@Override
	public void doAction() {
		InterfaceState.nextWindow = new CampResultsWindow(state)
		UnmappedMain.closeCurrent()
	}

}
