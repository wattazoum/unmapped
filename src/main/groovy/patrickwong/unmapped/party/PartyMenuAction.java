package patrickwong.unmapped.party;

import patrickwong.unmapped.InterfaceState;
import patrickwong.unmapped.UnmappedMain;

import com.googlecode.lanterna.gui.Action;

public class PartyMenuAction implements Action {
	private Action returnAction;
	public PartyMenuAction(Action returnAction) {
		this.returnAction = returnAction;
	}
	
	@Override
	public void doAction() {
		InterfaceState.nextWindow = new PartyMenuWindow(returnAction);
		UnmappedMain.closeCurrent();
	}

}
