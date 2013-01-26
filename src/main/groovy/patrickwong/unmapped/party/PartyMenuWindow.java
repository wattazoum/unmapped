package patrickwong.unmapped.party;

import java.util.List;

import patrickwong.unmapped.model.GameState;
import patrickwong.unmapped.model.PlayerCharacter;

import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.Label;
import com.googlecode.lanterna.gui.component.Panel;

public class PartyMenuWindow extends Window {
	private List<PlayerCharacter> party;
	
	public PartyMenuWindow(Action returnAction) {
		super("Party Menu");
		party = GameState.getInstance().getParty();
		Panel mainPanel = new Panel(Panel.Orientation.HORISONTAL);
		Panel leftColumn = new Panel(Panel.Orientation.VERTICAL);
		Panel rightColumn = new Panel(Panel.Orientation.VERTICAL);
		addComponent(new Label("The party has " + GameState.getInstance().getMoneyString()));
		for (PlayerCharacter pc : party) {
			leftColumn.addComponent(new Button(pc.getName(), new CharacterMenuAction(pc.getName(), returnAction)));
		}
		rightColumn.addComponent(new Button("Quests", new QuestListAction(returnAction)));
		mainPanel.addComponent(leftColumn);
		mainPanel.addComponent(rightColumn);
		addComponent(mainPanel);
		addComponent(new Button("OK", returnAction));
	}
}
