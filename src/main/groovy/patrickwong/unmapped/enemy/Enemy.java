package patrickwong.unmapped.enemy;

import patrickwong.unmapped.combat.Combatant;

public interface Enemy extends Combatant {
	public abstract void setName(String name);
	public abstract String nextAction();
}
