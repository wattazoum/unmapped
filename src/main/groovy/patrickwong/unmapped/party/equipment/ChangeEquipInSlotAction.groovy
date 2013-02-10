package patrickwong.unmapped.party.equipment

import patrickwong.unmapped.InterfaceState
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.model.EquipSlot
import patrickwong.unmapped.model.PlayerCharacter

import com.googlecode.lanterna.gui.Action

public class ChangeEquipInSlotAction implements Action {
	PlayerCharacter playerCharacter
	EquipSlot equipSlot
	Action returnAction
	public ChangeEquipInSlotAction(PlayerCharacter playerCharacter, EquipSlot equipSlot, Action returnAction) {
		this.playerCharacter = playerCharacter
		this.equipSlot = equipSlot
		this.returnAction = returnAction
	}
	@Override
	public void doAction() {
		UnmappedMain.log.debug("pc: " + playerCharacter.name)
		UnmappedMain.log.debug("es: " + equipSlot.key)
		InterfaceState.nextWindow = new ChangeEquipInSlotWindow(playerCharacter, equipSlot, returnAction)
		UnmappedMain.closeCurrent()
	}
}
