package patrickwong.unmapped.demon

import java.util.List;

import patrickwong.unmapped.DiceRoller
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.combat.Enemy

public class MinionSamael extends Enemy {
	
	public MinionSamael() {
		super()
		challengeLevel = DiceRoller.binaryPool(120)
		possibleActions = ["headbutt", "acid", "bite", "claw"]
		possibleActions = ["buzzsaw_shot", "knife_toss"]
	}
	
	@Override
	public String getAttackVerb() {
		if (currentAction.equalsIgnoreCase("headbutt")) {
			return " headbutts at ";
		} else if (currentAction.equalsIgnoreCase("acid")) {
			return " sprays acid at "
		} else if (currentAction.equalsIgnoreCase("bite")) {
			return " bites at "
		} else if (currentAction.equalsIgnoreCase("claw")) {
			return " claws at "
		} else if (currentAction.equalsIgnoreCase("buzzsaw_shot")) {
			return " shoots a buzzsaw at "
		} else if (currentAction.equalsIgnoreCase("knife_toss")) {
			return " throws a knife at "
		}
		UnmappedMain.log.error("enemy $name does not have an attack verb")
		UnmappedMain.log.error("currentAction: $currentAction")
		return " BLAH "
	}
	
	@Override
	public String getAttackDamageType() {
		if (currentAction.equalsIgnoreCase("headbutt")) {
			return "impact";
		} else if (currentAction.equalsIgnoreCase("acid")) {
			return "elemental"
		} else if (currentAction.equalsIgnoreCase("bite")) {
			return "piercing"
		} else if (currentAction.equalsIgnoreCase("claw")) {
			return "cutting"
		} else if (currentAction.equalsIgnoreCase("buzzsaw_shot")) {
			return "cutting"
		} else if (currentAction.equalsIgnoreCase("knife_toss")) {
			return "piercing"
		}
		UnmappedMain.log.error("enemy $name does not have a damage type")
		return "impact"
	}
}
