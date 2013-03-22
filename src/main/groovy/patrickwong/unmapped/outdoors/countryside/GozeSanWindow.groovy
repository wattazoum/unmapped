package patrickwong.unmapped.outdoors.countryside

import patrickwong.unmapped.DiceRoller
import patrickwong.unmapped.InterfaceState
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.combat.CombatWindow
import patrickwong.unmapped.model.PlayerCharacter
import patrickwong.unmapped.model.equipment.ItemDatabase
import patrickwong.unmapped.outdoors.OutsideAction
import patrickwong.unmapped.outdoors.OutsideWindow
import patrickwong.unmapped.party.WhoWillWindow

import com.googlecode.lanterna.gui.Action
import com.googlecode.lanterna.gui.Window
import com.googlecode.lanterna.gui.component.Button
import com.googlecode.lanterna.gui.component.Label
import com.googlecode.lanterna.gui.dialog.MessageBox

public class GozeSanWindow extends Window {
	private boolean assassin
	public GozeSanWindow() {
		super("Countryside - Blind Songstress")
		if (DiceRoller.binary() > 0) {
			assassin = true
		}
		addComponent(new Label("You come across a colorfully-dressed woman with a tattoo on her cheek\nindicating that she is blind. She is singing a sad folk tune while\nstrumming a shamisen."))
		addComponent(new Label("You decide to..."))
		addComponent(new Button("...offer to help her", helpHerAction))
		addComponent(new Button("...stay away from her and move on", new OutsideAction()))
	}
	Action helpHerAction = {
		if (assassin) {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Helping the blind songstress - assassin!", "After some time travelling together, she suddenly says, 'I have been\ncontracted to kill you.' She extends her musical instrument into a\nspear and starts swinging for your head.")
			InterfaceState.nextWindow = new CombatWindow(new GozeSanCombatState())
			UnmappedMain.closeCurrent()
		} else {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Helping the blind songstress - benevolence!", "After some time travelling together, you arrive at a quiet roadside shrine.\nShe says, 'I shall rest here for now. But, I will not forget your\nkindness.' She offers a Norton Coin, which is a rare treasure.")
			InterfaceState.nextWindow = new WhoWillWindow("receive the Norton Coin", receiveCoin)
			UnmappedMain.closeCurrent()
		}
	} as Action
	
	Closure receiveCoin = { PlayerCharacter pc ->
		pc.addItem(ItemDatabase.getItem("special_nortoncoin"))
		InterfaceState.nextWindow = new OutsideWindow()
		UnmappedMain.closeCurrent()
	}
}
