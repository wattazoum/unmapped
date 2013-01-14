package patrickwong.unmapped.combat;

import java.util.List;

import patrickwong.unmapped.model.GameState;
import patrickwong.unmapped.model.PlayerCharacter;

import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.GUIScreen;
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
		
		addComponent(new Button("OK", new Action() {
			@Override
			public void doAction() {
				getOwner().getActiveWindow().close();
				if (!pop) {
					getOwner().showWindow(new CombatWindow(state), GUIScreen.Position.CENTER);
				}
			}
		}));
	}
}
