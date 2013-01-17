package patrickwong.unmapped.combat

import patrickwong.unmapped.InterfaceState
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.model.GameState
import patrickwong.unmapped.model.PlayerCharacter

public class CombatProcessUtil {
	public static Closure genToNextDecisionClosure() {
		def theClosure = {
			CombatDecision cd, CombatState state ->
			UnmappedMain.log.debug("toNextDecision begins execution")
			state.getDecisions().add(cd)
			state.currentCharacter += 1
			if (state.currentCharacter >= GameState.getInstance().getParty().size()) {
				InterfaceState.nextWindow = new CombatRoundResultsWindow(state)
			} else {
				PlayerCharacter nextCharacter = GameState.getInstance().getCharacter(state.currentCharacter)
				InterfaceState.nextWindow = new CombatDecisionWindow(state, nextCharacter)
			}
			UnmappedMain.closeCurrent()
			UnmappedMain.log.debug("end of toNextDecision closure")
		}
		return theClosure
	}
}
