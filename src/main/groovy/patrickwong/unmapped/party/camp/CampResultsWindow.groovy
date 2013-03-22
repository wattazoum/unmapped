package patrickwong.unmapped.party.camp

import patrickwong.unmapped.model.GameState
import patrickwong.unmapped.model.PlayerCharacter

import com.googlecode.lanterna.gui.Window
import com.googlecode.lanterna.gui.component.Button
import com.googlecode.lanterna.gui.component.Label

public class CampResultsWindow extends Window {
	public CampResultsWindow(CampState state) {
		super("Camp Results")
		List<PlayerCharacter> party = GameState.getInstance().getParty()
		
		for (PlayerCharacter pc : party) {
			if (pc.isDead()) {
				addComponent(new Label(pc.name + " is dead"))
			} else {
				pc.removeShock(100)
				CampDecision decision = state.getDecision(pc.name)
				addComponent(new Label(decision.onAction()))
			}
		}
		if (state.onEndDay != null) {
			addComponent(new Label(state.onEndDay()))
		}
		if ((state.canContinueStaying == null) || (state.canContinueStaying())) {
			addComponent(new Button("OK", new CampAction(state)))
		} else {
			addComponent(new Label())
			addComponent(new Label(state.onKickedOut()))
			addComponent(new Button("OK", state.endCampAction))
		}
	}
}
