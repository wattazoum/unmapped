package patrickwong.unmapped.combat;

import java.util.List;

import patrickwong.unmapped.UnmappedMain;
import patrickwong.unmapped.model.GameState;

import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.Label;

public class CombatWindow extends Window {
	private GameState gs;
	private CombatState state;
	
	public CombatWindow(CombatState combatState) {
		super("Combat!");
		
		gs = GameState.getInstance();
		state = combatState;
		state.cancelDecisions();
		List<EnemyGroup> enemyGroups = state.getEnemyGroups();
		
		String enemyGroupSummary = "You face:\n";
		int liveGroups = 0;
		for (EnemyGroup eg : enemyGroups) {
			enemyGroupSummary += eg.getSummary();
			enemyGroupSummary += "\n";
			if (eg.getDistance() < 1) {
				state.setInMelee(true);
				eg.setDistance(0);
			}
			if (eg.hasValidEnemies()) {
				liveGroups++;
			}
		}
		if (liveGroups <= 0) {
			addComponent(new Label(state.doVictory()));
			addComponent(new Button("OK", new Action() {
				@Override
				public void doAction() {
					UnmappedMain.closeCurrent();
				}
			}));
		} else {
			addComponent(new Label(enemyGroupSummary));
			
			addComponent(new Button("Fight", new Action() {
				@Override
				public void doAction() {
					UnmappedMain.closeCurrent();
					getOwner().showWindow(new CombatDecisionWindow(state, gs.getCharacter(state.getCurrentCharacter())), GUIScreen.Position.CENTER);
				}
			}));
			
			if (!state.getInMelee()) {
				addComponent(new Button("Advance", new AdvanceAction()));
			}
			
			addComponent(new Button("Think", new Action() {
				@Override
				public void doAction() {
					UnmappedMain.closeCurrent();
					getOwner().showWindow(new CombatStatusWindow(state, false), GUIScreen.Position.CENTER);
				}
			}));
			
			addComponent(new Button("Run", new Action() {
				@Override
				public void doAction() {
					UnmappedMain.closeCurrent();
				}
			}));
		}
	}
	
	
	class AdvanceAction implements Action {
		@Override
		public void doAction() {
			List<EnemyGroup> enemyGroups = state.getEnemyGroups();
			for (EnemyGroup eg : enemyGroups) {
				eg.distance -= 1;
				if (eg.distance < 0) {
					eg.distance = 0;
					state.setInMelee(true);
				}
			}
			state.getDecisions().add(new PartyAdvanceDecision());
			UnmappedMain.closeCurrent();
			getOwner().showWindow(new CombatRoundActionWindow(state), GUIScreen.Position.CENTER);
		}
	}
}
