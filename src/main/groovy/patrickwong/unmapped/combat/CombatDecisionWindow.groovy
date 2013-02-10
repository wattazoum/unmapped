package patrickwong.unmapped.combat

import patrickwong.unmapped.DiceRoller
import patrickwong.unmapped.InterfaceState
import patrickwong.unmapped.UnmappedMain
import patrickwong.unmapped.model.GameState
import patrickwong.unmapped.model.PlayerCharacter

import com.googlecode.lanterna.gui.Action
import com.googlecode.lanterna.gui.GUIScreen
import com.googlecode.lanterna.gui.Window
import com.googlecode.lanterna.gui.component.Button

public class CombatDecisionWindow extends Window {
	
	private GameState gs
	private CombatState state
	private PlayerCharacter pc
	CombatDecision cd
	
	public CombatDecisionWindow(CombatState combatState, PlayerCharacter playerCharacter) {
		super("Combat - " + playerCharacter.getName())
		
		gs = GameState.getInstance()
		state = combatState
		pc = playerCharacter
		cd = new CombatDecision(pc.getName())
		
		state.removeDecision(pc.getName())
		
		addComponent(new Button("Attack", attackAction))
		addComponent(new Button("Defend", defendAction))
		addComponent(new Button("Use an Item", useItemAction))
		addComponent(new Button("Invoke a Holy Book", holyBookAction))
		addComponent(new Button("Pass", passAction))
		addComponent(new Button("Assess the situation", assessAction))
		
		if (state.currentCharacter > 0) {
			addComponent(new Button("Previous", previousAction))
		}
		
		def cancelAction = {
			InterfaceState.nextWindow = new CombatWindow(state)
			UnmappedMain.closeCurrent()
		} as Action
		addComponent(new Button("Cancel", cancelAction))
	}
	
	def attackAction = {
		cd.setClosure {
			String action = ""
			Combatant target = DefaultCombatUtil.pcChooseTarget(pc, state)
			if (target == null) {
				action += pc.getName() + pc.getAttackVerb() + "thin air"
				return action
			}
			action = DefaultCombatUtil.doAttack(pc, target)
			return action
		}
		Closure toNextDecision = CombatProcessUtil.genToNextDecisionClosure()
		toNextDecision(cd, state)
	} as Action
	
	def defendAction = {
		cd.setClosure {
			String action = pc.getName() + " defends with both hands"
			pc.activeDefense = true
			return action
		}
		Closure toNextDecision = CombatProcessUtil.genToNextDecisionClosure()
		toNextDecision(cd, state)
	} as Action

	def holyBookAction = {
		InterfaceState.nextWindow = new WhichBookWindow(pc, state)
		UnmappedMain.closeCurrent()
	} as Action

	def useItemAction = {
		InterfaceState.nextWindow = new CombatInventoryWindow(pc, state)
		UnmappedMain.closeCurrent()
	} as Action
	
	def passAction = {
		cd.setClosure {
			String action = pc.getName() + " passes"
			pc.removeShock(DiceRoller.binaryPool(20))
			return action
		}
		Closure toNextDecision = CombatProcessUtil.genToNextDecisionClosure()
		toNextDecision(cd, state)
	} as Action

	def assessAction = {
		UnmappedMain.showWindow(new CombatStatusWindow(state, true));
	} as Action
	
	def previousAction = {
		state.currentCharacter -= 1
		InterfaceState.nextWindow = new CombatDecisionWindow(state, gs.getCharacter(state.getCurrentCharacter()))
		UnmappedMain.closeCurrent()
	} as Action
}
