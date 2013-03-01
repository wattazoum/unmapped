package patrickwong.unmapped.demon

import patrickwong.unmapped.DiceRoller
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.combat.Enemy

public class HumanSergeant extends Enemy {
	public HumanSergeant() {
		super()
		challengeLevel = DiceRoller.binaryPool(100)
		possibleActions = ["halberd_thrust", "halberd_swing", "morningstar", "sword"]
		possibleRangedActions = ["crossbow_shot", "javelin_toss"]
	}
	
	@Override
	public String getAttackVerb() {
		if (currentAction.equalsIgnoreCase("halberd_thrust")) {
			return " thrusts a halberd at ";
		} else if (currentAction.equalsIgnoreCase("halberd_swing")) {
			return " swings a halberd at "
		} else if (currentAction.equalsIgnoreCase("morningstar")) {
			return " swings a morningstar at "
		} else if (currentAction.equalsIgnoreCase("sword")) {
			return " swings a sword at "
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
		if (currentAction.equalsIgnoreCase("halberd_thrust")) {
			return "piercing";
		} else if (currentAction.equalsIgnoreCase("halberd_swing")) {
			return "cutting"
		} else if (currentAction.equalsIgnoreCase("morningstar")) {
			return "impact"
		} else if (currentAction.equalsIgnoreCase("sword")) {
			return "cutting"
		} else if (currentAction.equalsIgnoreCase("crossbow_shot")) {
			return "piercing"
		} else if (currentAction.equalsIgnoreCase("javelin_toss")) {
			return "piercing"
		}
		UnmappedMain.log.error("enemy $name does not have a damage type")
		return "impact"
	}
}
