package patrickwong.unmapped.party;

import patrickwong.unmapped.UnmappedMain;
import patrickwong.unmapped.equipment.GameItem;
import patrickwong.unmapped.model.PlayerCharacter;

import com.googlecode.lanterna.gui.Action;

public class UseItemAction implements Action {
	private GameItem gi;
	private PlayerCharacter user;
	public UseItemAction(GameItem gi, PlayerCharacter user) {
		this.gi = gi;
		this.user = user;
	}
	@Override
	public void doAction() {
		UnmappedMain.closeCurrent();
		UnmappedMain.showWindow(new UseItemWindow(gi, user));
	}

}
