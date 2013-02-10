package patrickwong.unmapped.party.holybook;

import patrickwong.unmapped.InterfaceState;
import patrickwong.unmapped.UnmappedMain;

import com.googlecode.lanterna.gui.Action;

public class HolyBookListAction implements Action {
	private Action returnAction;
	
	public HolyBookListAction(Action returnAction) {
		this.returnAction = returnAction;
	}
	
	@Override
	public void doAction() {
		InterfaceState.nextWindow = new HolyBookListWindow(returnAction);
		UnmappedMain.closeCurrent();
	}

}
