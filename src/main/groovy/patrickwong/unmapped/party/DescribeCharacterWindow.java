package patrickwong.unmapped.party;

import patrickwong.unmapped.InterfaceState;
import patrickwong.unmapped.UnmappedMain;
import patrickwong.unmapped.model.PlayerCharacter;

import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.TextBox;
import com.googlecode.lanterna.terminal.TerminalSize;

public class DescribeCharacterWindow extends Window {
	private TextBox descriptionBox;
	private PlayerCharacter pc;
	private Action rta;
	public DescribeCharacterWindow(PlayerCharacter playerCharacter, Action returnAction) {
		super("Description for " + playerCharacter.getName());
		pc = playerCharacter;
		rta = returnAction;
		descriptionBox = new TextBox();
		descriptionBox.setPreferredSize(new TerminalSize(30, 20));
		descriptionBox.setText(pc.getDescription());
		addComponent(descriptionBox);
		addComponent(new Button("OK", new SetDescriptionAction()));
	}
	
	class SetDescriptionAction implements Action {
		@Override
		public void doAction() {
			pc.setDescription(descriptionBox.getText());
			InterfaceState.nextWindow = new CharacterSummaryWindow(pc, rta);
			UnmappedMain.closeCurrent();
		}
	}
}
