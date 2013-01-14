package patrickwong.unmapped.party;

import patrickwong.unmapped.UnmappedMain;
import patrickwong.unmapped.model.GameState;
import patrickwong.unmapped.model.PlayerCharacter;

import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.dialog.MessageBox;

public class CharacterMenuWindow extends Window {
	private GameState gs;
	private PlayerCharacter pc;
	
	public CharacterMenuWindow(String charName) {
		super(charName);
		gs = GameState.getInstance();
		pc = gs.getCharacter(charName);
		
		addComponent(new Button("Summary", new Action() {
			@Override
			public void doAction() {
				MessageBox.showMessageBox(getOwner(), pc.getName(), pc.summaryString());
			}
		}));
		
		addComponent(new Button("Inventory", new Action() {
			@Override
			public void doAction() {
				getOwner().showWindow(new CharacterInventoryWindow(pc), GUIScreen.Position.CENTER);
			}
		}));
		
		if (gs.getParty().size() > 1) {
			int order = pc.getOrder();
			if (order > 0) {
				addComponent(new Button("Move Up", new MoveUpAction(pc)));
			}
			if (order < (gs.getParty().size() - 1)) {
				addComponent(new Button("Move Down", new MoveDownAction(pc)));
			}
			addComponent(new Button("Dismiss from the party", new Action() {
				@Override
				public void doAction() {
					gs.removeFromParty(pc.getName());
					getOwner().getActiveWindow().close();
					getOwner().showWindow(new PartyMenuWindow(), GUIScreen.Position.CENTER);
				}
			}));
		}
		
		addComponent(new Button("OK", new Action() {
			@Override
			public void doAction() {
				UnmappedMain.closeCurrent();
				UnmappedMain.showWindow(new PartyMenuWindow());
			}
		}));
	}
}
