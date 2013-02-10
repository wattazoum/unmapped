package patrickwong.unmapped.model.equipment;

import patrickwong.unmapped.combat.CombatState;
import patrickwong.unmapped.model.PlayerCharacter

public class GameItem implements Comparable {
	String key = "default_key";
	String name = "Default item name";
	String desc = "This is a default item. Not much to it."
	int baseValue = 0
	ItemFieldAction actionInField = null
	ItemCombatAction actionInCombat = null
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
		return actionInField.useInField(user);
	}
	
	public String useInCombat(PlayerCharacter user, CombatState state) {
		if (!usableInCombat()) {
			return null
		}
		return actionInCombat.useInCombat(user, state)
	}
	
	@Override
	public GameItem clone() {
		GameItem gi = new GameItem(key: this.key, name: this.name, desc: this.desc, actionInField: this.actionInField, actionInCombat: this.actionInCombat, stackSize: 1, stackable: this.stackable, baseValue: this.baseValue)
		return gi
	}
	
	@Override
	public int hashCode() {
		return key.hashCode()
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof GameItem) {
			GameItem gi = (GameItem)o
			return key.equalsIgnoreCase(gi.key)
		}
		return key.equalsIgnoreCase(o.toString())
	}
	
	@Override
	public int compareTo(Object o) {
		if (o instanceof GameItem) {
			return name.compareToIgnoreCase(((GameItem)o).name)
		} else {
			return name.compareToIgnoreCase(o.toString())
		}
	}
}
