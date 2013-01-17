package patrickwong.unmapped.party;

import patrickwong.unmapped.InterfaceState;
import patrickwong.unmapped.UnmappedMain;
import patrickwong.unmapped.model.PlayerCharacter;

import com.googlecode.lanterna.gui.Action;

public class CharacterMenuAction implements Action {
	
	private String charName;
	private Action returnAction;
	
	public CharacterMenuAction(String charName, Action returnAction) {
		this.charName = charName;
		this.returnAction = returnAction;
	}
	public CharacterMenuAction(PlayerCharacter pc, Action returnAction) {
		this.charName = pc.getName();
		this.returnAction = returnAction;
	}
	
	@Override
	public void doAction() {
		InterfaceState.nextWindow = new CharacterMenuWindow(charName, returnAction);
		UnmappedMain.closeCurrent();
	}

}
