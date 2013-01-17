package patrickwong.unmapped.party;

import patrickwong.unmapped.InterfaceState
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.model.PlayerCharacter
import patrickwong.unmapped.model.equipment.GameItem

import com.googlecode.lanterna.gui.Action
import com.googlecode.lanterna.gui.Window
import com.googlecode.lanterna.gui.component.Button
import com.googlecode.lanterna.gui.component.Label

public class UseItemWindow extends Window {
	private PlayerCharacter pc
	private Action rta
	public UseItemWindow(GameItem gi, PlayerCharacter user, Action returnAction) {
		super("Using " + gi.getName())
		pc = user
		rta = returnAction
		
		addComponent(new Label(gi.getActionInField().call(pc)))
		addComponent(new Button("OK", okAction))
	}
	def okAction = {
		InterfaceState.nextWindow = new CharacterInventoryWindow(pc, rta)
		UnmappedMain.closeCurrent()
	} as Action
}
