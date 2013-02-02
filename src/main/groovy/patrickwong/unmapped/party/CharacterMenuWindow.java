package patrickwong.unmapped.party;

import patrickwong.unmapped.InterfaceState;
import patrickwong.unmapped.UnmappedMain;
import patrickwong.unmapped.model.GameState;
import patrickwong.unmapped.model.PlayerCharacter;

import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;

public class CharacterMenuWindow extends Window {
	private GameState gs;
	private PlayerCharacter pc;
	private Action rta;
	
	public CharacterMenuWindow(String charName, Action returnAction) {
		super(charName);
		gs = GameState.getInstance();
		pc = gs.getCharacter(charName);
		rta = returnAction;
		
		addComponent(new Button("Summary", new Action() {
			@Override
			public void doAction() {
				InterfaceState.nextWindow = new CharacterSummaryWindow(pc, rta);
				UnmappedMain.closeCurrent();
			}
		}));
		
		addComponent(new Button("Inventory", new Action() {
			@Override
			public void doAction() {
				InterfaceState.nextWindow = new CharacterInventoryWindow(pc, rta);
				UnmappedMain.closeCurrent();
			}
		}));
		
		addComponent(new Button("Weapons and Shields", new Action() {
			@Override
			public void doAction() {
				InterfaceState.nextWindow = new CharacterGripWindow(pc, rta);
				UnmappedMain.closeCurrent();
			}
		}));
		
		addComponent(new Button("Equipment", new Action() {
			@Override
			public void doAction() {
				InterfaceState.nextWindow = new CharacterEquipmentWindow(pc, rta);
				UnmappedMain.closeCurrent();
			}
		}));
		
		if (gs.getParty().size() > 1) {
			int order = pc.getOrder();
			if (order > 0) {
				addComponent(new Button("Move Up", new MoveUpAction(pc, rta)));
			}
			if (order < (gs.getParty().size() - 1)) {
				addComponent(new Button("Move Down", new MoveDownAction(pc, rta)));
			}
			addComponent(new Button("Dismiss from the party", new Action() {
				@Override
				public void doAction() {
					gs.removeFromParty(pc.getName());
					InterfaceState.nextWindow = new PartyMenuWindow(rta);
					UnmappedMain.closeCurrent();
				}
			}));
		}
		
		addComponent(new Button("OK", new Action() {
			@Override
			public void doAction() {
				InterfaceState.nextWindow = new PartyMenuWindow(rta);
				UnmappedMain.closeCurrent();
			}
		}));
	}
}
