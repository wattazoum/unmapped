package patrickwong.unmapped.party.camp

import patrickwong.unmapped.model.GameState;
import patrickwong.unmapped.model.PlayerCharacter

public class CampDecision {
	String charName
	String futureTense = " will rest for the day"
	Closure onAction = {
		PlayerCharacter pc = GameState.getInstance().getCharacter(charName)
		pc.removeShock(100)
		pc.removePain(10)
		pc.removeWounds(1)
		if ((pc.getPain() <= 0) && (pc.getWounds() <= 0)) {
			return ("$charName rests and feels as good as new")
		} else {
			return ("$charName rests and gets better")
		}
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof PlayerCharacter) {
			return charName.equalsIgnoreCase(((PlayerCharacter)o).name)
		} else if (o instanceof CampDecision) {
			return charName.equalsIgnoreCase(((CampDecision)o).charName)
		} else {
			return charName.equalsIgnoreCase(o.toString())
		}
	}
}
