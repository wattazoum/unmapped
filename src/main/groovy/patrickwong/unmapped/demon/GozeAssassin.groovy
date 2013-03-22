package patrickwong.unmapped.demon

import patrickwong.unmapped.DiceRoller
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.combat.Enemy

public class GozeAssassin extends Enemy {
	public GozeAssassin() {
		super()
		challengeLevel = DiceRoller.binaryPool(160)
		possibleActions = ["spear_thrust", "spear_swing", "spear_reverse"]
		possibleRangedActions = ["needle_toss", "razor_toss"]
	}
	
	@Override
	public String getAttackVerb() {
		if (currentAction.equalsIgnoreCase("spear_thrust")) {
			return " thrusts a spear at ";
		} else if (currentAction.equalsIgnoreCase("spear_swing")) {
			return " swings a spear at "
		} else if (currentAction.equalsIgnoreCase("spear_reverse")) {
			return " whacks the butt of her spear at "
		} else if (currentAction.equalsIgnoreCase("needle_toss")) {
			return " throws a needle at "
		} else if (currentAction.equalsIgnoreCase("razor_toss")) {
			return " throws a razor at "
		}
		UnmappedMain.log.error("enemy $name does not have an attack verb")
		UnmappedMain.log.error("currentAction: $currentAction")
		return " BLAH "
	}
	
	@Override
	public String getAttackDamageType() {
		if (currentAction.equalsIgnoreCase("spear_thrust")) {
			return "piercing";
		} else if (currentAction.equalsIgnoreCase("spear_swing")) {
			return "cutting"
		} else if (currentAction.equalsIgnoreCase("spear_reverse")) {
			return "impact"
		} else if (currentAction.equalsIgnoreCase("needle_toss")) {
			return "piercing"
		} else if (currentAction.equalsIgnoreCase("razor_toss")) {
			return "cutting"
		}
		UnmappedMain.log.error("enemy $name does not have a damage type")
		return "impact"
	}
}
