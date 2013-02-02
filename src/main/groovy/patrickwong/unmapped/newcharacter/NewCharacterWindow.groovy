package patrickwong.unmapped.newcharacter;

import patrickwong.unmapped.InterfaceState
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.model.GameState
import patrickwong.unmapped.model.PlayerCharacter
import patrickwong.unmapped.model.equipment.ItemDatabase

import com.googlecode.lanterna.gui.Action
import com.googlecode.lanterna.gui.Window
import com.googlecode.lanterna.gui.component.Button
import com.googlecode.lanterna.gui.component.TextBox
import com.googlecode.lanterna.gui.dialog.MessageBox
import com.googlecode.lanterna.terminal.TerminalSize

public class NewCharacterWindow extends Window {
	private GameState gs;
	private TextBox nameInputBox;
	private PlayerCharacter tempCharacter;
	
	public NewCharacterWindow() {
		super("You are born with the name...");
		UnmappedMain.suppressLevelupMessages = true;
		gs = GameState.getInstance();
		gs.setTempCharacter(new PlayerCharacter());
		tempCharacter = gs.getTempCharacter();
		nameInputBox = new TextBox();
		nameInputBox.setPreferredSize(new TerminalSize(30, 1));
		addComponent(nameInputBox);
		addComponent(new Button("OK", acceptNameAction));
	}
	
	def acceptNameAction = {
		String nameInput = nameInputBox.getText();
		if (gs.checkNameCollision(nameInput)) {
			new NameCollisionAction(nameInput).doAction();
			return;
		} else if (nameInput == null) {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Blank Name!", "If your character has no name, then enter 'Nameless'\nor something like that, please.");
			return;
		} else if (nameInput.length() <= 0) {
			MessageBox.showMessageBox(UnmappedMain.getGUI(), "Blank Name!", "If your character has no name, then enter 'Nameless'\nor something like that, please.");
			return;
		}
		tempCharacter.setName(nameInput);
		
		tempCharacter.statsAllAddExp(400);
		tempCharacter.skillsAllAddExp(100);
		
		InterfaceState.nextWindow = new GenderSelectWindow()
		UnmappedMain.closeCurrent()
	} as Action
}
