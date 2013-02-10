package patrickwong.unmapped.party.equipment;

import patrickwong.unmapped.InterfaceState;
import patrickwong.unmapped.UnmappedMain;
import patrickwong.unmapped.model.PlayerCharacter;
import patrickwong.unmapped.model.equipment.GameItem;

import com.googlecode.lanterna.gui.Action;

public class UseItemAction implements Action {
	private GameItem gi;
	private PlayerCharacter user;
	private Action returnAction;
	public UseItemAction(GameItem gi, PlayerCharacter user, Action returnAction) {
		this.gi = gi;
		this.user = user;
		this.returnAction = returnAction;
	}
	@Override
	public void doAction() {
		InterfaceState.nextWindow = new UseItemWindow(gi, user, returnAction);
		UnmappedMain.closeCurrent();
	}

}
