package patrickwong.unmapped.party;

import patrickwong.unmapped.InterfaceState;
import patrickwong.unmapped.UnmappedMain;

import com.googlecode.lanterna.gui.Action;

public class QuestListAction implements Action {
	private Action returnAction;
	
	public QuestListAction(Action returnAction) {
		this.returnAction = returnAction;
	}
	
	@Override
	public void doAction() {
		InterfaceState.nextWindow = new QuestListWindow(returnAction);
		UnmappedMain.closeCurrent();
	}

}
