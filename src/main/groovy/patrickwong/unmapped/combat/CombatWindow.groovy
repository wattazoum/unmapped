package patrickwong.unmapped.combat;

import patrickwong.unmapped.InterfaceState
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.model.GameState

import com.googlecode.lanterna.gui.Action
import com.googlecode.lanterna.gui.GUIScreen
import com.googlecode.lanterna.gui.Window
import com.googlecode.lanterna.gui.component.Button
import com.googlecode.lanterna.gui.component.Label

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
			addComponent(new Button("OK", afterVictoryAction));
		} else {
			addComponent(new Label(enemyGroupSummary));
			
			addComponent(new Button("Fight", fightAction));
			if (!state.getInMelee()) {
				addComponent(new Button("Advance", advanceAction));
			}
			addComponent(new Button("Think", thinkAction));
			addComponent(new Button("Run", runAction));
		}
	}
	
	def afterVictoryAction = {
		UnmappedMain.closeCurrent()
		state.afterVictory()
	} as Action

	def fightAction = {
		InterfaceState.nextWindow = new CombatDecisionWindow(state, gs.getCharacter(state.getCurrentCharacter()))
		UnmappedMain.closeCurrent()
	} as Action

	def advanceAction = {
		List<EnemyGroup> enemyGroups = state.getEnemyGroups();
		for (EnemyGroup eg : enemyGroups) {
			eg.setDistance(eg.getDistance() - 1);
			if (eg.getDistance() < 0) {
				eg.setDistance(0);
				state.setInMelee(true);
			}
		}
		state.getDecisions().add(new PartyAdvanceDecision());
		InterfaceState.nextWindow = new CombatRoundResultsWindow(state)
		UnmappedMain.closeCurrent()
	} as Action
	
	def thinkAction = {
		InterfaceState.nextWindow = new CombatStatusWindow(state, false)
		UnmappedMain.closeCurrent()
	} as Action
	
	def runAction = {
		gs.party.each {
			it.removeShock(1000)
		}
		UnmappedMain.closeCurrent()
		state.afterRunning()
	} as Action
}
