package patrickwong.unmapped.newcharacter;

import patrickwong.unmapped.equipment.ItemDatabase;
import patrickwong.unmapped.model.GameState;
import patrickwong.unmapped.model.PlayerCharacter;

import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.TextBox;
import com.googlecode.lanterna.gui.dialog.MessageBox;
import com.googlecode.lanterna.terminal.TerminalSize;

public class NewCharacterWindow extends Window {
	private GameState gs;
	private TextBox nameInputBox;
	private PlayerCharacter tempCharacter;
	
	public NewCharacterWindow() {
		super("You are born with the name...");
		gs = GameState.getInstance();
		gs.setTempCharacter(new PlayerCharacter());
		tempCharacter = gs.getTempCharacter();
		nameInputBox = new TextBox();
		nameInputBox.setPreferredSize(new TerminalSize(30, 1));
		addComponent(nameInputBox);
		addComponent(new Button("OK", new Action() {
			@Override
			public void doAction() {
				String nameInput = nameInputBox.getText();
				if (gs.checkNameCollision(nameInput)) {
					new NameCollisionAction(nameInput).doAction();
					return;
				} else if (nameInput == null) {
					MessageBox.showMessageBox(getOwner(), "Blank Name!", "If your character has no name, then enter 'Nameless' \n or something like that, please.");
					return;
				} else if (nameInput.length() <= 0) {
					MessageBox.showMessageBox(getOwner(), "Blank Name!", "If your character has no name, then enter 'Nameless' \n or something like that, please.");
					return;
				}
				tempCharacter.setName(nameInput);
				
				tempCharacter.statsAllAddExp(1000);
				tempCharacter.skillsAllAddExp(1000);
				tempCharacter.addItem(ItemDatabase.getItem("painkiller"));
				tempCharacter.addItem(ItemDatabase.getItem("painkiller"));
				tempCharacter.addItem(ItemDatabase.getItem("bandage"));
				tempCharacter.addItem(ItemDatabase.getItem("bandage"));
				
				getOwner().getActiveWindow().close();
				getOwner().showWindow(new GenderSelectWindow(), GUIScreen.Position.CENTER);
			}
		}));
	}
}
