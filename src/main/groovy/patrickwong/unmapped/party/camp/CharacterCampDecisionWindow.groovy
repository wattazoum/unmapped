package patrickwong.unmapped.party.camp

import patrickwong.unmapped.model.GameState
import patrickwong.unmapped.model.PlayerCharacter

import com.googlecode.lanterna.gui.Action
import com.googlecode.lanterna.gui.Window
import com.googlecode.lanterna.gui.component.Button

public class CharacterCampDecisionWindow extends Window {
	private CampState state = null
	private CampDecision decision = null
	private PlayerCharacter pc = null
	
	public CharacterCampDecisionWindow(CampState campState, PlayerCharacter playerCharacter) {
		super(playerCharacter.name + " decides to...")
		state = campState
		pc = playerCharacter
		
		addComponent(new Button("...rest", restAction))
		if (state.hasAmenity("town")) {
			addComponent(new Button("...do manual labor", laborAction))
		}
		if (state.hasAmenity("tavern")) {
			addComponent(new Button("...waste time in the tavern", wasteTimeAtTavernAction))
		}
		if (state.hasAmenity("temple")) {
			addComponent(new Button("...meditate in the temple", meditateAction))
		}
		if (state.hasAmenity("quiet")) {
			addComponent(new Button("...pray", prayAction))
		}
		addComponent(new Button("...continue", new CampAction(state)))
	}
	
	private def restAction = {
		state.setDecision(new CampDecision(charName: pc.name))
		CampAction.campAction(state)
	} as Action
	
	private def laborAction = {
		decision = new CampDecision(charName: pc.name,
			futureTense: " will do manual labor for some money",
			onAction: {
				int earnings = 1
				earnings += pc.rollStat("STR")
				earnings += pc.rollStat("END")
				earnings = (earnings / 5)
				GameState.getInstance().partyMoney += earnings
				return (pc.name + " works as a manual laborer to earn $earnings")
			}
		)
		state.setDecision(decision)
		CampAction.campAction(state)
	} as Action

	private def wasteTimeAtTavernAction = {
		decision = new CampDecision(charName: pc.name,
			summary: " will hang out at the tavern",
			onAction: {
				return (pc.name + " wastes time at the tavern")
			}
		)
		state.setDecision(decision)
		CampAction.campAction(state)
	} as Action
	
	private def meditateAction = {
		decision = new CampDecision(charName: pc.name,
			summary: " will meditate",
			onAction: {
				int numberOfThoughts = pc.rollStat("SIX")
				return (pc.name + " meditates and forms $numberOfThoughts complete thoughts")
			}
		)
		state.setDecision(decision)
		CampAction.campAction(state)
	} as Action
	
	// TODO - implement praying, and the stuff that praying could possibly affect
	private def prayAction = {
		decision = new CampDecision(charName: pc.name,
			summary: " will pray",
			onAction: {
				return (pc.name + " prays (not yet implemented)")
			}
		)
		state.setDecision(decision)
		CampAction.campAction(state)
	} as Action
}
