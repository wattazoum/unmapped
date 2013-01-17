package patrickwong.unmapped.combat;

import java.util.List;

import patrickwong.unmapped.InterfaceState;
import patrickwong.unmapped.UnmappedMain;
import patrickwong.unmapped.mainmenu.MainMenuWindow;
import patrickwong.unmapped.model.GameState;
import patrickwong.unmapped.model.PlayerCharacter;

import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.Label;

public class CombatStatusWindow extends Window {
	private CombatState state;
	private GameState gs;
	boolean pop;
	
	public CombatStatusWindow(CombatState combatState, boolean isPopup) {
		super("Combat Status");
		
		pop = isPopup;
		state = combatState;
		gs = GameState.getInstance();
		List<PlayerCharacter> party = gs.getParty();
		
		for (PlayerCharacter pc : party) {
			addComponent(new Label(pc.getStatus()));
		}
		
		String enemyStatusString = "\n";
		List<EnemyGroup> enemyGroups = state.getEnemyGroups();
		for (EnemyGroup eg : enemyGroups) {
			enemyStatusString += eg.getStatus();
		}
		addComponent(new Label(enemyStatusString));
		
		if (!(gs.isPartyAlive())) {
			addComponent(new Label("The party has perished!"));
			addComponent(new Button("This is the end...", new Action() {
				@Override
				public void doAction() {
					GameState.wipeGameState();
					InterfaceState.nextWindow = new MainMenuWindow();
					UnmappedMain.closeCurrent();
				}
			}));
		}
		
		addComponent(new Button("OK", new Action() {
			@Override
			public void doAction() {
				if (!pop) {
					InterfaceState.nextWindow = new CombatWindow(state);
				}
				UnmappedMain.closeCurrent();
			}
		}));
	}
}
