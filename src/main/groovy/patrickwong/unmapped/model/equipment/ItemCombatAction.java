package patrickwong.unmapped.model.equipment;

import patrickwong.unmapped.combat.CombatState;
import patrickwong.unmapped.model.PlayerCharacter;

public interface ItemCombatAction {
	public abstract String useInCombat(PlayerCharacter pc, CombatState state);
}
