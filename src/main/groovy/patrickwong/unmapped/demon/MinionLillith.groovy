package patrickwong.unmapped.demon

import java.util.List;

import patrickwong.unmapped.DiceRoller
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.combat.Enemy

public class MinionLillith extends Enemy {
	
	public MinionLillith() {
		super()
		challengeLevel = DiceRoller.nextInt(120)
		possibleActions = ["punch", "kiss", "spear", "sword"]
	}
	
	@Override
	public String getAttackVerb() {
		if (currentAction.equalsIgnoreCase("punch")) {
			return " punches at ";
		} else if (currentAction.equalsIgnoreCase("kiss")) {
			return " blows a kiss at "
		} else if (currentAction.equalsIgnoreCase("spear")) {
			return " thrusts at "
		} else if (currentAction.equalsIgnoreCase("sword")) {
			return " slashes at "
		}
		UnmappedMain.log.error("enemy $name does not have an attack verb")
		UnmappedMain.log.error("currentAction: $currentAction")
		return " BLAH "
	}
	
	@Override
	public String getAttackDamageType() {
		if (currentAction.equalsIgnoreCase("punch")) {
			return "impact";
		} else if (currentAction.equalsIgnoreCase("kiss")) {
			return "elemental"
		} else if (currentAction.equalsIgnoreCase("spear")) {
			return "piercing"
		} else if (currentAction.equalsIgnoreCase("sword")) {
			return "cutting"
		}
		UnmappedMain.log.error("enemy $name does not have a damage type")
		return "impact"
	}
}
