package patrickwong.unmapped.demon

import patrickwong.unmapped.DiceRoller
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.combat.Enemy

public class EvilVillager extends Enemy {
	public EvilVillager() {
		super()
		challengeLevel = DiceRoller.binaryPool(80)
		possibleActions = ["punch", "knife", "axe", "sickle", "pitchfork", "torch"]
		possibleRangedActions = ["sickle_toss", "axe_toss"]
	}
	
	@Override
	public String getAttackVerb() {
		if (currentAction.equalsIgnoreCase("punch")) {
			return " punches at ";
		} else if (currentAction.equalsIgnoreCase("knife")) {
			return " slices a knife at "
		} else if (currentAction.equalsIgnoreCase("axe")) {
			return " swings an axe at "
		} else if (currentAction.equalsIgnoreCase("sickle")) {
			return " swings a sickle at "
		} else if (currentAction.equalsIgnoreCase("pitchfork")) {
			return " stabs a spear at "
		} else if (currentAction.equalsIgnoreCase("torch")) {
			return " swings a torch at "
		} else if (currentAction.equalsIgnoreCase("sickle_toss")) {
			return " throws a sickle at "
		} else if (currentAction.equalsIgnoreCase("axe_toss")) {
			return " throws an axe at "
		}
		UnmappedMain.log.error("enemy $name does not have an attack verb")
		UnmappedMain.log.error("currentAction: $currentAction")
		return " BLAH "
	}
	
	@Override
	public String getAttackDamageType() {
		if (currentAction.equalsIgnoreCase("punch")) {
			return "impact";
		} else if (currentAction.equalsIgnoreCase("knife")) {
			return "cutting"
		} else if (currentAction.equalsIgnoreCase("axe")) {
			return "cutting"
		} else if (currentAction.equalsIgnoreCase("sickle")) {
			return "piercing"
		} else if (currentAction.equalsIgnoreCase("pitchfork")) {
			return "piercing"
		} else if (currentAction.equalsIgnoreCase("torch")) {
			return "elemental"
		} else if (currentAction.equalsIgnoreCase("sickle_toss")) {
			return "piercing"
		} else if (currentAction.equalsIgnoreCase("axe_toss")) {
			return "cutting"
		}
		UnmappedMain.log.error("enemy $name does not have a damage type")
		return "impact"
	}
}
