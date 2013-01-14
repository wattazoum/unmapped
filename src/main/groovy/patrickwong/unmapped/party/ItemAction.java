package patrickwong.unmapped.party;

import patrickwong.unmapped.UnmappedMain;
import patrickwong.unmapped.equipment.GameItem;
import patrickwong.unmapped.model.PlayerCharacter;

import com.googlecode.lanterna.gui.Action;

public class ItemAction implements Action {
	private GameItem gi;
	private PlayerCharacter owner;
	public ItemAction(GameItem gi, PlayerCharacter owner) {
		this.gi = gi;
		this.owner = owner;
	}
	@Override
	public void doAction() {
		UnmappedMain.closeCurrent();
		UnmappedMain.showWindow(new ItemWindow(gi, owner));
	}

}
