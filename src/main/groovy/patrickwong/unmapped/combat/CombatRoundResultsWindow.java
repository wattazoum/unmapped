package patrickwong.unmapped.combat;

import java.util.List;

import patrickwong.unmapped.InterfaceState;
import patrickwong.unmapped.UnmappedMain;
import patrickwong.unmapped.model.GameState;
import patrickwong.unmapped.model.PlayerCharacter;

import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.Label;

public class CombatRoundResultsWindow extends Window {
	private CombatState state;
	private List<PlayerCharacter> party;
	private List<EnemyGroup> enemyGroups;
	
	public CombatRoundResultsWindow(CombatState combatState) {
		super("Combat Results");
		
		state = combatState;
		party = GameState.getInstance().getParty();
		enemyGroups = combatState.getEnemyGroups();
		
		for (PlayerCharacter pc : party) {
			pc.beginRoundLogic();
		}
		for (EnemyGroup eg : enemyGroups) {
			List<Enemy> enemies = eg.getValidEnemies();
			for (Enemy enemy : enemies) {
				enemy.beginRoundLogic();
			}
		}
		
		List<CombatDecision> decisions = combatState.getDecisions();
		for (CombatDecision cd : decisions) {
			PlayerCharacter pc = GameState.getInstance().getCharacter(cd.getCharName());
			if (pc != null) {
				pc.midRoundLogic();
			}
			addComponent(new Label(cd.doAction()));
		}
		
		for (EnemyGroup eg : enemyGroups) {
			addComponent(new Label(eg.nextAction()));
		}
		
		addComponent(new Button("OK", new Action() {
			@Override
			public void doAction() {
				for (PlayerCharacter pc : party) {
					pc.endRoundLogic();
				}
				for (EnemyGroup eg : enemyGroups) {
					List<Enemy> enemies = eg.getValidEnemies();
					for (Enemy enemy : enemies) {
						enemy.endRoundLogic();
					}
				}
				InterfaceState.nextWindow = new CombatStatusWindow(state, false);
				UnmappedMain.closeCurrent();
			}
		}));
	}
}
