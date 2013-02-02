package patrickwong.unmapped.party;

import patrickwong.unmapped.InterfaceState;
import patrickwong.unmapped.UnmappedMain;
import patrickwong.unmapped.model.PlayerCharacter;

import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.Label;
import com.googlecode.lanterna.gui.component.Panel;
import com.googlecode.lanterna.gui.component.TextArea;
import com.googlecode.lanterna.terminal.TerminalSize;

public class CharacterSummaryWindow extends Window {
	private PlayerCharacter pc;
	private Action rta;
	public CharacterSummaryWindow(PlayerCharacter playerCharacter, Action returnAction) {
		super(playerCharacter.getName());
		pc = playerCharacter;
		rta = returnAction;
		Panel mainPanel = new Panel(Panel.Orientation.HORISONTAL);
		Panel leftColumn = new Panel(Panel.Orientation.VERTICAL);
		Panel rightColumn = new Panel(Panel.Orientation.VERTICAL);
		leftColumn.addComponent(new Label(pc.summaryString()));
		TextArea descriptionArea = new TextArea();
		descriptionArea.setPreferredSize(new TerminalSize(30, 20));
		descriptionArea.appendLine(pc.getDescription());
		// rightColumn.addComponent(descriptionArea);
		// rightColumn.addComponent(new Button("Edit Description", new EditDescriptionAction()));
		mainPanel.addComponent(leftColumn);
		mainPanel.addComponent(rightColumn);
		addComponent(mainPanel);
		addComponent(new Label(pc.getStatus()));
		addComponent(new Button("OK", new CharacterMenuAction(pc, rta)));
	}
	
	class EditDescriptionAction implements Action {
		@Override
		public void doAction() {
			InterfaceState.nextWindow = new DescribeCharacterWindow(pc, rta);
			UnmappedMain.closeCurrent();
		}
	}
}
