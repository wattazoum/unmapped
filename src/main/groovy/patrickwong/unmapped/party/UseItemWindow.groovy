package patrickwong.unmapped.party;

import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.equipment.GameItem
import patrickwong.unmapped.model.PlayerCharacter

import com.googlecode.lanterna.gui.Action
import com.googlecode.lanterna.gui.Window
import com.googlecode.lanterna.gui.component.Button
import com.googlecode.lanterna.gui.component.Label

public class UseItemWindow extends Window {
	public UseItemWindow(GameItem gi, PlayerCharacter user) {
		super("Using " + gi.getName())
		
		def okAction = {
			UnmappedMain.closeCurrent()
			UnmappedMain.showWindow(new CharacterInventoryWindow(user))
		} as Action
		
		addComponent(new Label(gi.getActionInField().call(user)))
		addComponent(new Button("OK", okAction))
	}
}
