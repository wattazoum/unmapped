package patrickwong.unmapped.demon

import patrickwong.unmapped.DiceRoller
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.combat.Enemy

public class HumanGuard extends Enemy {
	public HumanGuard() {
		super()
		challengeLevel = DiceRoller.binaryPool(80)
		possibleActions = ["spear", "mace", "axe", "torch"]
		possibleRangedActions = ["dart_toss", "shortbow_shot"]
	}
	
	@Override
	public String getAttackVerb() {
		if (currentAction.equalsIgnoreCase("spear")) {
			return " thrusts a spear at ";
		} else if (currentAction.equalsIgnoreCase("mace")) {
			return " swings a mace at "
		} else if (currentAction.equalsIgnoreCase("axe")) {
			return " swings an axe at "
		} else if (currentAction.equalsIgnoreCase("torch")) {
			return " swings a torch at "
		} else if (currentAction.equalsIgnoreCase("dart_toss")) {
			return " throws a sickle at "
		} else if (currentAction.equalsIgnoreCase("shortbow_shot")) {
			return " throws an axe at "
		}
		UnmappedMain.log.error("enemy $name does not have an attack verb")
		UnmappedMain.log.error("currentAction: $currentAction")
		return " BLAH "
	}
	
	@Override
	public String getAttackDamageType() {
		if (currentAction.equalsIgnoreCase("spear")) {
			return "piercing";
		} else if (currentAction.equalsIgnoreCase("mace")) {
			return "impact"
		} else if (currentAction.equalsIgnoreCase("axe")) {
			return "cutting"
		} else if (currentAction.equalsIgnoreCase("torch")) {
			return "elemental"
		} else if (currentAction.equalsIgnoreCase("dart_toss")) {
			return "piercing"
		} else if (currentAction.equalsIgnoreCase("shortbow_shot")) {
			return "piercing"
		}
		UnmappedMain.log.error("enemy $name does not have a damage type")
		return "impact"
	}
}
