package patrickwong.unmapped.model.equipment;

import patrickwong.unmapped.model.PlayerCharacter;

public interface ItemFieldAction {
	public abstract String useInField(PlayerCharacter pc);
}
