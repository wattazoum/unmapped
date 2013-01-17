package patrickwong.unmapped.combat;

import patrickwong.unmapped.DiceRoller;


public class DefaultCombatUtil {
	
	// TODO - implement logic for ranged attacks
	public static Combatant pcChooseTarget(CombatState state) {
		Combatant target = null;
		EnemyGroup eg = state.getRandomGroupInMelee();
		if (eg == null) {
			return null;
		}
		Enemy validEnemy = eg.getRandomValidEnemy();
		if (validEnemy == null) {
			return null;
		}
		target = validEnemy;
		return target;
	}
	
	public static String doAttack(Combatant attacker, Combatant target) {
		String attackString = "";
		attackString += attacker.getName();
		attackString += attacker.getAttackVerb() + target.getName();
		int attackerAccuracy = attacker.rollAttackAccuracy();
		int targetEvade = target.rollMeleeEvade();
		if (attackerAccuracy > targetEvade) {
			attackString += " and hits";
			int damagePoolBonus = attackerAccuracy - targetEvade;
			doDamage(attacker, target, damagePoolBonus);
			if (target.isDead()) {
				attackString += target.getKilledString();
			}
		} else {
			attackString += " but misses";
		}
		return attackString;
	}
	
	public static void doDamage(Combatant attacker, Combatant target, int damagePoolBonus) {
		String damageType = attacker.getAttackDamageType();
		int damage = attacker.rollAttackDamage();
		if (damagePoolBonus > 0) {
			damage += DiceRoller.binaryPool(damagePoolBonus);
		}
		if (damageType.equalsIgnoreCase("cutting")) {
			target.addCuttingDamage(damage);
		} else if (damageType.equalsIgnoreCase("piercing")) {
			target.addPiercingDamage(damage);
		} else if (damageType.equalsIgnoreCase("impact")) {
			target.addImpactDamage(damage);
		} else if (damageType.equalsIgnoreCase("bullet")) {
			target.addBulletDamage(damage);
		} else if (damageType.equalsIgnoreCase("elemental")) {
			target.addElementalDamage(damage);
		} else {
			target.addElementalDamage(damage);
		}
	}
	
	public static void addCuttingDamage(int damage, Combatant target) {
		int finalDamage = damage - target.rollCuttingResistance();
		if (finalDamage <= 0) {
			return;
		}
		int shock = finalDamage;
		int pain = finalDamage - 10;
		int wounds = (finalDamage - 20) / 2;
		if (shock > 0) {
			target.addShock(shock);
		}
		if (pain > 0) {
			target.addPain(pain);
		}
		if (wounds > 0) {
			target.addWounds(wounds);
		}
	}
	public static void addPiercingDamage(int damage, Combatant target) {
		int finalDamage = damage - target.rollPiercingResistance();
		if (finalDamage <= 0) {
			return;
		}
		int shock = finalDamage;
		int pain = finalDamage - 20;
		int wounds = (finalDamage - 30) * 2;
		if (shock > 0) {
			target.addShock(shock);
		}
		if (pain > 0) {
			target.addPain(pain);
		}
		if (wounds > 0) {
			target.addWounds(wounds);
		}
	}
	public static void addImpactDamage(int damage, Combatant target) {
		int finalDamage = damage - target.rollImpactResistance();
		if (finalDamage <= 0) {
			return;
		}
		int shock = finalDamage + 10;
		int pain = (finalDamage - 30) * 2;
		int wounds = finalDamage - 30;
		if (shock > 0) {
			target.addShock(shock);
		}
		if (pain > 0) {
			target.addPain(pain);
		}
		if (wounds > 0) {
			target.addWounds(wounds);
		}
	}
	public static void addBulletDamage(int damage, Combatant target) {
		int finalDamage = damage - target.rollBulletResistance();
		if (finalDamage <= 0) {
			return;
		}
		int shock = finalDamage;
		int pain = finalDamage - 10;
		int wounds = finalDamage - 20;
		if (shock > 0) {
			target.addShock(shock);
		}
		if (pain > 0) {
			target.addPain(pain);
		}
		if (wounds > 0) {
			target.addWounds(wounds);
		}
	}
	public static void addElementalDamage(int damage, Combatant target) {
		int finalDamage = damage - target.rollElementalResistance();
		if (finalDamage <= 0) {
			return;
		}
		int shock = finalDamage;
		int pain = (finalDamage / 2);
		int wounds = (finalDamage / 3);
		if (shock > 0) {
			target.addShock(shock);
		}
		if (pain > 0) {
			target.addPain(pain);
		}
		if (wounds > 0) {
			target.addWounds(wounds);
		}
	}
	public static String getStatus(Combatant target) {
		String statusString = target.getName() + " is ";
		
		if (target.isDead()) {
			statusString += "dead";
			return statusString;
		}
		
		String shockString = "";
		if (target.getShock() > 80) {
			shockString += "totally rocked";
		} else if (target.getShock() > 60) {
			shockString += "staggered";
		} else if (target.getShock() > 40) {
			shockString += "stumbling";
		} else if (target.getShock() > 20) {
			shockString += "unbalanced";
		} else if (target.getShock() > 0) {
			shockString += "slightly shaken";
		}
		
		String painString = "";
		if (target.getPain() > 60) {
			painString += "going into shock";
		} else if (target.getPain() > 50) {
			painString += "fainting from pain";
		} else if (target.getPain() > 40) {
			painString += "in extreme pain";
		} else if (target.getPain() > 30) {
			painString += "hurting badly";
		} else if (target.getPain() > 20) {
			painString += "feeling pain";
		} else if (target.getPain() > 10) {
			painString += "hurt";
		} else if (target.getPain() > 0) {
			painString += "lightly bruised";
		}
		
		String woundString = "";
		if (target.getWounds() > 60) {
			woundString += "amazingly still alive!";
		} else if (target.getWounds() > 50) {
			woundString += "about to die!";
		} else if (target.getWounds() > 40) {
			woundString += "critically wounded";
		} else if (target.getWounds() > 30) {
			woundString += "seriously wounded";
		} else if (target.getWounds() > 20) {
			woundString += "bleeding";
		} else if (target.getWounds() > 10) {
			woundString += "bloodied";
		} else if (target.getWounds () > 0) {
			woundString += "slightly scratched";
		}
		
		if (shockString.length() > 0) {
			statusString += shockString;
			if (painString.length() > 0) {
				if (woundString.length() > 0) {
					statusString += ", " + painString + ", and " + woundString;
				} else {
					statusString += " and " + painString;
				}
			} else if (woundString.length() > 0) {
				statusString += " and " + woundString;
			}
		} else {
			if (painString.length() > 0) {
				statusString += painString;
				if (woundString.length() > 0) {
					statusString += " and " + woundString;
				}
			} else if (woundString.length() > 0) {
				statusString += woundString;
			} else {
				statusString += "in good condition";
			}
		}
		
		return statusString;
	}
}