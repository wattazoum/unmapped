package patrickwong.unmapped.demon

import patrickwong.unmapped.DiceRoller
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.combat.Enemy

public class MinionAkuma extends Enemy {
	
	public MinionAkuma() {
		super()
		challengeLevel = DiceRoller.nextInt(120)
		possibleActions = ["punch", "tetsubo", "bite", "claw"]
	}
	
	@Override
	public String getAttackVerb() {
		if (currentAction.equalsIgnoreCase("punch")) {
			return " punches at ";
		} else if (currentAction.equalsIgnoreCase("tetsubo")) {
			return " swings at "
		} else if (currentAction.equalsIgnoreCase("bite")) {
			return " bites at "
		} else if (currentAction.equalsIgnoreCase("claw")) {
			return " claws at "
		}
		UnmappedMain.log.error("enemy $name does not have an attack verb")
		UnmappedMain.log.error("currentAction: $currentAction")
		return " BLAH "
	}
	
	@Override
	public String getAttackDamageType() {
		if (currentAction.equalsIgnoreCase("punch")) {
			return "impact";
		} else if (currentAction.equalsIgnoreCase("tetsubo")) {
			return "impact"
		} else if (currentAction.equalsIgnoreCase("bite")) {
			return "piercing"
		} else if (currentAction.equalsIgnoreCase("claw")) {
			return "cutting"
		}
		UnmappedMain.log.error("enemy $name does not have a damage type")
		return "impact"
	}
}
