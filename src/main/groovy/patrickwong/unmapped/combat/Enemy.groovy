package patrickwong.unmapped.combat;

import patrickwong.unmapped.DiceRoller
import patrickwong.unmapped.model.GameState

public class Enemy implements Combatant {
	String name = "Target Dummy";
	int challengeLevel = 40;
	int shock = 0;
	int pain = 0;
	int wounds = 0;
	
	public int getChallengeLevel() {
		return challengeLevel;
	}

	public void setChallengeLevel(int challengeLevel) {
		this.challengeLevel = challengeLevel;
	}
	
	@Override
	public String getAttackVerb() {
		return " punches ";
	}
	
	@Override
	public Integer rollAttackAccuracy() {
		int pool = challengeLevel;
		pool -= shock;
		pool *= 2;
		return DiceRoller.binaryPool(pool);
	}
	
	@Override
	public Integer rollAttackDamage() {
		int pool = challengeLevel;
		pool -= shock;
		pool *= 1.5;
		return DiceRoller.binaryPool(pool);
	}
	
	@Override
	public String getAttackDamageType() {
		return "impact";
	}
	
	@Override
	public Integer rollMeleeEvade() {
		int pool = challengeLevel;
		pool -= shock;
		pool *= 2;
		return DiceRoller.binaryPool(pool);
	}

	@Override
	public Integer rollRangeEvade() {
		int pool = challengeLevel;
		pool -= shock;
		pool *= 2;
		return DiceRoller.binaryPool(pool);
	}

	@Override
	public void addShock(Integer shock) {
		this.shock += shock;
	}

	@Override
	public void addPain(Integer pain) {
		this.pain += pain;
	}

	@Override
	public void addWounds(Integer wounds) {
		this.wounds += wounds;
	}

	@Override
	public Integer getShock() {
		return shock;
	}

	@Override
	public Integer getPain() {
		return pain;
	}

	@Override
	public Integer getWounds() {
		return wounds;
	}
	public void removeShock(Integer shock) {
		this.shock -= shock;
		if (this.shock < 0) {
			this.shock = 0;
		}
	}
	public void removePain(Integer pain) {
		this.pain -= pain;
		if (this.pain < 0) {
			this.pain = 0;
		}
	}
	public void removeWounds(Integer wounds) {
		this.wounds -= wounds;
		if (this.wounds < 0) {
			this.wounds = 0;
		}
	}
	public Integer rollCuttingResistance() {
		return DiceRoller.binaryPool(challengeLevel);
	}
	public Integer rollPiercingResistance() {
		return DiceRoller.binaryPool(challengeLevel);
	}
	public Integer rollImpactResistance() {
		return DiceRoller.binaryPool(challengeLevel);
	}
	public Integer rollBulletResistance() {
		return DiceRoller.binaryPool(challengeLevel);
	}
	public Integer rollElementalResistance() {
		return DiceRoller.binaryPool(challengeLevel);
	}
	
	@Override
	public void addCuttingDamage(Integer damage) {
		DefaultCombatUtil.addCuttingDamage(damage, this);
	}

	@Override
	public void addPiercingDamage(Integer damage) {
		DefaultCombatUtil.addPiercingDamage(damage, this);
	}

	@Override
	public void addImpactDamage(Integer damage) {
		DefaultCombatUtil.addImpactDamage(damage, this);
	}

	@Override
	public void addBulletDamage(Integer damage) {
		DefaultCombatUtil.addBulletDamage(damage, this);
	}

	@Override
	public void addElementalDamage(Integer damage) {
		DefaultCombatUtil.addElementalDamage(damage, this);
	}

	@Override
	public String getStatus() {
		return DefaultCombatUtil.getStatus(this);
	}
	
	@Override
	public String getKilledString() {
		return ", killing it!";
	}
	
	public String nextAction() {
		Combatant target = GameState.getInstance().getRandomLivingCharacter();
		if (target != null) {
			return DefaultCombatUtil.doAttack(this, target);
		} else {
			return "$name jeers at the dead characters"
		}
	}
	public void beginRoundLogic() {
		shock += pain;
	}
	public void midRoundLogic() {
		shock -= DiceRoller.binaryPool(challengeLevel);
		if (shock < 0) {
			shock = 0;
		}
	}
	public void endRoundLogic() {
		
	}
	public boolean isDead() {
		return (wounds > challengeLevel);
	}
}
