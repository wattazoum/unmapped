package patrickwong.unmapped.party

import patrickwong.unmapped.InterfaceState
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.model.PlayerCharacter
import patrickwong.unmapped.model.equipment.Equippable

import com.googlecode.lanterna.gui.Action

public class PutItemInSlotAction implements Action {
	private PlayerCharacter pc
	private Equippable gi
	private Action returnAction
	public PutItemInSlotAction(PlayerCharacter pc, Equippable gi, Action returnAction) {
		this.pc = pc
		this.gi = gi
		this.returnAction = returnAction
	}
	@Override
	public void doAction() {
		InterfaceState.nextWindow = new PutItemInSlotWindow(pc, gi, returnAction)
		UnmappedMain.closeCurrent()
	}

}
