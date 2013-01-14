package patrickwong.unmapped.combat

public class PartyAdvanceDecision extends CombatDecision {
	public PartyAdvanceDecision() {
		super()
		setCharName("The party ")
		setClosure {
			return "The party advances forward"
		}
	}
}
