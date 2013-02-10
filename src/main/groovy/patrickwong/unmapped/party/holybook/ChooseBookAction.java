package patrickwong.unmapped.party.holybook;

import patrickwong.unmapped.InterfaceState;
import patrickwong.unmapped.UnmappedMain;
import patrickwong.unmapped.model.PlayerCharacter;

import com.googlecode.lanterna.gui.Action;

public class ChooseBookAction implements Action {
	private PlayerCharacter playerCharacter;
	private Action returnAction;
	
	public ChooseBookAction(PlayerCharacter playerCharacter, Action returnAction) {
		this.playerCharacter = playerCharacter;
		this.returnAction = returnAction;
	}
	
	@Override
	public void doAction() {
		InterfaceState.nextWindow = new ChooseBookWindow(playerCharacter, returnAction);
		UnmappedMain.closeCurrent();
	}

}
