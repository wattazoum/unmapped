package patrickwong.unmapped.party.holybook

import patrickwong.unmapped.InterfaceState
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.model.GameState
import patrickwong.unmapped.model.HolyBook
import patrickwong.unmapped.model.PlayerCharacter
import patrickwong.unmapped.party.CharacterMenuAction
import patrickwong.unmapped.party.CharacterMenuWindow

import com.googlecode.lanterna.gui.Action
import com.googlecode.lanterna.gui.Window
import com.googlecode.lanterna.gui.component.Button
import com.googlecode.lanterna.gui.dialog.MessageBox

public class ChooseBookWindow extends Window {
	private PlayerCharacter pc
	private Action rta
	
	public ChooseBookWindow(PlayerCharacter playerCharacter, Action returnAction) {
		super(playerCharacter.name + " invoking a Holy Book")
		pc = playerCharacter
		rta = returnAction
		
		List<HolyBook> books = GameState.getInstance().getBooksForField()
		books.each {
			addComponent(new Button(it.shortName, genInvokeBookAction(it)))
		}
		
		addComponent(new Button("Cancel", new CharacterMenuAction(pc, rta)))
	}
	
	private Action genInvokeBookAction(HolyBook hb) {
		Action theAction = {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), pc.name + " invokes the " + hb.fullName, hb.useInField(pc))
			InterfaceState.nextWindow = new CharacterMenuWindow(pc.name, rta)
			UnmappedMain.closeCurrent()
		} as Action
		return theAction
	}
}
