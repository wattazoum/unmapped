package patrickwong.unmapped.equipment;

import patrickwong.unmapped.combat.CombatState;
import patrickwong.unmapped.model.PlayerCharacter

public class GameItem implements Comparable {
	String key = "default_key";
	String name = "Default item name";
	String desc = "This is a default item. Not much to it."
	Closure actionInField = null
	Closure actionInCombat = null
	int stackSize = 1
	boolean stackable = true
	
	public String getReadableName() {
		if (stackable && (stackSize > 1)) {
			return (name + " x" + stackSize)
		} else {
			return name
		}
	}
	
	public boolean usableInField() {
		return (actionInField != null);
	}
	public boolean usableInCombat() {
		return (actionInCombat != null);
	}
	
	public String useInField(PlayerCharacter user) {
		if (!usableInField()) {
			return null
		}
		return actionInField.call(user);
	}
	
	public String useInCombat(PlayerCharacter user, CombatState state) {
		if (!usableInCombat()) {
			return null
		}
		return actionInCombat.call(user, state)
	}
	
	@Override
	public GameItem clone() {
		GameItem gi = new GameItem(key: this.key, name: this.name, desc: this.desc, actionInField: this.actionInField, actionInCombat: this.actionInCombat, stackSize: 1, stackable: this.stackable)
		return gi
	}
	
	@Override
	public int hashCode() {
		return key.hashCode()
	}
	
	public boolean equals(GameItem gi) {
		return key.equalsIgnoreCase(gi.key)
	}
	
	public boolean equals(Object o) {
		if (o instanceof GameItem) {
			return equals((GameItem)o)
		}
		return key.equalsIgnoreCase(o.toString())
	}
	
	public int compareTo(GameItem gi) {
		return key.compareToIgnoreCase(gi.key)
	}
	
	public int compareTo(Object o) {
		if (o instanceof GameItem) {
			return compareTo((GameItem)o)
		} else {
			return key.compareToIgnoreCase(o.toString())
		}
	}
}
