package patrickwong.unmapped.party.camp

import patrickwong.unmapped.InterfaceState
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.model.PlayerCharacter

import com.googlecode.lanterna.gui.Action

public class CharacterCampDecisionAction implements Action {
	private CampState state
	private PlayerCharacter pc
	public CharacterCampDecisionAction(CampState state, PlayerCharacter pc) {
		this.state = state
		this.pc = pc
	}
	@Override
	public void doAction() {
		InterfaceState.nextWindow = new CharacterCampDecisionWindow(state, pc)
		UnmappedMain.closeCurrent()
	}

}
