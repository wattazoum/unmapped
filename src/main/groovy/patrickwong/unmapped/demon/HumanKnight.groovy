package patrickwong.unmapped.demon

import patrickwong.unmapped.DiceRoller
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.combat.Enemy

public class HumanKnight extends Enemy {
	public HumanKnight() {
		super()
		challengeLevel = DiceRoller.binaryPool(130)
		possibleActions = ["zweihander_thrust", "zweihander_swing", "great_morningstar"]
		possibleRangedActions = ["crossbow_shot", "javelin_toss"]
	}
	
	@Override
	public String getAttackVerb() {
		if (currentAction.equalsIgnoreCase("zweihander_thrust")) {
			return " thrusts a zweihander at ";
		} else if (currentAction.equalsIgnoreCase("zweihander_swing")) {
			return " swings a zweihander at "
		} else if (currentAction.equalsIgnoreCase("great_morningstar")) {
			return " swings a great morningstar at "
		} else if (currentAction.equalsIgnoreCase("crossbow_shot")) {
			return " shoots a light crossbow at "
		} else if (currentAction.equalsIgnoreCase("javelin_toss")) {
			return " throws a javelin at "
		}
		UnmappedMain.log.error("enemy $name does not have an attack verb")
		UnmappedMain.log.error("currentAction: $currentAction")
		return " BLAH "
	}
	
	@Override
	public String getAttackDamageType() {
		if (currentAction.equalsIgnoreCase("zweihander_thrust")) {
			return "piercing";
		} else if (currentAction.equalsIgnoreCase("zweihander_swing")) {
			return "cutting"
		} else if (currentAction.equalsIgnoreCase("great_morningstar")) {
			return "impact"
		} else if (currentAction.equalsIgnoreCase("crossbow_shot")) {
			return "piercing"
		} else if (currentAction.equalsIgnoreCase("javelin_toss")) {
			return "piercing"
		}
		UnmappedMain.log.error("enemy $name does not have a damage type")
		return "impact"
	}
}
