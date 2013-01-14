package patrickwong.unmapped.combat;

public interface Combatant {
	public abstract String getName();
	
	public abstract String getAttackVerb();
	public abstract Integer rollAttackAccuracy();
	public abstract Integer rollAttackDamage();
	public abstract String getAttackDamageType();
	public abstract Integer rollMeleeEvade();
	public abstract Integer rollRangeEvade();
	
	public abstract void addShock(Integer shock);
	public abstract void addPain(Integer pain);
	public abstract void addWounds(Integer wounds);
	public abstract Integer getShock();
	public abstract Integer getPain();
	public abstract Integer getWounds();
	public abstract void removeShock(Integer shock);
	public abstract void removePain(Integer pain);
	public abstract void removeWounds(Integer wounds);
	
	public abstract Integer getCuttingResistance();
	public abstract Integer getPiercingResistance();
	public abstract Integer getImpactResistance();
	public abstract Integer getBulletResistance();
	public abstract Integer getElementalResistance();
	
	public abstract void addCuttingDamage(Integer damage);
	public abstract void addPiercingDamage(Integer damage);
	public abstract void addImpactDamage(Integer damage);
	public abstract void addBulletDamage(Integer damage);
	public abstract void addElementalDamage(Integer damage);
	
	public abstract String getStatus();
	public abstract String getKilledString();
	
	public abstract void beginRoundLogic();
	public abstract void midRoundLogic();
	public abstract void endRoundLogic();
	
	public abstract boolean isDead();
}
