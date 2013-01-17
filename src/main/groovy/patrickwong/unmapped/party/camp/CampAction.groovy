package patrickwong.unmapped.party.camp

import patrickwong.unmapped.InterfaceState
import patrickwong.unmapped.UnmappedMain

import com.googlecode.lanterna.gui.Action

public class CampAction implements Action {
	private CampState campState
	
	public CampAction(CampState campState) {
		this.campState = campState
	}
	
	@Override
	public void doAction() {
		campAction(campState)
	}
	
	public static def campAction = {
		CampState state ->
		InterfaceState.nextWindow = new CampWindow(state)
		UnmappedMain.closeCurrent()
	}
}
