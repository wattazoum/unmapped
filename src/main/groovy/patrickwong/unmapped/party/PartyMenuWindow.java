package patrickwong.unmapped.party;

import java.util.List;

import patrickwong.unmapped.model.GameState;
import patrickwong.unmapped.model.PlayerCharacter;

import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;

public class PartyMenuWindow extends Window {
	private List<PlayerCharacter> party;
	
	public PartyMenuWindow() {
		super("Party Menu");
		party = GameState.getInstance().getParty();
		for (PlayerCharacter pc : party) {
			addComponent(new Button(pc.getName(), new CharacterMenuAction(pc.getName())));
		}
		addComponent(new Button("OK", new Action() {
			@Override
			public void doAction() {
				getOwner().getActiveWindow().close();
			}
		}));
	}
}
