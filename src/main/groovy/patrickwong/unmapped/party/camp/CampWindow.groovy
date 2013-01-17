package patrickwong.unmapped.party.camp

import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.model.GameState
import patrickwong.unmapped.model.PlayerCharacter

import com.googlecode.lanterna.gui.Action
import com.googlecode.lanterna.gui.Window
import com.googlecode.lanterna.gui.component.Button
import com.googlecode.lanterna.gui.component.Label

public class CampWindow extends Window {
	private CampState state
	private List<PlayerCharacter> party
	public CampWindow(CampState campState) {
		super(campState.campTitle)
		state = campState
		party = GameState.getInstance().getParty()
		
		addComponent(new Label(state.campDesc))
		if (state.specialNote != null) {
			addComponent(new Label(state.specialNote()))
		}
		addComponent(new Button("Do the chosen actions", new CampResultsAction(state)))
		for (PlayerCharacter pc : party) {
			if (pc.isDead()) {
				addComponent(new Button(pc.name + " is dead"))
			} else {
				CampDecision decision = state.getDecision(pc.name)
				if (decision == null) {
					UnmappedMain.log.debug("setting default camp decision for " + pc.name)
					decision = new CampDecision(charName: pc.name)
					state.setDecision(decision)
				} else {
					UnmappedMain.log.debug("presenting camp decision for " + pc.name)
					UnmappedMain.log.debug(decision.getFutureTense())
				}
				addComponent(new Button(pc.name + decision.getFutureTense(), new CharacterCampDecisionAction(state, pc)))
			}
		}
		addComponent(new Button("Break Camp", campState.endCampAction))
	}
}
