package patrickwong.unmapped.party

import patrickwong.unmapped.InterfaceState
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.model.GameState
import patrickwong.unmapped.model.PlayerCharacter
import patrickwong.unmapped.model.equipment.GameItem

import com.googlecode.lanterna.gui.Action
import com.googlecode.lanterna.gui.Window
import com.googlecode.lanterna.gui.component.Button

public class GiveOneWindow extends Window {
	private PlayerCharacter pc
	private GameItem gi
	private Action rta
	
	public GiveOneWindow(PlayerCharacter playerCharacter, GameItem gameItem, Action returnAction) {
		super(playerCharacter.name + " giving a " + gameItem.getReadableName())
		this.pc = playerCharacter
		this.gi = gameItem
		this.rta = returnAction
		List<PlayerCharacter> party = GameState.getInstance().getParty()
		for (PlayerCharacter receiver : party) {
			if (!(pc.name.equalsIgnoreCase(receiver.name))) {
				addComponent(new Button(receiver.name, makeGiveAction(receiver)))
			}
		}
		addComponent(new Button("Cancel", cancelAction))
	}
	
	def cancelAction = {
		InterfaceState.nextWindow = new ItemWindow(gi, pc, rta)
		UnmappedMain.closeCurrent()
	} as Action
	
	private Action makeGiveAction(PlayerCharacter receiver) {
		def giveAction = {
			GameItem transitingItem = pc.takeItem(gi.key)
			receiver.addItem(transitingItem)
			InterfaceState.nextWindow = new ItemWindow(gi, pc, rta)
			UnmappedMain.closeCurrent()
		} as Action
		return giveAction
	}
}
