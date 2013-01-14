package patrickwong.unmapped.combat

import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.model.GameState
import patrickwong.unmapped.model.PlayerCharacter

public class CombatProcessUtil {
	
	public static Closure toNextDecision = { CombatDecision cd, CombatState state ->
		state.getDecisions().add(cd)
		state.currentCharacter += 1
		UnmappedMain.closeCurrent()
		if (state.currentCharacter >= GameState.getInstance().getParty().size()) {
			UnmappedMain.showWindow(new CombatRoundActionWindow(state))
		} else {
			PlayerCharacter nextCharacter = GameState.getInstance().getCharacter(state.currentCharacter)
			UnmappedMain.showWindow(new CombatDecisionWindow(state, nextCharacter))
		}
	}
	
}
