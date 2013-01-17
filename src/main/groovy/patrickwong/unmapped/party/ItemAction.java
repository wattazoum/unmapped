package patrickwong.unmapped.party;

import patrickwong.unmapped.InterfaceState;
import patrickwong.unmapped.UnmappedMain;
import patrickwong.unmapped.model.PlayerCharacter;
import patrickwong.unmapped.model.equipment.GameItem;

import com.googlecode.lanterna.gui.Action;

public class ItemAction implements Action {
	private GameItem gi;
	private PlayerCharacter owner;
	private Action returnAction;
	public ItemAction(GameItem gi, PlayerCharacter owner, Action returnAction) {
		this.gi = gi;
		this.owner = owner;
		this.returnAction = returnAction;
	}
	@Override
	public void doAction() {
		InterfaceState.nextWindow = new ItemWindow(gi, owner, returnAction);
		UnmappedMain.closeCurrent();
	}

}
