package patrickwong.unmapped.party;

import java.util.List;

import patrickwong.unmapped.model.GameState;
import patrickwong.unmapped.model.PlayerCharacter;

import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.Label;

public class PartyMenuWindow extends Window {
	private List<PlayerCharacter> party;
	
	public PartyMenuWindow(Action returnAction) {
		super("Party Menu");
		party = GameState.getInstance().getParty();
		addComponent(new Label("The party has " + GameState.getInstance().getMoneyString()));
		for (PlayerCharacter pc : party) {
			addComponent(new Button(pc.getName(), new CharacterMenuAction(pc.getName(), returnAction)));
		}
		addComponent(new Button("OK", returnAction));
	}
}
