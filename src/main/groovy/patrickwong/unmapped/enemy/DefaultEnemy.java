package patrickwong.unmapped.enemy;

import patrickwong.unmapped.DiceRoller;
import patrickwong.unmapped.combat.Combatant;
import patrickwong.unmapped.combat.DefaultCombatUtil;
import patrickwong.unmapped.model.GameState;

public class DefaultEnemy implements Enemy {
	private String name = "Target Dummy";
	private int challengeLevel = 50;
	private int shock = 0;
	private int pain = 0;
	private int wounds = 0;
	
	public DefaultEnemy() { }
	
	public DefaultEnemy(String name) {
		this.name = name;
	}
	
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
		return DiceRoller.binaryPool(pool);
	}
	
	@Override
	public Integer rollAttackDamage() {
		int pool = challengeLevel;
		pool -= shock;
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
		return DiceRoller.binaryPool(pool);
	}

	@Override
	public Integer rollRangeEvade() {
		int pool = challengeLevel;
		pool -= shock;
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
	public Integer getCuttingResistance() {
		return DiceRoller.binaryPool(challengeLevel);
	}
	public Integer getPiercingResistance() {
		return DiceRoller.binaryPool(challengeLevel);
	}
	public Integer getImpactResistance() {
		return DiceRoller.binaryPool(challengeLevel);
	}
	public Integer getBulletResistance() {
		return DiceRoller.binaryPool(challengeLevel);
	}
	public Integer getElementalResistance() {
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
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getStatus() {
		return DefaultCombatUtil.getStatus(this);
	}
	
	@Override
	public String getKilledString() {
		return ", killing it!";
	}
	
	@Override
	public String nextAction() {
		Combatant target = GameState.getInstance().getRandomLivingCharacter();
		return DefaultCombatUtil.doAttack(this, target);
	}
	public void beginRoundLogic() {
		shock += pain;
	}
	public void midRoundLogic() {
		shock -= challengeLevel;
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
