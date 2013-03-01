package patrickwong.unmapped.demon

import java.util.List;

import patrickwong.unmapped.DiceRoller
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.combat.Enemy

public class MinionBaphomet extends Enemy {
	
	public MinionBaphomet() {
		super()
		challengeLevel = DiceRoller.binaryPool(120)
		possibleActions = ["punch", "fireball", "bite", "claw"]
		possibleRangedActions = ["fire_toss", "snake_toss"]
	}
	
	@Override
	public String getAttackVerb() {
		if (currentAction.equalsIgnoreCase("punch")) {
			return " punches at ";
		} else if (currentAction.equalsIgnoreCase("fireball")) {
			return " shoots a fireball at "
		} else if (currentAction.equalsIgnoreCase("bite")) {
			return " bites at "
		} else if (currentAction.equalsIgnoreCase("claw")) {
			return " claws at "
		} else if (currentAction.equalsIgnoreCase("fire_toss")) {
			return " throws a fireball at "
		} else if (currentAction.equalsIgnoreCase("snake_toss")) {
			return " throws a snake at "
		}
		UnmappedMain.log.error("enemy $name does not have an attack verb")
		UnmappedMain.log.error("currentAction: $currentAction")
		return " BLAH "
	}
	
	@Override
	public String getAttackDamageType() {
		if (currentAction.equalsIgnoreCase("punch")) {
			return "impact";
		} else if (currentAction.equalsIgnoreCase("fireball")) {
			return "elemental"
		} else if (currentAction.equalsIgnoreCase("bite")) {
			return "piercing"
		} else if (currentAction.equalsIgnoreCase("claw")) {
			return "cutting"
		} else if (currentAction.equalsIgnoreCase("fire_toss")) {
			return "elemental"
		} else if (currentAction.equalsIgnoreCase("snake_toss")) {
			return "piercing"
		}
		UnmappedMain.log.error("enemy $name does not have a damage type")
		return "impact"
	}
}
